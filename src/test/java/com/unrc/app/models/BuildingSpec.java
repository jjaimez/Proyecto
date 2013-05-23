/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */

package com.unrc.app.models;

import static org.javalite.test.jspec.JSpec.the;

import java.util.LinkedList;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.unrc.app.models.Building;


public class BuildingSpec {
	 @Before
	    public void before(){
	        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
	        Base.openTransaction();
	    }

	    @After
	    public void after(){
	        Base.rollbackTransaction();
	        Base.close();
	    }

	    //creo un Building invalido y lo corroboro
	    @Test
	    public void shouldValidateMandatoryFields(){
	    	Building b = new Building();
	        //check errors
	        the(b).shouldNotBe("valid");
	        the(b.errors().get("price")).shouldBeEqual("value is missing");
	        the(b.errors().get("type")).shouldBeEqual("value is missing");
	        the(b.errors().get("offer")).shouldBeEqual("value is missing");
	        the(b.errors().get("address_id")).shouldBeEqual("value is missing");

	    }
	    
	    /*Creo un Building valido y corroboro esto, luego creo un Building con el mismo
	     * Address, con lo que no tendria q crear un nuevo Owner y retortan el Building
	     * ya existente en la BD.
	     */
	    @Test
	    public void shouldValidateCreate(){
	    	City c = City.createCity("example City", 1);
	    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
	    	LinkedList<String> list = new LinkedList<String>();
	    	RealEstate r = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate", "web_site RealEstate", "email RealEstate", a,list);
	    	Owner o = Owner.createOwner("first name Owner", "last name Owner", "dni Owner", "email Owner", a,list);
	    	Building b = Building.createBuilding(a,"description Building", "price Building","rent" ,"office", o, r);
	    	the(b).shouldBe("valid");
	    	the(b).shouldNotBeNull();
	    	the(b).shouldContain("description Building");
	    	the(b).shouldContain("price Building");
	    	Building b2 = Building.createBuilding(a,"description Building", "price Building2","rent", "office", o, r);
	    	the(b2).shouldNotContain("price Building2");
	    }
	    
	    /*Eliminamos un Building existente y corroboramos que sea borre, luego eliminamos
	     * e intento eliminarlo nuevamente
	     */
	    @Test
	    public void shouldValidateDelete(){
	    	City c = City.createCity("example City", 1);
	    	int idCity= c.getInteger("id");
	    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
	    	LinkedList<String> list = new LinkedList<String>();
	    	RealEstate r = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate", "web_site RealEstate", "email RealEstate", a,list);
	    	Owner o = Owner.createOwner("first name Owner", "last name Owner", "dni Owner", "email Owner", a,list);
	    	Building.createBuilding(a,"description Building", "price Building", "rent", "office", o, r);
	    	the(Building.deleteBuilding("st Address","num Address",idCity)).shouldBeTrue();
	    	the(Building.deleteBuilding("st Address","num Address",idCity)).shouldBeFalse();
	    }

	    
	    /*Creo un Building, luego la busco y me fijo si son el mismo Building
	     */
	    @Test
	    public void shouldValidateFindByBuilding(){
	    	City c = City.createCity("example City", 1);
	    	City c2 = City.createCity("example City", 2);
	    	int idCityC2= c2.getInteger("id");
	    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
	    	Address a2 = Address.createAddress("st Address","num Address","nghd Address",c2);
	    	LinkedList<String> list = new LinkedList<String>();
	    	RealEstate r = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate", "web_site RealEstate", "email RealEstate", a, list);
	    	Owner o = Owner.createOwner("first name Owner", "last name Owner", "dni Owner", "email Owner", a,list);
	    	Building b = Building.createBuilding(a2,"description Building", "price Building2", "rent", "office", o, r);
	    	Building b2 = Building.findByBuilding("st Address","num Address",idCityC2);
	    	the(b2.getOffer()).shouldBeEqual(b.getOffer());	
	    }
	    
	   /*Creo un Building y corroboro que exista, luego busco una Building inexistente
	    */
	    @Test
	    public void shouldValidateExistBuilding(){
	    	City c = City.createCity("example City", 1);
	    	int idCity= c.getInteger("id");
	    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
	    	LinkedList<String> list = new LinkedList<String>();
	    	RealEstate r = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate", "web_site RealEstate", "email RealEstate", a,list);
	    	Owner o = Owner.createOwner("first name Owner", "last name Owner", "dni Owner", "email Owner", a,list);
	    	Building.createBuilding(a,"description Building", "price Building", "rent", "office", o, r);
	    	the(Building.existBuilding("st Address","num Address",idCity)).shouldBeTrue();
	    	the(Building.existBuilding("st AddressInex","num Address",1)).shouldBeFalse();
	    }		
	    

}
