package com.gcu.edu.model;

import java.text.DateFormat;
import java.util.Date;

import com.gcu.edu.util.Month;

public class AccelSensorModel {
	private String sensorName;
	private double pitch;
	private double roll;
	private double yaw;
	private int year;
	private int day;
	private int hour;
	private int minute;
	private int second;
	private String month;
	private String timeZone;
	private String dow;
	private String time;
	
	private Month monthLabel;

	public AccelSensorModel() {
		sensorName = "";
		pitch = 0;
		roll = 0;
		yaw = 0;

		Date date = new Date();
		time = DateFormat.getDateInstance().format(date);
		
		time = date.toString();
		
		setFormatTime(time);
	}

	public AccelSensorModel(double pitch, double roll, double yaw) {
		this.pitch = pitch;
		this.roll = roll;
		this.yaw = yaw;
		
		Date date = new Date();
		time = DateFormat.getDateInstance().format(date);
		
		time = date.toString();
		
		System.out.println("Original Time: " + time);
		setFormatTime(time);
	}
	
	public AccelSensorModel(double pitch, double roll, double yaw, String date) {
		this.sensorName = "Main";
		this.pitch = pitch;
		this.roll = roll;
		this.yaw = yaw;
		
		time = date;
	}

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public double getPitch() {
		return pitch;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

	public double getRoll() {
		return roll;
	}

	public void setRoll(double roll) {
		this.roll = roll;
	}

	public double getYaw() {
		return yaw;
	}

	public void setYaw(double yaw) {
		this.yaw = yaw;
	}
	
	public int getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public String getDow() {
		return dow;
	}

	public void setDow(String dow) {
		this.dow = dow;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTime() {
		return time;
	}
	
	public void setTime(String date) {
		this.time = date;
	}

	// Breaks apart the given string and formats the string to the correct DateTime for JavaScript
	public void setFormatTime(String date) {
		
		this.setDow(date.substring(0, 3));
		date = date.substring(4);
		this.month = date.substring(0, 3);
		this.monthLabel = Month.valueOf(this.month);
		this.month = monthLabel.getIdentifier();
		date = date.substring(4);
		this.day = Integer.parseInt(date.substring(0, 2));
		date = date.substring(3);
		this.hour = Integer.parseInt(date.substring(0, 2));
		date = date.substring(3);
		this.minute = Integer.parseInt(date.substring(0, 2));
		date = date.substring(3);
		this.second = Integer.parseInt(date.substring(0, 2));
		date = date.substring(3);
		this.timeZone = date.substring(0, 3);
		date = date.substring(4);
		this.year = Integer.parseInt(date.substring(0, 4));
		
		String formattedTime;
		if(day < 10) {
			System.out.println("Day is less than ten, formatting properly");
			formattedTime = year + "-" + month + "-0" + day + " " + hour + ":" + minute + ":" + second;
		}
		else
			formattedTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
		
		time = formattedTime;
	}
}
