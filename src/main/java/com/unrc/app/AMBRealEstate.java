/**
 * 
 */
package com.unrc.app;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.unrc.app.models.Address;
import com.unrc.app.models.City;
import com.unrc.app.models.Owner;
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
	
	//Creo una inmobiliaria
	public void createRealEstate(ObjectRealEstate realEstate){
		City citBD = City.createCity(realEstate.getCity(),realEstate.getCode());
		Address address =Address.createAddress(realEstate.getStreet(), realEstate.getNum(),realEstate.getNeighborhood(),citBD);
		RealEstate.createRealEstate(realEstate.getName(),realEstate.getPhoneNumber(), realEstate.getWebSite(),realEstate.getEmail(), address, realEstate.getOwners());
	}//end createRealEstate
	
	//borro una inmobiliaria
	public void deleteRealEstate(String name){
		RealEstate.deleteRealEstate(name);
	}//end inmobiliaria
	
	//Obtengo los datos de una inmobiliaria almacenada en la base de datos
		public ObjectRealEstate consultRealEstate(String name){
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
				objectRealEstate.setOwners(realEstate.getOwners());
				return objectRealEstate;
			}
			else{
				return null;
			}
		}//end consulRealEstates
	
	//Actualizo los datos de una inmobiliaria
	public void updateRealEstate(String name,String phoneNumber,String webSite,String email,String nameCity, int code, String street, String num, String neighborhood){
		RealEstate realEstate= RealEstate.findByName(name);
		if (realEstate!=null){
			realEstate.setName(name);
			realEstate.setPhoneNumber(phoneNumber);
			realEstate.setWebSite(webSite);
			realEstate.setEmail(email);
			City city= City.findByCode(code);
			if(city==null){
				city= City.createCity(nameCity, code);
			}
			Address address= Address.findByAddress(street, num, city.getInteger("id"));
			if (address==null){
				int idAddress = realEstate.getInteger("address_id");
				address=new Address();
				address.setStreet(street);
				address.setNum(num);
				address.setNeighborhood(neighborhood);
				city.add(address);
				address.saveIt();
				address.add(realEstate);
				if (null==Owner.findFirst("address_id = ?", idAddress)&&(null==RealEstate.findFirst("address_id = ?", idAddress))){
					Address add= Address.findById(idAddress);
					System.out.println("dasdasd"+idAddress);
					add.deleteAddress();
				}
			}
		}
		else{
			System.out.println("La inmobiliaria "+name+" no se encuentra registrada");
		}
	}//end updateRealEstate

	//Agregar Owners de Real Estate
	public void addRealEstate(String name,String...owners){
		RealEstate realEstate = RealEstate.findByName(name);
		List<Owner> ownersContains = realEstate.getAll(Owner.class);
		List<String> list = Arrays.asList(owners);
		Iterator<String> itr = list.iterator();
	  		while (itr.hasNext()){
	  			String dni = (String)itr.next();
	  			if(Owner.existOwner(dni)){
	  				if(!ownersContains.contains(Owner.findByDni(dni))){
	  					Owner owner = Owner.findByDni(dni);
	  	  	  			realEstate.add(owner);
	  				}
	  			}
	  		}
	}
	
	//Elimina Owners de Real Estate
	public void removeRealEstates(String name,String...owners){
		RealEstate realEstate = RealEstate.findByName(name);
		List<Owner> ownersContains = realEstate.getAll(Owner.class);
		List<String> list = Arrays.asList(owners);
		Iterator<String> itr = list.iterator();
	  		while (itr.hasNext()){
	  			String dni = (String)itr.next();
	  			if(Owner.existOwner(dni)){
	  				if(!ownersContains.contains(Owner.findByDni(dni))){
	  					Owner owner = Owner.findByDni(dni);
	  					realEstate.remove(owner);
	  				}	
	  			}
	  		}
	}

	public void printRealEstate(String name){
		if (RealEstate.existRealEstate(name)){
			System.out.println(consultRealEstate(name).toString());
		}
		else{
			System.out.println("La inmobiliaria "+name+ " no se encuentra registrada" );
		}
	}
}