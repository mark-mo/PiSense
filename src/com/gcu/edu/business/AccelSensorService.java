package com.gcu.edu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.edu.dao.AccelSensorDAOInterface;
import com.gcu.edu.exception.AccelSensorsNotFound;
import com.gcu.edu.model.AccelSensorModel;

public class AccelSensorService implements AccelSensorServiceInterface {
	AccelSensorDAOInterface dao;
	
	public AccelSensorService() {
	}

	public boolean create(AccelSensorModel move) {
		System.out.println("In Serivce:create");
		return dao.createSensor(move);
	}

	public List<AccelSensorModel> findAll() throws AccelSensorsNotFound {
		return dao.findAll();
	}
	
	@Autowired
	public void setAccelSensorDAO(AccelSensorDAOInterface dao) {
		this.dao = dao;
	}
}
