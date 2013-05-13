package com.unrc.app.models;

import java.util.Iterator;
import java.util.List;

import org.javalite.activejdbc.Model;


public class RealEstate extends Model {
	static{
        validatePresenceOf("name");
        validatePresenceOf("address_id");
    }

	
    public static Boolean existRealEstate(String name){
    	Boolean ret=true;
    	if( first("name = ?",name)==null){
    		return false;
    	}
    	return ret;
    }
    
  //Crea un RealEstate en la base de datos si es que no existe previamente
  	public static RealEstate createRealEstate(String name,String phone_number,String web_site,String email, Address address){
  		RealEstate realEstate= create("name",name,"phone_number",phone_number,"web_site",web_site, "email", email);
  		if(!existRealEstate(name)){
  			address.add(realEstate);
  			realEstate.saveIt();
  		}
  		return (findByName(name));
  	}
  	
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
  	}
  	
  	public static RealEstate findByName(String name){
  		return RealEstate.findFirst("name = ?", name);
  	}
  	
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
		if (null==RealEstate.findFirst("address_id = ?", idAddress)){
			Address add= Address.findById(idAddress);
			add.deleteAddress();
		}
	}
	
	public String getName(){
		return (getString("name"));
	}
	
	public String getPhoneNumber(){
		return (getString("phone_number"));
	}
	
	public String getWebSite(){
		return (getString("web_site"));
	}
	
	public String getEmail(){
		return (getString("email"));
	}
	   
}  