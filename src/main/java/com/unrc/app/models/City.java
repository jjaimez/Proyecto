/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */

package com.unrc.app.models;
import org.javalite.activejdbc.Model;

public class City extends Model {
    static{
    	//valido que la ciudad tenga nombre y codigo postal
        validatePresenceOf("name").message("Falta nombre ciudad");
        validatePresenceOf("code").message("Falta codigo postal");
    }

	//Retorna si un modelo se encuentra almacenado en la base de datos
	public static Boolean existCity(int code){
    	Boolean ret=true;
    	if( City.first("code = ? ", code )==null){
    		return false;
    	}
    	return ret;
    }//end existcity
	
	//Retorno el modelo de una ciudad que se busca en la BD por el codigo postal
	public static City findByCode(int code){
		return (findFirst("code = ?", code));
	}//end findByCode
 		
	//Crea un modelo y lo registra en la base de datos si es que ya no existe, en caso de existir no hace nada
    public static City createCity(String name, int code){
    	City city= create("name",name,"code",code);
        if(!existCity(code)){
        	city.saveIt();
        }
        return findByCode(code);
    }//end createCity
    
    //Obtengo el nombre de la ciudad
	public String getName(){
		return (getString("name"));
	}//end getName
	
	//Obtengo el codigo postal
	public int getCode(){
		return (getInteger("code"));
	}//end getCode
	
	//Seteo una ciudad
	public void setCity(String name,int code){
		set("name", name,"code",code);
		saveIt();
	}//end setCity
}