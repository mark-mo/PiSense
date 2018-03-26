package com.gcu.edu.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseDataModel extends ResponseModel {
   private List<AccelSensorModel> list = new ArrayList<AccelSensorModel>();

   public ResponseDataModel(int status, String message, List<AccelSensorModel> data) 
   {
      super(status, message);
      this.list = data;
   }
	
   public List<AccelSensorModel> getList() {
      return list;
   }

   public void setList(List<AccelSensorModel> list) {
      this.list = list;
   }
}
