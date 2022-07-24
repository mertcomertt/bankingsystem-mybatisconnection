package com.example.bankingsystem_dbconnection.service;

import java.util.List;

import com.example.bankingsystem_dbconnection.model.Log;

public interface IMyBatisLogService {

public void insertLog(String message);
	
	
	public List<Log> getLogs();
}
