package com.unrc.app.models;

import org.javalite.activejdbc.Model;
import java.util.Iterator;
import java.util.List;

public class Owner extends Model {
	
	static{
		validatePresenceOf("first_name").message("Por favor ingrese su nombre");
		validatePresenceOf("last_name").message("Por favor, ingrese su apellido");
		validatePresenceOf("dni").message("Por favor, ingrese su dni");
		validatePresenceOf("address_id");
	}
 

	//Retorna un booleano diciendo si existe en la base de datos el modelo dado
	public static Boolean existOwner(String dni){
		Boolean ret=true;
		if( first("dni = ?" ,dni)==null){
			return false;
		}
		return ret;
	}
  
	//crea un owner con relacion a las inmo
	public static Owner createOwner(String firstName,String lastName, String dni,String email, Address address,List<String> realEstates){
		Owner owner= create("first_name",firstName,"last_name",lastName,"dni",dni, "email", email);
		if(!existOwner(dni)){
			address.add(owner);
			Iterator<String> itr = realEstates.iterator();
  	  		while (itr.hasNext()){
  	  			String name = (String)itr.next();
  	  			RealEstate realEstate = RealEstate.findByName(name);
  	  	  		realEstate.add(realEstate);
  	  		}
			owner.saveIt();
		}
		return (findByDni(dni));
	}
	
	//Crea un owner en la base de datos si es que no existe previamente
	public static Owner createOwner(String firstName,String lastName, String dni,String email, Address address,String...Inmos){
		Owner owner= create("first_name",firstName,"last_name",lastName,"dni",dni, "email", email);
		if(!existOwner(dni)){
			address.add(owner);
			owner.saveIt();
		}
		return (findByDni(dni));
	}
	
	public static Owner findByDni(String dni){
		return Owner.findFirst("dni = ?", dni);
	}

	
	public static void deleteOwner(String dni){
		Owner ownerForDelete = findByDni(dni);
		int idAddress = ownerForDelete.getInteger("address_id");
		ownerForDelete.delete();
		if (null==Owner.findFirst("address_id = ?", idAddress)){
			Address add= Address.findById(idAddress);
			add.deleteAddress();
		}
	}
	
	public String getFirstName(){
		return (getString("first_name"));
	}
	
	public String getLastName(){
		return (getString("last_name"));
	}
	
	public String getDni(){
		return (getString("dni"));
	}
	
	public String getEmail(){
		return (getString("email"));
	}
 
}