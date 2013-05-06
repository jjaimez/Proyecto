package com.unrc.app.models;
import org.javalite.activejdbc.Model;

public class Address extends Model {
    static{
        validatePresenceOf("city_id","street","num");
    }

    public Boolean existAddress(){
    	Boolean ret=true;
    	if( Address.findFirst("city_id = ? and street = ? and num = ?" ,get("city_id") ,get("street"),get("num"))==null){
    		return false;
    	}
    	return ret;
    }
    
}