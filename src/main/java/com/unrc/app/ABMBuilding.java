/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */

package com.unrc.app;

import com.unrc.app.enumerado.Offer;
import com.unrc.app.enumerado.Type;
import com.unrc.app.models.Address;
import com.unrc.app.models.Building;
import com.unrc.app.models.City;
import com.unrc.app.models.Owner;
import com.unrc.app.models.RealEstate;
import com.unrc.app.object.ObjectBuilding;




public class ABMBuilding {

	public ABMBuilding() {
		// TODO Auto-generated constructor stub
	}

	//Creo un edificio, con direccion y ciudad
	public void createBuilding(ObjectBuilding building){
		Owner owner= Owner.findByDni(building.getDniOwner());
		RealEstate realEstate= RealEstate.findByName(building.getNameRealEstate());
		if (owner!=null && realEstate!=null){
			City citBD = City.createCity(building.getCity(),building.getCode());
			Address address =Address.createAddress(building.getStreet(), building.getNum(),building.getNeighborhood(),citBD);
			Building.createBuilding(address,building.getDescription(), building.getPrice(),building.getOffer(), building.getType(),owner, realEstate);
		}
		else{
			System.out.println("No existe el dueño o inmobiliaria");
		}
	}//end createBuilding

	
	//Borro edificio
			public void deleteBuilding(String street, String num, int code){
				int idCity= City.findByCode(code).getInteger("id");
				Building.deleteBuilding(street, num,idCity );
	}//end deleteBuilding
	
	
//-------------------MODIFICACIONES------------------------------------------------------	

		
		//Modifico la oferta de un inmueble
		public void updateOffer(String street, String num, int code, Offer newOffer){
			int idCity= City.findByCode(code).getInteger("id"); // saco el id de la ciudad
			Building building= Building.findByBuilding(street, num, idCity);
			if (building!=null){
				building.setOffer(newOffer);
			}
			else{
				System.out.println("El inmueble con los detalles especificados no se encuentra archivado");
			}
		}//end updateOffer

		//Modifico el tipo de un inmueble
		public void updateType(String street, String num, int code, Type newType){
			int idCity= City.findByCode(code).getInteger("id"); // saco el id de la ciudad
			Building building= Building.findByBuilding(street, num, idCity);
			if (building!=null){
				building.setType(newType);
			}
			else{
				System.out.println("El inmueble con los detalles especificados no se encuentra archivado");
			}	
		}//End updateType
		
	//Modifico el precio de un inmueble
	public void updatePrice(String street, String num, int code, String newPrice){
		int idCity= City.findByCode(code).getInteger("id"); // saco el id de la ciudad
		Building building= Building.findByBuilding(street, num, idCity);
		if (building!=null){
			building.setPrice(newPrice);
		}
		else{
			System.out.println("El inmueble con los detalles especificados no se encuentra archivado");
		}	
		}//End updateType
		
	//Modifico el dueño de un inmueble
	public void updateOwner(String street, String num, int code,String dni){
		Owner owner= Owner.findByDni(dni);
		int idCity= City.findByCode(code).getInteger("id"); // saco el id de la ciudad
		Building building= Building.findByBuilding(street, num, idCity);
		if((owner!=null)&&(building!=null)){
			building.setOwner(owner.getInteger("id"));
			
		}
		else{
			System.out.println("El dueño o el inmueble no se encuentra registrado");
		}
	}
	
	//Modifico la inmobiliaria de un inmueble
	public void updateRealEstate(String street, String num, int code,String name){
		RealEstate realEstate= RealEstate.findByName(name);
		int idCity= City.findByCode(code).getInteger("id"); // saco el id de la ciudad
		Building building= Building.findByBuilding(street, num, idCity);
		if((realEstate!=null)&&(building!=null)){
			building.setRealEstate(realEstate.getInteger("id"));
			
		}
		else{
			System.out.println("El dueño o el inmueble no se encuentra registrado");
		}
	}
	//Modifico la descripcion de un inmueble
	public void updateDescription(String street, String num, int code,String description){
		int idCity= City.findByCode(code).getInteger("id"); // saco el id de la ciudad
		Building building= Building.findByBuilding(street, num, idCity);
		if(building!=null){
			building.setDescription(description);
			
		}
		else{
			System.out.println("El dueño o el inmueble no se encuentra registrado");
		}
	}
		
		
}
