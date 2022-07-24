package com.example.bankingsystem_dbconnection.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.bankingsystem_dbconnection.model.Account;

@Mapper
public interface IMyBatisAccountRepository {

	
	@Transactional
	public void createAccount(Account account);

	@Transactional
	Account getAccountById(int id);

	@Transactional
	public void updateAccount(int id, double balance, long lastUpdateDate);

	@Transactional
	public void deleteAccount(int id, boolean isdeleted, long lastUpdateDate);


}
