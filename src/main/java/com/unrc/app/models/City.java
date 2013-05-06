package com.unrc.app.models;
import org.javalite.activejdbc.Model;

public class City extends Model {
    static{
        validatePresenceOf("name","code");
    }

    public Boolean existCity(){
    	Boolean ret=true;
    	if( findFirst("code = ?",get("code"))==null){
    		return false;
    	}
    	return ret;
    }
    
}

	
