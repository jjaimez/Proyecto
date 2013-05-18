///*  Analisis Y Diseño De Sistemas(3303)
// *         Año 2013
// * Proyecto:Web para informatizar revista inmobiliaria  
// * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
// */
//
//package com.unrc.app.models;
//
//import static org.javalite.test.jspec.JSpec.the;
//
//import java.util.LinkedList;
//
//import org.javalite.activejdbc.Base;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.unrc.app.enumerado.Offer;
//import com.unrc.app.enumerado.Type;
//
//public class BuildingSpec {
//	 @Before
//	    public void before(){
//	        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
//	        Base.openTransaction();
//	    }
//
//	    @After
//	    public void after(){
//	        Base.rollbackTransaction();
//	        Base.close();
//	    }
//
//	    //creo un Building invalido y lo corroboro
//	    @Test
//	    public void shouldValidateMandatoryFields(){
//	    	Owner o = new Owner();
//	        //check errors
//	        the(o).shouldNotBe("valid");
//	        the(o.errors().get("price")).shouldBeEqual("value is missing");
//	        the(o.errors().get("type")).shouldBeEqual("value is missing");
//	        the(o.errors().get("offer")).shouldBeEqual("value is missing");
//	        the(o.errors().get("address_id")).shouldBeEqual("value is missing");
//
//	    }
//	    
//	    /*Creo un Building valido y corroboro esto, luego creo un Building con el mismo
//	     * Address, con lo que no tendria q crear un nuevo Owner y retortan el Building
//	     * ya existente en la BD.
//	     */
//	    @Test
//	    public void shouldValidateCreate(){
//	    	createBuilding(Address address,String description,String price,Offer offer,Type type,Owner owner, RealEstate realEstate){
//	    	City c = City.createCity("example City", 1);
//	    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
//	    	LinkedList<String> list = new LinkedList<String>();
//	    	RealEstate r = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate", "web_site RealEstate", "email RealEstate", a,list);
//	    	Building b = Building.createBuilding(a, description, price, offer, type, owner, realEstate)
//	    	the(r).shouldBe("valid");
//	    	the(r).shouldNotBeNull();
//	    	the(r).shouldContain("phone_number RealEstate");
//	    	the(r).shouldContain("web_site RealEstate");
//	    	the(r).shouldContain("email RealEstate");
//	    	RealEstate r2 = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate2", "web_site RealEstate", "email RealEstate", a, null);
//	    	the(r2).shouldNotContain("phone_number RealEstate2");
//	    }
//	    
//	    /*Eliminamos un Building existente y corroboramos que sea borre, luego eliminamos
//	     * e intento eliminarlo nuevamente
//	     */
//	    @Test
//	    public void shouldValidateDelete(){
//	    	City c = City.createCity("example City", 1);
//	    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
//	    	LinkedList<String> list = new LinkedList<String>();
//	    	RealEstate r = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate", "web_site RealEstate", "email RealEstate", a, list);
//	    	the(RealEstate.deleteRealEstate(r.getName())).shouldBeTrue();
//	    	the((RealEstate.deleteRealEstate("Name RealEstate"))).shouldBeFalse();
//	    }
//
//	    
//	    /*Creo un Building, luego la busco y me fijo si son el mismo Building
//	     */
//	    @Test
//	    public void shouldValidateFindByName(){
//	    	City c = City.createCity("example City", 1);
//	    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
//	    	LinkedList<String> list = new LinkedList<String>();
//	    	RealEstate r = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate", "web_site RealEstate", "email RealEstate", a, list);
//	    	RealEstate r2 = RealEstate.findByName("Name RealEstate");
//	    	the(r2.getName()).shouldBeEqual(r.getName());	
//	    }
//	    
//	   /*Creo un Building y corroboro que exista, luego busco una Building inexistente
//	    */
//	    @Test
//	    public void shouldValidateExistRealEsate(){
//	    	City c = City.createCity("example City", 1);
//	    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
//	    	LinkedList<String> list = new LinkedList<String>();
//	    	RealEstate r = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate", "web_site RealEstate", "email RealEstate", a, list);
//	    	the(RealEstate.existRealEstate(r.getName())).shouldBeTrue();
//	    	the(RealEstate.existRealEstate("No existe")).shouldBeFalse();
//	    }	
//	    
//
//}
