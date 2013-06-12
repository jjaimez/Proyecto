/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */
package com.unrc.app;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.unrc.app.models.Address;
import com.unrc.app.models.Building;
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
	
	/*Creo una inmobiliaria tomando como parametro un ObjectRealEstate, creo la ciudad que es pasada como parametro dentro de realEstate
	 *  y creo la dirección que es pasada como parametro dentro de realEstate, luego creo la inmobiliaria en la base de datos, en caso
	 *  de que exista una inmobiliaria con el mismo nombre no se carga*/
	public void createRealEstate(ObjectRealEstate realEstate){
		City citBD = City.createCity(realEstate.getCity(),realEstate.getCode());
		Address address =Address.createAddress(realEstate.getStreet(), realEstate.getNum(),realEstate.getNeighborhood(),citBD);
		RealEstate.createRealEstate(realEstate.getName(),realEstate.getPhoneNumber(), realEstate.getWebSite(),realEstate.getEmail(), address, realEstate.getOwners());
	}//end createRealEstate
	
	//borro una inmobiliaria
	public void deleteRealEstate(String name){
		RealEstate.deleteRealEstate(name);
	}//end inmobiliaria
	
	/*Obtengo los datos de una inmobiliaria que se encuentra registrada en la base da datos mediante el ingreso de su nombre, primero me fijo 
	 *si la inmobiliaria existe, en caso de no existir retorno null, sino obtengo los datos de el modelo realEstate, luego con
	 *el id_address obtengo el modelo address, y de la misma forma el modelo City y seteo los valores en un ObjectRealEstate para
	 *mas tarde retornarlo*/
	public ObjectRealEstate consultRealEstate(String name){
		RealEstate realEstate= RealEstate.findByName(name);
		return toObject(realEstate);
	}//end consulRealEstates
	
	public static ObjectRealEstate toObject(RealEstate realEstate){
		ObjectRealEstate objectRealEstate = new ObjectRealEstate();
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
	}
