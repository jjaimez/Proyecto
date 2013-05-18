/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */

package com.unrc.app.models;

import java.util.LinkedList;

import com.unrc.app.models.RealEstate;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
public class RealEstateSpec {

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

    /*Creo un RealEstate invalido y lo corroboro 
     */
    @Test
    public void shouldValidateMandatoryFields(){
    	RealEstate r = new RealEstate();
        //check errors
        the(r).shouldNotBe("valid");
        the(r.errors().get("name")).shouldBeEqual("value is missing");
        the(r.errors().get("address_id")).shouldBeEqual("value is missing");
    }
    
    /*Creo un RealEstate valido y corroboro esto, luego creo un RealEstate con el mismo
     * Name, con lo que no tendria q crear un nuevo RealEstate y retortan el RealEstate
     * ya existente en la BD.
     */
    @Test
    public void shouldValidateCreate(){
    	City c = City.createCity("example City", 1);
    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
    	LinkedList<String> list = new LinkedList<String>();
    	RealEstate r = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate", "web_site RealEstate", "email RealEstate", a,list);
    	the(r).shouldBe("valid");
    	the(r).shouldNotBeNull();
    	the(r).shouldContain("phone_number RealEstate");
    	the(r).shouldContain("web_site RealEstate");
    	the(r).shouldContain("email RealEstate");
    	RealEstate r2 = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate2", "web_site RealEstate", "email RealEstate", a, null);
    	the(r2).shouldNotContain("phone_number RealEstate2");
    }
    
    /*Eliminamos un RealEstate existente y corroboramos que sea borre, luego eliminamos
     * e intento eliminarlo nuevamente
     */
    @Test
    public void shouldValidateDelete(){
    	City c = City.createCity("example City", 1);
    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
    	LinkedList<String> list = new LinkedList<String>();
    	RealEstate r = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate", "web_site RealEstate", "email RealEstate", a, list);
    	the(RealEstate.deleteRealEstate(r.getName())).shouldBeTrue();
    	the((RealEstate.deleteRealEstate("Name RealEstate"))).shouldBeFalse();
    }
    
    
    /*Creo un RealEstate, luego la busco y me fijo si son el mismo RealEstate
     */
    @Test
    public void shouldValidateFindByName(){
    	City c = City.createCity("example City", 1);
    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
    	LinkedList<String> list = new LinkedList<String>();
    	RealEstate r = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate", "web_site RealEstate", "email RealEstate", a, list);
    	RealEstate r2 = RealEstate.findByName("Name RealEstate");
    	the(r2.getName()).shouldBeEqual(r.getName());	
    }
    
   /*Creo un RealEstate y corroboro que exista, luego busco una RealEstate inexistente
    */
    @Test
    public void shouldValidateExistRealEsate(){
    	City c = City.createCity("example City", 1);
    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
    	LinkedList<String> list = new LinkedList<String>();
    	RealEstate r = RealEstate.createRealEstate("Name RealEstate", "phone_number RealEstate", "web_site RealEstate", "email RealEstate", a, list);
    	the(RealEstate.existRealEstate(r.getName())).shouldBeTrue();
    	the(RealEstate.existRealEstate("No existe")).shouldBeFalse();
    }	
    
    
}