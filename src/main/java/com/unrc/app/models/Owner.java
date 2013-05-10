package com.unrc.app.models;

import org.javalite.activejdbc.Model;

public class Owner extends Model {
	private String firstName;
	private String lastName;
	private String dni;
	private String email;
	private int addressId;
	
	static{
		validatePresenceOf("first_name", "last_name","dni","address_id");
	}
  
	/**
    * 
 	*/
	public Owner() {
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param dni
	 * @param email
	 * @param addressId
	 */
	public Owner(String firstName, String lastName, String dni, String email,int addressId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.email = email;
		this.addressId = addressId;
	}

	//Retorna un booleano diciendo si existe en la base de datos el modelo dado
	public Boolean existOwner(){
		Boolean ret=true;
		if( first("dni = ?" ,dni)==null){
			return false;
		}
		return ret;
	}
  
	//Crea un owner en la base de datos si es que no existe previamente
	public void createOwner(){
      set("first_name",firstName,"last_name",lastName,"dni",dni,"address_id",addressId,"email",email);
      if(!existOwner()){
    	  saveIt();
      }
  }
	//Retorna el modelo de la base de datos correspondiente
	public Owner returnModel(){
		return Owner.first("dni = ?",dni);
	}
  
	//Retorna el id de un modelo
	public Integer returnId(){
		if (existOwner()){
			return returnModel().getInteger("id");
		}
		else{
			return -1;
		}
	}
	
	public void deleteOwner(String dni){
		this.dni=dni;
		Owner ownerForDelete = returnModel();
		int idAddress = ownerForDelete.getInteger("address_id");
		ownerForDelete.delete();
		if (null==Owner.findFirst("address_id = ?", idAddress)){
			Address add= Address.findById(idAddress);
			add.deleteAddress();
		}

	}
}