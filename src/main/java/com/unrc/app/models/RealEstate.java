package com.unrc.app.models;
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
  	
  	public static RealEstate findByName(String name){
  		return RealEstate.findFirst("name = ?", name);
  	}
  	
  	public static void deleteRealEstate(String name){
		RealEstate realEstateForDelete = findByName(name);
		int idAddress = realEstateForDelete.getInteger("address_id");
		realEstateForDelete.delete();
		if (null==Owner.findFirst("address_id = ?", idAddress)){
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