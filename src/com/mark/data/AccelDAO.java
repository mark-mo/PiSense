package com.mark.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.mark.beans.AccelSensorModel;
import com.mark.exception.DatabaseErrorException;

@Stateless
@Local(DataAccessInterface.class)
@LocalBean
@Named
public class AccelDAO implements DataAccessInterface<AccelSensorModel> {
	private Connection con;

	public AccelDAO() {
		// null at first, to be set later
		con = null;
	}

	public void makeConnection() {
		if (con == null) {
			// DB Connection Info
			con = null;
			String url = "jdbc:mysql://172.30.100.47:3306/PiSense";
			String username = "accelroot";
			String password = "thisisthepassword";

			try {
				// Connect to database
				con = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean create(AccelSensorModel model) {
		makeConnection();

		try {
			// Query for # of rows with matching username and password
			String sql1 = String.format("INSERT INTO readings (PITCH, ROLL, YAW, DATETIME) VALUES (%f, %f, %f, '%s')",
					model.getPitch(), model.getRoll(), model.getYaw());
			Statement stmt1 = con.createStatement();
			stmt1.executeUpdate(sql1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseErrorException(e);
		} finally {
			// Cleanup Database
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DatabaseErrorException(e);
				}
			}
		}
		return true;
	}

	// Finds past 10 entries
	@Override
	public List<AccelSensorModel> findAll() {
		makeConnection();
		List<AccelSensorModel> accelList = new ArrayList<AccelSensorModel>();

		try {
			String sql1 = "SELECT * FROM readings";
			System.out.println("Hello");
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while (rs1.next()) {
				AccelSensorModel weather = new AccelSensorModel(rs1.getDouble("PITCH"), rs1.getDouble("ROLL"),
						rs1.getDouble("YAW"), rs1.getString("DATETIME"));

				accelList.add(weather);
			}
			rs1.close();
			stmt1.close();

			// return albums;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseErrorException(e);
		} finally {
			// Cleanup Database
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DatabaseErrorException(e);
				}
			}
		}
		return accelList;
	}

	@Override
	public AccelSensorModel findByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccelSensorModel findBy(AccelSensorModel t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(AccelSensorModel t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(AccelSensorModel t) {
		// TODO Auto-generated method stub
		return false;
	}
}
