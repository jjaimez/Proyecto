package com.unrc.app.models;
import org.javalite.activejdbc.Model;

public class City extends Model {
    static{
        validatePresenceOf("name").message("Por favor, ingrese el nombre de la ciudad");
        validatePresenceOf("code").message("Por favor, ingrese el codigo postal de la ciudad");
    }

	//Retorna si un modelo se encuentra almacenado en la base de datos
	public static Boolean existCity(int code){
    	Boolean ret=true;
    	if( City.first("code = ? ", code )==null){
    		return false;
    	}
    	return ret;
    }
	
	public static City findByCode(int code){
		return (findFirst("code = ?", code));
	}
 		
	//Crea un modelo y lo registra en la base de datos si es que ya no existe, en caso de existir no hace nada
    public static City createCity(String name, int code){
    	City city= create("name",name,"code",code);
        if(!existCity(code)){
        	city.saveIt();
        }
        return findByCode(code);
    }
    
	public String getName(){
		return (getString("name"));
	}
	public int getCode(){
		return (getInteger("code"));
	}
}