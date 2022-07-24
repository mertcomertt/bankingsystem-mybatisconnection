package com.example.bankingsystem_dbconnection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.bankingsystem_dbconnection.dto.request.AccountCreateRequest;
import com.example.bankingsystem_dbconnection.dto.request.AccountDepositRequest;
import com.example.bankingsystem_dbconnection.dto.request.AccountTransferRequest;
import com.example.bankingsystem_dbconnection.model.Account;
import com.example.bankingsystem_dbconnection.model.Account.AccountType;
import com.example.bankingsystem_dbconnection.repository.IAccountRepository;
import com.example.bankingsystem_dbconnection.service.IAccountService;

@RestController
public class AccountController {

	private final IAccountService accountService;

	
	private final KafkaTemplate<String, String> producer;
	
    @Autowired
    public AccountController(IAccountService accountService,KafkaTemplate<String, String> producer) {
        this.accountService = accountService;
        this.producer = producer;
    }
    
	
	

	@PostMapping(path = "/accounts")
	public ResponseEntity<?> createAccount(@RequestBody AccountCreateRequest request) {

		try {
			Object obj = (Object) this.accountService.createAccount(request.getName(), request.getSurname(),
					request.getEmail(), request.getTc(), request.getType());
			return ResponseEntity.status(HttpStatus.OK).body(obj);
		} catch (ResponseStatusException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/accounts/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable long id) {
		Account ac = this.accountService.getAccountById(id);
		return ResponseEntity.status(HttpStatus.OK).lastModified(ac.getLastUpdateDate()).body(ac);
}
	
	@PutMapping(path = "/accounts/{id}")
	public ResponseEntity<?> depositAccount(@PathVariable long id,@RequestBody AccountDepositRequest request) {
		String msg = id + " deposit amount: " + request.getBalance();
		producer.send("logs", msg);
		return ResponseEntity.status(HttpStatus.OK).body(this.accountService.depositAccount(id, request.getBalance()));
	}
	@PostMapping("/accounts/{id}/")
	public ResponseEntity<?> transferBalance(@PathVariable long id,@RequestBody AccountTransferRequest request){
		String msg = id + " transfer amount: " + request.getAmount() + " "
				+ " ,transferred_account: "
				+ request.getTransferredAccountNumber();
		producer.send("logs", msg);
		return ResponseEntity.status(HttpStatus.OK).body(this.accountService.transferBalance(request.getTransferredAccountNumber(),request.getAmount(),id));
	}
	@GetMapping("/accounts/{id}/log")
	@CrossOrigin(origins = "http://localhost:6162")
	public ResponseEntity<Object> loggingAccount(@PathVariable long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.accountService.getAccountLog(id));
	}
	
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<> delete(@PathVariable int id) {
		
	}
}