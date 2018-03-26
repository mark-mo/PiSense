package com.gcu.edu.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.edu.exception.AccelSensorsNotFound;
import com.gcu.edu.exception.DAOException;
import com.gcu.edu.model.AccelSensorModel;

public class AccelSensorDAO implements AccelSensorDAOInterface {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public boolean createSensor(AccelSensorModel sensorModel) {
		String sql = "INSERT INTO PISENSE.readings(PITCH, ROLL, YAW, DATETIME) VALUES(?,?,?,?)";
		
		System.out.println("In createSensor");
		try {
			int rows = jdbcTemplateObject.update(sql,(float)sensorModel.getPitch(), (float)sensorModel.getRoll(), 
					(float)sensorModel.getYaw(), sensorModel.getTime());
			System.out.println("Rows = " + rows);
		    return true;
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<AccelSensorModel> findAll() throws AccelSensorsNotFound {
		String sql = "SELECT * FROM PISENSE.readings";
		List<AccelSensorModel> output = new ArrayList<AccelSensorModel>();

		SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
		
		if(!srs.isBeforeFirst()) {
			throw new AccelSensorsNotFound();
		}
		while (srs.next()) {
			output.add(new AccelSensorModel(
				srs.getFloat("PITCH"),
				srs.getFloat("ROLL"),
				srs.getFloat("YAW"),
				srs.getString("DATETIME")
			));
		}
		return output;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
}
