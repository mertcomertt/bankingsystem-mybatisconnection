package com.example.bankingsystem_dbconnection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem_dbconnection.model.Account;
import com.example.bankingsystem_dbconnection.repository.IMyBatisAccountRepository;

@Service
public class MyBatisAccountService implements IMyBatisAccountService {

	
	private IMyBatisAccountRepository mybatisRepository;
	
	MyBatisAccountService(IMyBatisAccountRepository mybatisRepository){
		this.mybatisRepository = mybatisRepository;
	}
	@Override
	public void createAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account getAccountById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAccount(int id, double balance, long lastUpdateDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount(int id, boolean isdeleted, long lastUpdateDate) {
		// TODO Auto-generated method stub
		
	}

}
