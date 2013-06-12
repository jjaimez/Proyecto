/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */

package com.unrc.app;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.unrc.app.models.Address;
import com.unrc.app.models.City;
import com.unrc.app.models.Owner;
import com.unrc.app.models.RealEstate;
import com.unrc.app.object.ObjectOwner;


public class ABMOwner {

	public ABMOwner() {
		// TODO Auto-generated constructor stub
	}

	/*Creo un dueño tomando como parametro un ObjectOwner, creo la ciudad que es pasada como parametro dentro de owner y creo la dirección
	 * que es pasada como parametro dentro de owner, luego creo el dueño en la base de datos, en caso de existir un dueño con el mismo dni
	 * no se cargará*/
	public void createOwner(ObjectOwner owner){
		City citBD = City.createCity(owner.getCity(),owner.getCode());
		Address address =Address.createAddress(owner.getStreet(), owner.getNum(),owner.getNeighborhood(),citBD);
		Owner.createOwner(owner.getFirstName(), owner.getLastName(),owner.getDni(),owner.getEmail(), address,owner.getRealEstates());
	}//end createOwner
	
	/*Borro un dueño dado su dni*/
	public void deleteOwner(String dni){
		Owner.deleteOwner(dni);
	}
	
	/*Obtengo los datos de un dueño que se encuentra registrado en la base da datos mediante el ingreso de su dni, primero me fijo si el 
	 * dueño existe, en caso de no existir retorno null, sino obtengo los datos de el modelo Owner, luego con el id_address obtengo
	 * el modelo address, y de la misma forma el modelo City y seteo los valores en un ObjectOwner para mas tarde retornarlo*/
	public ObjectOwner consultOwner(String dni){
		Owner owner= Owner.findByDni(dni);
		return toObject(owner);
	}//end consulOwner
	
	
	public static ObjectOwner toObject(Owner owner){
		ObjectOwner objectOwner = new ObjectOwner();
		if (owner!=null){
			objectOwner.setFirstName(owner.getFirstName());
			objectOwner.setLastName(owner.getLastName());
			objectOwner.setDni(owner.getDni());
			objectOwner.setEmail(owner.getEmail());
			Address address= owner.parent(Address.class);
			objectOwner.setStreet(address.getStreet());
			objectOwner.setNum(address.getNum());
			objectOwner.setNeighborhood(address.getNeighborhood());
			City city= address.parent(City.class);
			objectOwner.setCity(city.getName());
			objectOwner.setCode(city.getCode());
			objectOwner.setRealEstates(owner.getRealEstates());
			return objectOwner;
		}
		else{
			return null;
		}
	}
//--------------------------MODIFICACIONES------------------------------------------------------	
	
	/*Modifico el nombre de un dueño dado su dni y su nuevo nombre, busco si el dueño existe cargado previamente
	 * y en caso de existir lo modifico,  sino retorna un mensaje por pantalla que no existe*/
	public void updateFirstName(String dni, String firstName){
		Owner owner=Owner.findByDni(dni);
		if (owner!=null){
			owner.setFirstName(firstName);
		}
		else{
			System.out.println("El dueño con dni "+dni+" no se encuentra registrado");
		}
	}//end updateFirstName
	
	/*Modifico el apellido de un dueño dado su dni y su nuevo apellido, busco si el dueño existe cargado
	 * previamente, en caso de existir lo modifico, sino retorna un mensaje por pantalla que no existe*/
	public void updateLastName(String dni, String lastName){
		Owner owner=Owner.findByDni(dni);
		if (owner!=null){
			owner.setLastName(lastName);
		}
		else{
			System.out.println("El dueño con dni "+dni+" no se encuentra registrado");
		}	
	}//End updateLastName
	
	/*Modifico el mail de un dueño dado su dni y su nuevo email, busco si el dueño existe cargado
	 * previamente, en caso de existir lo modifico, sino retorna un mensaje por pantalla que no existe*/
	public void updateEmail(String dni, String email){
		Owner owner=Owner.findByDni(dni);
		if (owner!=null){
			owner.setEmail(email);
		}
		else{
			System.out.println("El dueño con dni "+dni+" no se encuentra registrado");
		}	
	}//end updateEmail
	
