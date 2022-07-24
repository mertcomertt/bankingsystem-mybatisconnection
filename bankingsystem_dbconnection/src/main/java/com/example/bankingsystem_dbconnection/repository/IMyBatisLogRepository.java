package com.example.bankingsystem_dbconnection.repository;

import java.util.List;

import com.example.bankingsystem_dbconnection.model.Log;

public interface IMyBatisLogRepository {

	public void insertLog(String message);
	
	
	public List<Log> getLogs();
}
