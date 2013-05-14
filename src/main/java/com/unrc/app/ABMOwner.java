package com.unrc.app;

import com.unrc.app.models.Address;
import com.unrc.app.models.City;
import com.unrc.app.models.Owner;
import com.unrc.app.object.ObjectOwner;


public class ABMOwner {

	public ABMOwner() {
		// TODO Auto-generated constructor stub
	}

	//Creo un dueño
	public void createOwner(ObjectOwner owner){
		City citBD = City.createCity(owner.getCity(),owner.getCode());
		Address address =Address.createAddress(owner.getStreet(), owner.getNum(),owner.getNeighborhood(),citBD);
		Owner.createOwner(owner.getFirstName(), owner.getLastName(),owner.getDni(),owner.getEmail(), address,owner.getRealEstates());
	}//end createOwner
	
	//Borro un dueño
	public void deleteOwner(String dni){
		Owner.deleteOwner(dni);
	}
	
	//Obtengo los datos de un dueño que se encuentra en la base de datos
	public ObjectOwner consultOwner(String dni){
		ObjectOwner objectOwner = new ObjectOwner();
		Owner owner= Owner.findByDni(dni);
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
			return objectOwner;
		}
		else{
			return null;
		}
	}//end consulOwner
	
//-------------------MODIFICACIONES------------------------------------------------------	
	public void updateOwner(String dni,String firstName, String lastName, String email,String nameCity, int code, String street, String num, String neighborhood){	
		Owner owner= Owner.findByDni(dni);
		if (owner!=null){
			owner.setFirstName(firstName);
			owner.setLastName(lastName);
			owner.setEmail(email);
			City city= City.findByCode(code);
			if(city==null){
				city= City.createCity(nameCity, code);
			}
			Address address= Address.findByAddress(street, num, city.getInteger("id"));
			if (address==null){
				int idAddress = owner.getInteger("address_id");
				address=new Address();
				address.setStreet(street);
				address.setNum(num);
				address.setNeighborhood(neighborhood);
				city.add(address);
				address.saveIt();
				address.add(owner);
				if (null==Owner.findFirst("address_id = ?", idAddress)){
					Address add= Address.findById(idAddress);
					System.out.println("dasdasd"+idAddress);
					add.deleteAddress();
				}
			}
		}
		else{
			System.out.println("El dueño con dni "+dni+" no se encuentra registrado");
		}
	}//end updateOwner
}
