package com.unrc.app.models;
import org.javalite.activejdbc.Model;



public class Address extends Model {
	private int cityId;//referencia a la ciudad 
	public String street;//calle
	private int num;//numero de domicilio
	private String neighborhood;//barrio
    static{
        validatePresenceOf("city_id","street","num");
    }
    
	/**
	 * 
	 */
	public Address() {
	}

	/**
	 * @param cityId
	 * @param street
	 * @param num
	 * @param neighborhood
	 */
	public Address(int cityId, String street, int num, String neighborhood) {
		this.cityId = cityId;
		this.street = street;
		this.num = num;
		this.neighborhood = neighborhood;
	}

	
	//Crea una direccion en la base de datos
	public void CreateAddress() {
    	set("city_id",cityId,"street",street,"num",num,"neighborhood",neighborhood);
    	if (!existAddress()){
    		saveIt();
    	}
	}

	//retorna un valor booleano si existe una direccion en la base de datos
	public Boolean existAddress(){
    	Boolean ret=true;
    	if( Address.first("city_id = ? and street = ? and num = ?" ,cityId ,street,num)==null){
    		return false;
    	}
    	return ret;
    } 
	
	//Retorna el modelo de una base de datos correspondiente a los datos del objeto
    public Address returnModel(){
    	return Address.first("city_id = ? and street = ? and num = ?" ,cityId ,street,num);
    }
    
    //Retorna el id de el modelo
    public Integer returnId(){
    	if (existAddress()){
    		return returnModel().getInteger("id");
    	}
    	else{
    		return -1;
    	}
    }
    
		
    public void deleteAddress(){
    	int idCity= getInteger("city_id") ;
    	this.delete();
		if (null==Address.findFirst("city_id = ?", idCity)){
			
			City.delete("id = ?", idCity );
		}
    }
}