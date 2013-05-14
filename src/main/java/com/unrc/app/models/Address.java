package com.unrc.app.models;
import org.javalite.activejdbc.Model;

public class Address extends Model {
    static{
    	//valido que una direccion tenga calle numero y ciudad
        validatePresenceOf("street").message("Por favor, ingrese la calle");
        validatePresenceOf("num").message("Por favor, ingrese el numero de dirección");
        validatePresenceOf("city_id");
    }
    
	//Crea una direccion en la base de datos y retorno el modelo
	public static Address createAddress(String street, String num, String neighborhood, City city ) {
		Address address= create("street",street,"num",num,"neighborhood",neighborhood);
    	if (!existAddress(street,num, city.getInteger("id"))){
    		city.add(address);
    		address.saveIt();
    	}
    	return (findByAddress(street,num,city.getInteger("id")));
	}//end CreateAddress

	//retorna un valor booleano si existe una direccion en la base de datos
	private static Boolean existAddress(String street, String num, int idCity){
    	Boolean ret=true;
    	if( Address.first("city_id = ? and street = ? and num = ?" ,idCity ,street,num)==null){
    		return false;
    	}
    	return ret;
    }//end existAddress
	
	//Busco una direccion en la base de datos dado el id de la ciudad calle y numero
	public static Address findByAddress(String street,String num, int idCity){
		return (Address.findFirst("city_id = ? and street = ? and num = ?" ,idCity ,street,num));
	}//end findByAddress
	
	//Obtengo la calle
	public String getStreet(){
		return (getString("street"));
	}//end getStreet
	
	//Obtengo el numero de de la direccio
	public String getNum(){
		return (getString("num"));
	}//end getNum
	
	//Obtengo el barrio
	public String getNeighborhood(){
		return (getString("neighborhood"));
	}//end getNeighborhood
	
	//seteo la calle
	public void setStreet(String street){
		set("street", street);
	}//end setStreet
	
	//Seteo el numero de la direccion
	public void setNum(String num){
		set("num", num);
	}//end setNum
	
	//Seteo el barrio 
	public void setNeighborhood(String neighborhood){
		set("neighborhood", neighborhood);
	}//end setNeighborhood
	
	//borro una direccion, en caso de que la las direcciones existentes en la base de datos no usen más la ciudad de esta
	//borra la ciudad
    public void deleteAddress(){
    	int idCity= getInteger("city_id") ;
    	this.delete();
		if (null==Address.findFirst("city_id = ?", idCity)){
			City.delete("id = ?", idCity );
		}
    }//end deleteAddress
}