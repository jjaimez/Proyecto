package com.unrc.app.models;
import org.javalite.activejdbc.Model;


public class RealEstate extends Model {
	static{
        validatePresenceOf("name");
    }

    public Boolean existRealEstate(){
    	Boolean ret=true;
    	if( findFirst("name = ?",get("name"))==null){
    		return false;
    	}
    	return ret;
    }
}  