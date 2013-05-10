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
		City citBD = new City(owner.getCity(),owner.getCode());
		citBD.createCity();
		Address address= new Address(citBD.returnId(), owner.getStreet(), owner.getNum(),owner.getNeighborhood());
		address.CreateAddress();
		Owner datesOwner = new Owner(owner.getFirstName(), owner.getLastName(),owner.getDni(),owner.getEmail(), address.returnId());
		datesOwner.createOwner();
	}
	
	
	//Anda MAL
	public void deleteOwner(ObjectOwner owner){
		Owner datesOwner = new Owner();
		datesOwner.deleteOwner(owner.getDni());

		
	}
}
