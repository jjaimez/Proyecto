package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Owner extends Model {
  static{
      validatePresenceOf("first_name", "last_name","dni","address_id");
  }
  
  public Boolean existOwner(){
  	Boolean ret=true;
  	if( first("dni = ?" ,get("dni"))==null){
  		return false;
  	}
  	return ret;
  }
  
}