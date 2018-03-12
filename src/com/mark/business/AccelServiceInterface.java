package com.mark.business;

import java.util.List;

import com.mark.beans.AccelSensorModel;

public interface AccelServiceInterface {
	public boolean save(AccelSensorModel model);
	
	public List<AccelSensorModel> getReadings();
}
