package com.unrc.app.models;
import org.javalite.activejdbc.Model;



public class Address extends Model {
    static{
        validatePresenceOf("street").message("Por favor, ingrese la calle");
        validatePresenceOf("num").message("Por favor, ingrese el numero de dirección");
        validatePresenceOf("city_id");
    }
    
	//Crea una direccion en la base de datos
	public static Address CreateAddress(String street, String num, String neighborhood, City city ) {
		Address address= create("street",street,"num",num,"neighborhood",neighborhood);
    	if (!existAddress(street,num, city.getInteger("id"))){
    		city.add(address);
    		address.saveIt();
 	
    	}
    	return (findByAddress(street,num,city.getInteger("id")));
	}

	//retorna un valor booleano si existe una direccion en la base de datos
	private static Boolean existAddress(String street, String num, int idCity){
    	Boolean ret=true;
    	if( Address.first("city_id = ? and street = ? and num = ?" ,idCity ,street,num)==null){
    		return false;
    	}
    	return ret;
    } 
	
	public static Address findByAddress(String street, String num, int idCity){
		return (Address.findFirst("city_id = ? and street = ? and num = ?" ,idCity ,street,num));
	}
	
	public String getStreet(){
		return (getString("street"));
	}
	public String getNum(){
		return (getString("num"));
	}
	
	public String getNeighborhood(){
		return (getString("neighborhood"));
	}
	
    public void deleteAddress(){
    	int idCity= getInteger("city_id") ;
    	this.delete();
		if (null==Address.findFirst("city_id = ?", idCity)){
			
			City.delete("id = ?", idCity );
		}
    }

}