/**
 * 
 */
package com.unrc.app;

import com.unrc.app.models.Address;
import com.unrc.app.models.City;
import com.unrc.app.models.RealEstate;
import com.unrc.app.object.ObjectRealEstate;

/**
 * @author jacinto
 *	
 */
public class AMBRealEstate {

	
	public AMBRealEstate() {
		// TODO Auto-generated constructor stub
	}
	

	public void createRealEstate(ObjectRealEstate realEstate){
		City citBD = City.createCity(realEstate.getCity(),realEstate.getCode());
		Address address =Address.CreateAddress(realEstate.getStreet(), realEstate.getNum(),realEstate.getNeighborhood(),citBD);
		RealEstate.createRealEstate(realEstate.getName(),realEstate.getPhoneNumber(), realEstate.getWebSite(),realEstate.getEmail(), address, realEstate.getOwners());
	}
	
	public void deleteRealEstate(String name){
		RealEstate.deleteRealEstate(name);
	}
	
	public void ConsultRealEstate(String name){
		ObjectRealEstate objectRealEstate = new ObjectRealEstate();
		RealEstate realEstate= RealEstate.findByName(name);
		if (realEstate!=null){
			objectRealEstate.setName(realEstate.getName());
			objectRealEstate.setPhoneNumber(realEstate.getPhoneNumber());
			objectRealEstate.setWebSite(realEstate.getWebSite());
			objectRealEstate.setEmail(realEstate.getEmail());
			Address address= realEstate.parent(Address.class);
			objectRealEstate.setStreet(address.getStreet());
			objectRealEstate.setNum(address.getNum());
			objectRealEstate.setNeighborhood(address.getNeighborhood());
			City city= address.parent(City.class);
			objectRealEstate.setCity(city.getName());
			objectRealEstate.setCode(city.getCode());
			System.out.println(objectRealEstate.toString());
		}
		else{
			System.out.println("la inmobiliaria con nombre:"+name+" no se encuentra registrado");
		}
	}
	

}