//--------------------------MODIFICACIONES------------------------------------------------------	
	
	/*Actualizo el numero de telefono de una inmobiliaria dado su nombre y su nuevo numero
	 *busco si la inmobiliaria existe cargada previamente y en caso de existir la modifico,
	 *sino retorna un mensaje por pantalla que no existe*/
	public void updatePhoneNumber(String name,String phoneNumber){
		RealEstate realEstate= RealEstate.findByName(name);
		if (realEstate!=null){
			realEstate.setPhoneNumber(phoneNumber);
		}
		else{
			System.out.println("La inmobiliaria "+name+" no se encuentra registrada");
		}
	}//end updatePhoneNumber
	
	/*Actualizo el sitio web de una inmobiliaria dado su nombre y su nuevo sitio web
	 * busco si la inmobiliaria existe cargada previamente y en caso de existir la modifico,
	 * sino retorna un mensaje por pantalla que no existe*/
	public void updateWebSite(String name,String webSite){
		RealEstate realEstate= RealEstate.findByName(name);
		if (realEstate!=null){
			realEstate.setWebSite(webSite);
		}
		else{
			System.out.println("La inmobiliaria "+name+" no se encuentra registrada");
		}
	}//end updteWebSite
	
	/*Actualizo el email de una inmobiliaria dado su nombre y su nuevo sitio web, busco 
	 * si la inmobiliaria existe cargada previamente, y en caso de existir la modifico, sino
	 * retorna un mensaje por pantalla que no existe*/
	public void updateEmail(String name,String email){
		RealEstate realEstate= RealEstate.findByName(name);
		if (realEstate!=null){
			realEstate.setEmail(email);
		}
		else{
			System.out.println("La inmobiliaria "+name+" no se encuentra registrada");
		}
	}//end updateEmail
	
	/*Actualizo una dirección que se encuentra en la misma ciudad de antes, dado el nombre de la inmobiliaria
	 *que se quiere modificar la direccion, busco en la base de datos la inmobiliaria, si existe, 
	 *obtengo la direccion vieja y busco si existe previamente la direccion nueva en la base de datos, 
	 *en caso de existir relaciono esta direccion con la inmobiliaria, y sino, la creo y la relaciono,
	 * luego corroboro que no use nadie más la direccion vieja y de ser así, será borrada*/
	public void updateAddress(String name,String street,String num, String neighborhood){
		RealEstate realEstate=RealEstate.findByName(name);
		if (realEstate!=null){
			Address oldAddress= Address.findById(realEstate.getAddressId());
			Address newAddress= Address.findByAddress(street, num, oldAddress.getCityId());
			City city= City.findById(oldAddress.getCityId());
			if (newAddress==null){
				newAddress=new Address();
				newAddress.setStreet(street);
				newAddress.setNum(num);
				newAddress.setNeighborhood(neighborhood);
				city.add(newAddress);
				newAddress.saveIt();
			}
			newAddress.add(realEstate);
			oldAddress.deleteAddress();
		}
		else{
			System.out.println("La inmobiliaria "+name+" no se encuentra registrada");
		}	
	}//end updateAddress
	
	/*Actualizo una dirección incluida la ciudad, dado el nombre de la inmobiliaria que se quiere
	 * modificar la direccion, busco en la base de datos la inmobiliaria, si existe, obtengo la direccion 
	 * vieja y busco si existe previamente la ciudad, de no existir se crea, busco la direccion nueva en la base 
	 * de datos, en caso de existir relaciono esta direccion con la inmobiliaria, y sino, la creo y la relaciono, 
	 * luego corroboro que no use nadie más la direccion vieja y de ser así, será borrada*/
	public void updateAddress(String name, String nameCity,int code, String street, String num,String neighborhood){
		RealEstate realEstate=RealEstate.findByName(name);
		if (realEstate!=null){
			City city= City.findByCode(code);
			if(city==null){
				city= City.createCity(nameCity, code);
			}	
			Address oldAddress= Address.findById(realEstate.getAddressId());
			Address newAddress= Address.findByAddress(street, num, city.getInteger("id"));
			if (newAddress==null){
				newAddress=new Address();
				newAddress.setStreet(street);
				newAddress.setNum(num);
				newAddress.setNeighborhood(neighborhood);
				city.add(newAddress);
				newAddress.saveIt();
			}	
			newAddress.add(realEstate);
			oldAddress.deleteAddress();
		}
		else{
			System.out.println("La inmobiliaria "+name+" no se encuentra registrada");
		}	
	}//end updateAddress
	
	/*Se agregan el o los dueños a una sola inmobiliaria, pasandole los dni, corroboro que exista la inmobiliaria
	 *en caso de existir obtengo todos los dni de los dueños pasados como parametro, y si el dueño existe lo relaciono */
	public void addOwners(String name,String...owners){
		RealEstate realEstate = RealEstate.findByName(name);
		if(realEstate!=null){
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
	}//end addOwners
	
	/*Elimina el o los dueños que tiene relacionado una inmobiliaria pasandole los dni de los mismos, primero
	 * busco si existe la inmobiliaria, luego obtengo los dni de los dueños, si el dueño tiene relacion
	 * con la inmobiliaria entonces se borra la relacion*/
	public void removeOwners(String name,String...owners){
		RealEstate realEstate = RealEstate.findByName(name);
		if(realEstate!=null){
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
	}//end removeOwners
	
	//Lista de los Buildings asociados
	public LinkedList<Building> getBuildings(String name){
		RealEstate realEstate = RealEstate.findByName(name);
		if(realEstate!=null){
			return realEstate.getBuildings();
		}
		else{
			return null;
		}
	}
	
	//imprime por pantalla una inmobiliaria
	public void printRealEstate(String name){
		if (RealEstate.existRealEstate(name)){
			System.out.println(consultRealEstate(name).toString());
		}
		else{
			System.out.println("La inmobiliaria "+name+ " no se encuentra registrada" );
		}
	}//end printRealEstates
}//end AMBRealEstate