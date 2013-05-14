package com.unrc.app.models;

import java.util.Iterator;
import java.util.List;

import org.javalite.activejdbc.Model;


public class RealEstate extends Model {
	static{
		//valido que tenga nombre y direccion
        validatePresenceOf("name");
        validatePresenceOf("address_id");
    }

	//Retorno un booleano si dado el nombre de una inmobiliaria existe o no
    public static Boolean existRealEstate(String name){
    	Boolean ret=true;
    	if( first("name = ?",name)==null){
    		return false;
    	}
    	return ret;
    }//end existeRealEstate
    
    //Estoy probando si no hace falta,creo que no hace falta, hasta ahora anda sin esta
  /*  //Crea un RealEstate en la base de datos si es que no existe previamente
  	public static RealEstate createRealEstate(String name,String phone_number,String web_site,String email, Address address){
  		RealEstate realEstate= create("name",name,"phone_number",phone_number,"web_site",web_site, "email", email);
  		if(!existRealEstate(name)){
  			address.add(realEstate);
  			realEstate.saveIt();
  		}
  		return (findByName(name));
  	}//end createRealEstate*/
  	
  //Crea un RealEstate con relacion con owners
  	public static RealEstate createRealEstate(String name,String phone_number,String web_site,String email,Address address,List<String> dnis){
  		RealEstate realEstate= create("name",name,"phone_number",phone_number,"web_site",web_site, "email", email);
  		if(!existRealEstate(name)){
  			address.add(realEstate);
  			Iterator<String> itr = dnis.iterator();
  	  		while (itr.hasNext()){
  	  			String dni = (String)itr.next();
  	  			if (Owner.existOwner(dni)) {
  	  				Owner owner = Owner.findByDni(dni);
  	  	  			realEstate.add(owner);
  	  			}
  	  		}
  			realEstate.saveIt();
  		}
  		return (findByName(name));
  	}//end createRealEstate
  	
  	//Retorna un modelo que se busca en la base de datos por el nombre
  	public static RealEstate findByName(String name){
  		return RealEstate.findFirst("name = ?", name);
  	}//end findByName
  	
  	//Borra una imnobiliaria en caso de que la dirección no se utilice más se borra tambien
  	public static void deleteRealEstate(String name){
		RealEstate realEstateForDelete = findByName(name);
		List<Owner> removeRelation = realEstateForDelete.getAll(Owner.class);
		Iterator<Owner> itr = removeRelation.iterator();
	  		while (itr.hasNext()){
	  			Owner owner = (Owner)itr.next();
	  			realEstateForDelete.remove(owner);
	  		}
		int idAddress = realEstateForDelete.getInteger("address_id");
		realEstateForDelete.delete();
		if (null==Owner.findFirst("address_id = ?", idAddress)&&(null==RealEstate.findFirst("address_id = ?", idAddress))){
			Address add= Address.findById(idAddress);
			add.deleteAddress();
		}
	}//end deleteRealEstate
	
  	//Obtengo el nombre
	public String getName(){
		return (getString("name"));
	}
	
	//Obtengo el numero de telefono
	public String getPhoneNumber(){
		return (getString("phone_number"));
	}//end getPhoneNumber
	
	//Obtengo el sitio web
	public String getWebSite(){
		return (getString("web_site"));
	}//end getWebSite
	
	//Obtengo el email
	public String getEmail(){
		return (getString("email"));
	}//end getEmail
}  