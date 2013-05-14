package com.unrc.app.models;

import java.util.Iterator;
import java.util.List;

import org.javalite.activejdbc.Model;

public class Owner extends Model {
	
	static{
		//valido que el first_name,last_name, dni, address_id sean obligatorio
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
	}//end existOwner
  
	//crea un owner 
	public static Owner createOwner(String firstName,String lastName, String dni,String email, Address address,List<String> realEstates){
		Owner owner= create("first_name",firstName,"last_name",lastName,"dni",dni, "email", email);
		if(!existOwner(dni)){
			address.add(owner);
			Iterator<String> itr = realEstates.iterator();
  	  		while (itr.hasNext()){
  	  			String name = (String)itr.next();
  	  			if(RealEstate.existRealEstate(name)){
  	  				RealEstate realEstate = RealEstate.findByName(name);
  	  	  	  		owner.add(realEstate);	
  	  			}
  	  		}
			owner.saveIt();
		}
		return (findByDni(dni));
	}//end createOwner
	
	//Busco si existe un dueño con dni especificado y retorno el modelo
	public static Owner findByDni(String dni){
		return Owner.findFirst("dni = ?", dni);
	}//end findByDni
	
	//Borro un dueño de la base de datos y si la dirección no la utiliza nadie la borra de la base de datos
	public static void deleteOwner(String dni){
		Owner ownerForDelete = findByDni(dni);
		List<RealEstate> removeRelation = ownerForDelete.getAll(RealEstate.class);
		Iterator<RealEstate> itr = removeRelation.iterator();
	  		while (itr.hasNext()){
	  			RealEstate realEstate = (RealEstate)itr.next();
	  			ownerForDelete.remove(realEstate);
	  		}
		int idAddress = ownerForDelete.getInteger("address_id");
		ownerForDelete.delete();
		if (null==Owner.findFirst("address_id = ?", idAddress)&&(null==RealEstate.findFirst("address_id = ?", idAddress))){
			Address add= Address.findById(idAddress);
			add.deleteAddress();
		}
	}//end deleteOwner
	
	
	//Obtengo el firstName de un modelo Owner
	public String getFirstName(){
		return (getString("first_name"));
	}//end getFirstName
	
	//Obtengo el lastName de un modelo Owner
	public String getLastName(){
		return (getString("last_name"));
	}//end getLastName
	
	//Obtengo el dni de un modelo Owner
	public String getDni(){
		return (getString("dni"));
	}//end getDni
	
	//Obtengo el email de un modelo Owner
	public String getEmail(){
		return (getString("email"));
	}//end getEmail
	
	//seteo firstName
	public void setFirstName(String firstName){
		set("first_name", firstName);
		saveIt();
	}//end setFirstName

	//Seteo lastName
	public void setLastName(String lastName){
		set("last_name", lastName);
		saveIt();
	}//end setLastName
	
	//Seteo email 
	public void setEmail(String email){
		set("email", email);
		saveIt();
	}//end setEmail
}//end Owner