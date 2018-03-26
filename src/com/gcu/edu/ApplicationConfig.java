package com.gcu.edu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.gcu.edu.business.AccelSensorService;
import com.gcu.edu.business.AccelSensorServiceInterface;
import com.gcu.edu.dao.AccelSensorDAO;
import com.gcu.edu.dao.AccelSensorDAOInterface;

@Configuration
public class ApplicationConfig {
	@Bean(name="accelSensorDAO")
	@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public AccelSensorDAOInterface getAccelSensorDAO() {
		return new AccelSensorDAO();
	}
	
	@Bean(name="accelSensorService")
	@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
	public AccelSensorServiceInterface getAccelSensorService() {
		return new AccelSensorService();
	}
}
