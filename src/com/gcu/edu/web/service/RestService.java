package com.gcu.edu.web.service;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.edu.business.AccelSensorServiceInterface;
import com.gcu.edu.exception.AccelSensorsNotFound;
import com.gcu.edu.exception.DAOException;
import com.gcu.edu.model.AccelSensorModel;
import com.gcu.edu.model.ResponseDataModel;
import com.gcu.edu.model.ResponseModel;
import com.gcu.edu.util.HtmlCode;

import org.slf4j.Logger;

/**
 * Code retrieved and modified from Mark Reha's Cloud Computing code This class
 * is REST Service that implements the REST API to support the Weather Sensor
 * IoT.
 * 
 * @author Mark Mott
 * @version 1
 */
@RestController
@RequestMapping("accel")
public class RestService {
	Logger logger = LoggerFactory.getLogger(RestService.class);
	@Autowired
	AccelSensorServiceInterface service;

	/**
	 * Test Service API at /test using HTTP GET.
	 * 
	 * @return Response Model
	 */
	@GetMapping("/flights")
	public ResponseModel test() {
		// Log the API call
		logger.info("Entering RestService.test()");

		// Return a Test Response
		ResponseModel response = new ResponseModel(0, "This is a test");
		return response;
	}

	/**
	 * Save Sensor Data API at /save using HTTP POST.
	 * 
	 * @param model
	 *            The Weather Data to save.
	 * @return Response Model with error code of 1 for no error, 0 if save failed,
	 *         -2 if database error
	 */
	@PostMapping("/save")
	public ResponseModel saveAccelSensorData(@RequestBody AccelSensorModel model) {
		try {
			System.out.println("In save");
			// Log the API call
			logger.info("Entering RestService.saveAccelSensorData()");
			System.out.println("Model: " + model.getPitch());

			// Insert the model into the queue
			boolean OK = service.create(model);
			
			String good = "OK: " + model.getPitch() + "," + model.getRoll() + "," + model.getYaw();
			
			// Return OK Response
			ResponseModel response = new ResponseModel(
					OK ? HtmlCode.Success.getIdentifier() : 400, OK ? good : "Bad Request, Data Connection failed");
			return response;
		} catch (DAOException e) {
			// Return Database Exception Response
			ResponseModel response = new ResponseModel(-2, "Database Exception: " + e.getMessage());
			return response;
		}
	}

	/**
	 * Get Sensor Data for a specified Sensor Device ID at /get/{device}/{id} using
	 * HTTP GET.
	 * 
	 * @param deviceID
	 *            Device ID to query Sensor Data from
	 * @param id
	 *            Sensor ID to return
	 * @return Response Model with error code of 0 for no error, -1 if no sensor
	 *         data found, -2 if database error
	 */
	@GetMapping("/readings")
	public ResponseDataModel getAccelSensorData() {
		List<AccelSensorModel> data;
		int returnError;
		String returnMessage;

		// Get data from python code
		try {
			// Log the API call
			System.out.println("Entering RestService.getAccelSensorData()");
			logger.info("Entering RestService.getAccelSensorData()");

			// Call Business Service to get the Sensor Data for a specified Sensor Data ID
			data = service.findAll();
			// Return Response and Data
			returnError = HtmlCode.Success.getIdentifier();
			returnMessage = "OK";

			ResponseDataModel response = new ResponseDataModel(returnError, returnMessage, data);
			return response;
		} catch (NotFoundException e) {
			// Return Database Error Response
			ResponseDataModel response = new ResponseDataModel(HtmlCode.BadRequest.getIdentifier(),
					"Sensor Data Not Found" + e.getMessage(), new ArrayList<AccelSensorModel>());
			return response;
		} catch (DAOException e) {
			// Return Database Error Response
			ResponseDataModel response = new ResponseDataModel(-2, "Database Exception: " + e.getMessage(),
					new ArrayList<AccelSensorModel>());
			return response;
		} catch (AccelSensorsNotFound e) {
			returnError = HtmlCode.BadRequest.getIdentifier();
			returnMessage = "Error";
			data = new ArrayList<AccelSensorModel>();

			ResponseDataModel response = new ResponseDataModel(returnError, returnMessage, data);
			return response;
		}
	}

	// ***** Dependencies and Helper Functions *****

	/**
	 * IoC helper function.
	 * 
	 * @param service
	 *            WeatherServiceInterface to inject into this service
	 *            implementation.
	 */
	@Autowired
	public void setService(AccelSensorServiceInterface service) {
		this.service = service;
	}
}