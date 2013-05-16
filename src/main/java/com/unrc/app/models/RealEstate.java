package com.unrc.app.models;

import java.util.Iterator;
import java.util.LinkedList;
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
  	
  /*Borra una imnobiliaria en caso de que la dirección no se utilice más
   *  se borra tambien, borramos todas las relaciones de esta inmobiliaria con
   *  los Owners y con Building */
  	public static void deleteRealEstate(String name){
  		if(existRealEstate(name)){
  			RealEstate realEstateForDelete = findByName(name);
  			List<Owner> removeRelation = realEstateForDelete.getAll(Owner.class);
  			Iterator<Owner> itr = removeRelation.iterator();
  				while (itr.hasNext()){
  					Owner owner = (Owner)itr.next();
  					realEstateForDelete.remove(owner);
  				}
  			List<Building> removeRelationB = realEstateForDelete.getAll(Building.class);
  			Iterator<Building> itrb = removeRelationB.iterator();
				while (itrb.hasNext()){
					Building building = (Building)itrb.next();
					realEstateForDelete.remove(building);
				}
  			int idAddress = realEstateForDelete.getInteger("address_id");
  			realEstateForDelete.delete();
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
	
	public int getAddressId(){
		return getInteger("address_id");
	}
	
	//Obtengo la lista de dueños que tiene la inmobiliaria
		public LinkedList<String> getOwners(){
			Iterator<Owner> owners=getAll(Owner.class).iterator();
			LinkedList<String> dnis = new LinkedList<String>();
			while (owners.hasNext()){
				dnis.add(owners.next().getDni());
			}
			return dnis;
			
		}
		
	//Obtengo la lista de dueños que tiene la inmobiliaria
		public LinkedList<Building> getBuildings(){
			Iterator<Building> itrb= getAll(Building.class).iterator();
			LinkedList<Building> buildings = new LinkedList<Building>();
			while (itrb.hasNext()){
				buildings.add(itrb.next());
			}
			return buildings;
		}	
	
	//Seteo name
	public void setName(String name){
		set("name", name);
		saveIt();
	}//end setName
	
	//Seteo el telefono
	public void setPhoneNumber(String phoneNumber){
		set("phone_number", phoneNumber);
		saveIt();
	}//end setPhoneNumber
	
	//Seteo el telefono
	public void setWebSite(String webSite){
		set("web_site", webSite);
		saveIt();
	}//end setPhoneNumber
	
	//Seteo email 
	public void setEmail(String email){
		set("email", email);
		saveIt();
	}//end setEmail
	
	
}  