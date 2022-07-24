package com.example.bankingsystem_dbconnection.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.bankingsystem_dbconnection.model.Account;

public interface IMyBatisAccountService {

	
	
	@Transactional
	public void createAccount(Account account);

	@Transactional
	Account getAccountById(int id);

	@Transactional
	public void updateAccount(int id, double balance, long lastUpdateDate);

	@Transactional
	public void deleteAccount(int id, boolean isdeleted, long lastUpdateDate) ;
}
