package com.gcu.edu.dao;

import java.util.List;

import com.gcu.edu.exception.AccelSensorsNotFound;
import com.gcu.edu.model.AccelSensorModel;

public interface AccelSensorDAOInterface {
	public boolean createSensor(AccelSensorModel sensorModel);
	public List<AccelSensorModel> findAll() throws AccelSensorsNotFound;
}
