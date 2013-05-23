/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */

package com.unrc.app.models;

import org.javalite.activejdbc.Model;


public class Building extends Model {
	static{
		//valido que el price,addres_id,type, offer sean obligatorio
		validatePresenceOf("price");
		validatePresenceOf("address_id");
		validatePresenceOf("type");
		validatePresenceOf("offer");
		//validatePresenceOf("owner_id"); //No puedo validar ya que el metodo add me guarda el modelo al agregar
		//validatePresenceOf("real_estate_id"); //la dirección y me tira error
	}
	
	//Retorna un booleano diciendo si existe en la base de datos el modelo dado
	public static Boolean existBuilding(String street, String num, int city_id){
		Address add = Address.findByAddress(street, num, city_id);
		if (add!=null){
			int addId=add.getInteger("id");
			return(null!=Building.findFirst("address_id= ?", addId));
		}	
		else{
			return false;
		}
	}//end existBuilding
	
	//Retorno el modelo de una Direccion que se busca en la BD el identificador del address
	public static Building findByBuilding(String street, String num, int city_id){
		Address add = Address.findByAddress(street, num, city_id);
		if (add!=null){
			int addId=add.getInteger("id");
			return Building.findFirst("address_id= ?", addId);
		}
		else{
			return null;
		}
	}//end findByAddress
	
	//crea un edificio
	public static Building createBuilding(Address address,String description,String price,String offer,String type,Owner owner, RealEstate realEstate){
  		Building building= create("description",description,"price",price,"offer",offer.toString(), "type", type.toString());
  		if(!existBuilding(address.getStreet(),address.getNum(),address.getCityId())){
  			address.add(building);
  			owner.add(building);
  			realEstate.add(building);
  			building.saveIt();
  		}
  		return (findByBuilding(address.getStreet(),address.getNum(),address.getCityId()));
  	}//end 
	
	//Buscar error 
	//Borro un edificio de la base de datos y si la dirección no la utiliza nadie la borra de la base de datos
	public static boolean deleteBuilding(String street, String num, int idCity){
		if (existBuilding(street, num, idCity)){
			Building buildingForDelete= Building.findByBuilding(street, num, idCity);
			int adId = buildingForDelete.getInteger("address_id");
			buildingForDelete.delete();
			Address address= Address.findById(adId);
			address.deleteAddress();
			return true;
		}
		else{
			return false;
		}
	}//end 

	//Obtengo el precio
	public String getPrice(){
		return (getString("price"));
	}//end getPrice
	
	public String getDescription(){
		return (getString("description"));
	}//end getDescription
	
	//Obtengo la oferta
	public String getOffer(){
		return(getString("offer"));
	}//end 
	
	
	//Obtengo el tipo
	public String getType(){
		return(getString("type"));
	}//end 
		
	//seteo el precio
	public void setPrice(String price){
		set("price", price);
		saveIt();
	}//end setPrice

	//Seteo la oferta
	public void setOffer(String offer){
		set("offer", offer);
		saveIt();
	}//end 
		
	//Seteo el tipo 
	public void setType(String type){
		saveIt();
	}//end 
		
	public void setOwner(int idOwner){
		set("owner_id", idOwner);
		saveIt();
	}//end 
	
	//Seteo RealEstate Asociado
	public void setRealEstate(int idRealEstate){
		set("real_estate_id", idRealEstate);
		saveIt();
	}//end 
	
	//Setro la descripcion
	public void setDescription(String description){
		set("description", description);
		saveIt();	
	}
}