	/*Actualizo una dirección que se encuentra en la misma ciudad de antes, dado el dni del dueño al que se quiere
	 * modificar la direccion, busco en la base de datos el dueño, si existe, obtengo la direccion vieja y 
	 * busco si existe previamente la direccion nueva en la base de datos, en caso de existir relaciono 
	 * esta direccion con el dueño, y sino, la creo y la relaciono, luego corroboro que no use nadie más
	 * la direccion vieja y de ser así, será borrada*/
	public void updateAddress(String dni,String street,String num, String neighborhood){
		Owner owner=Owner.findByDni(dni);
		if (owner!=null){
			Address oldAddress= Address.findById(owner.getAddressId());
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
			newAddress.add(owner);
			oldAddress.deleteAddress();
		}
		else{
			System.out.println("El dueño con dni "+dni+" no se encuentra registrado");
		}	
	}//end updateAddress
	
	/*Actualizo una dirección incluida la ciudad, dado el dni del dueño al que se quiere
	 * modificar la direccion, busco en la base de datos el dueño, si existe, obtengo la direccion vieja y 
	 * busco si existe previamente la ciudad, de no existir se crea, busco la direccion nueva en la base 
	 * de datos, en caso de existir relaciono esta direccion con el dueño, y sino, la creo y la relaciono, 
	 * luego corroboro que no use nadie más la direccion vieja y de ser así, será borrada*/
	public void updateAddress(String dni, String nameCity,int code, String street, String num,String neighborhood){
		Owner owner=Owner.findByDni(dni);
		if (owner!=null){
			City city= City.findByCode(code);
			if(city==null){
				city= City.createCity(nameCity, code);
			}	
			Address oldAddress= Address.findById(owner.getAddressId());
			Address newAddress= Address.findByAddress(street, num, city.getInteger("id"));
			if (newAddress==null){
				newAddress=new Address();
				newAddress.setStreet(street);
				newAddress.setNum(num);
				newAddress.setNeighborhood(neighborhood);
				city.add(newAddress);
				newAddress.saveIt();
			}
			newAddress.add(owner);
			oldAddress.deleteAddress();
		}
		else{
			System.out.println("El dueño con dni "+dni+" no se encuentra registrado");
		}	
	}//End updateAddress
	
	
	/*Se agregan la o las inmobiliarias a un solo dueño, pasandole los nombres, corroboro que exista el dueño,
	 * en caso de existir obtengo todos los nombres de las inmobiliarias, y si la inmobiliaria existe la relaciono */
	public void addRealEstates(String dni,String...realEstates){
		Owner owner = Owner.findByDni(dni);
		if (owner!=null){
			List<RealEstate> realEstatesContains = owner.getAll(RealEstate.class);
			List<String> list = Arrays.asList(realEstates);
			Iterator<String> itr = list.iterator();
	  		while (itr.hasNext()){
	  			String name = (String)itr.next();
	  			if(RealEstate.existRealEstate(name)){
	  				if(!realEstatesContains.contains(RealEstate.findByName(name))){
	  					RealEstate realEstate = RealEstate.findByName(name);
	  	  	  			owner.add(realEstate);
	  				}
	  			}
	  		}
		}
	}//end addRealEstates
	
	/*Elimina la o las inmobiliarias que tiene relacionado un dueño pasandole los nombres de las mismas, primero
	 * busco si existe el dueño, luego obtengo los nombres de las inmobiliarias, si la inmobiliaria tiene relacion
	 * con el dueño entonces se borra la relacion*/
	public void removeRealEstates(String dni,String...realEstates){
		Owner owner = Owner.findByDni(dni);
		if (owner!=null){
			List<RealEstate> realEstatesContains = owner.getAll(RealEstate.class);
			List<String> list = Arrays.asList(realEstates);
			Iterator<String> itr = list.iterator();
		  	while (itr.hasNext()){
		  		String name = (String)itr.next();
		  		if(RealEstate.existRealEstate(name)){
		  			if(!realEstatesContains.contains(RealEstate.findByName(name))){
		  				RealEstate realEstate = RealEstate.findByName(name);
		  				owner.remove(realEstate);
		  			}	
		  		}
		  	}
		}
	}//end removeRealEstates
	
	//Imprime por pantalla un dueño 
	public void printOwner(String dni){
		if (Owner.existOwner(dni)){
			System.out.println(consultOwner(dni).toString());
		}
		else{
			System.out.println("El dueño con dni "+dni+ " no se encuentra registrado" );
		}
	}
}
