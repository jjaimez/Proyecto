package com.unrc.app;

import com.unrc.app.models.Address;
import com.unrc.app.models.City;
import com.unrc.app.models.Owner;
import com.unrc.app.object.ObjectOwner;


public class ABMOwner {

	public ABMOwner() {
		// TODO Auto-generated constructor stub
	}

	public void createOwner(ObjectOwner owner){
		City citBD = City.createCity(owner.getCity(),owner.getCode());
		Address address =Address.CreateAddress(owner.getStreet(), owner.getNum(),owner.getNeighborhood(),citBD);
		Owner.createOwner(owner.getFirstName(), owner.getLastName(),owner.getDni(),owner.getEmail(), address);
	}
	
	public void deleteOwner(String dni){
		Owner.deleteOwner(dni);
	}
	
	public void ConsultOwner(String dni){
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
			System.out.println(objectOwner.toString());
		}
		else{
			System.out.println("El dueño con dni "+dni+" no se encuentra registrado");
		}
	}
}
	