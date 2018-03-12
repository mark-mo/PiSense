package com.mark.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.mark.beans.AccelSensorModel;
import com.mark.data.DataAccessInterface;

@Stateless
@Local(AccelServiceInterface.class)
@LocalBean
public class AccelService {
	@EJB
	DataAccessInterface<AccelSensorModel> dao;
	
	public boolean save(AccelSensorModel model) {
		return dao.create(model);
	}

	public List<AccelSensorModel> getReadings() {
		return dao.findAll();
	}
}
