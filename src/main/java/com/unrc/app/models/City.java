package com.unrc.app.models;
import org.javalite.activejdbc.Model;

public class City extends Model {
	private String name;//nombre ciudad
	private int code;//codigo postal
    static{
        validatePresenceOf("name","code");
    }
    
    //constructor vacio
    public City(){
    }
     
    /**
	 * @param name
	 * @param code
	 */
	public City(String name, int code) {
		this.name = name;
		this.code = code;
	}

	//Retorna si un modelo se encuentra almacenado en la base de datos
	public Boolean existCity(){
    	Boolean ret=true;
    	if( City.first("code = ? ",this.code)==null){
    		return false;
    	}
    	return ret;
    }
 	
	//Crea un modelo y lo registra en la base de datos si es que ya no existe, en caso de existir no hace nada
    public void createCity(){
    	this.set("name",this.name,"code",this.code);
        if(!existCity()){
        	saveIt();
        }
    }
    
    //retorna un modelo de ciudad
    public City returnModel(){
    	return City.first("code = ?",code);
    }
    
    
    //Retorna el id de un modelo
    public Integer returnId(){
    	if (existCity()){
    		return returnModel().getInteger("id");
    	}
    	else{
    		return -1;
    	}
    }    
    
    public void deleteCity(){
    	City cityForDelete= returnModel();
    	cityForDelete.deleteCascade();
    }
}
    

	
