package com.gcu.edu.business;

import java.util.List;

import com.gcu.edu.exception.AccelSensorsNotFound;
import com.gcu.edu.model.AccelSensorModel;

public interface AccelSensorServiceInterface {
	public boolean create(AccelSensorModel move);
	public List<AccelSensorModel> findAll() throws AccelSensorsNotFound;
}
