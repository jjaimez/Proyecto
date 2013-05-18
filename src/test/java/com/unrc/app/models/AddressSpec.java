/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */

package com.unrc.app.models;

import com.unrc.app.models.Address;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
public class AddressSpec {

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

    //Creo un Address invalido y lo corroboro
    @Test
    public void shouldValidateMandatoryFields(){
    	Address a = new Address();
        //check errors
        the(a).shouldNotBe("valid");
        the(a.errors().get("street")).shouldBeEqual("Falta calle");
        the(a.errors().get("num")).shouldBeEqual("Falta numero");
        the(a.errors().get("city_id")).shouldBeEqual("value is missing");
    }
    
    /*Creo un Address valido y corroboro esto, luego creo un Address con el mismo
     * Code, con lo que no tendria q crear un nuevo Address y retortan el Address
     * ya existente en la BD.
     */
    @Test
    public void shouldValidateCreate(){
    	City c = City.createCity("example City", 1);
    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
    	the(a).shouldBe("valid");
    	the(a).shouldNotBeNull();
    	the(a).shouldContain("st Address");
    	the(a).shouldContain("num Address");
    	the(a).shouldContain("nghd Address");
    	Address a2 = Address.createAddress("st Address","num Address","nghd Address2",c);
    	the(a2).shouldNotContain("nghd Address2");
    }
    
    /*Eliminamos un Address existente y corroboramos que sea borre, luego eliminamos
     * y compuebo q sea null
     */
    @Test
    public void shouldValidateDelete(){
    	City c = City.createCity("example City", 1);
    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
    	the(a.deleteAddress()).shouldBeTrue();
    	//the(a.deleteAddress()).shouldBeFalse(); //Bug
    }
    
    
    /*Creo un Address, luego la busco y me fijo si son el mismo Address
     */
    @Test
    public void shouldValidateFindByAddress(){
    	City c = City.createCity("example City", 1);
    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
    	Address a2 = Address.findByAddress("st Address","num Address",a.getCityId());
    	the(a2.getNeighborhood()).shouldBeEqual(a.getNeighborhood());	
    }
    
   /*Creo un Address y corroboro que exista, luego busco una Address inexistente
    */
    @Test
    public void shouldValidateExistAddress(){
    	City c = City.createCity("example City", 1);
    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
    	the(Address.existAddress(a.getStreet(),a.getNum(),a.getCityId())).shouldBeTrue();
    	the(Address.existAddress("st","num",1)).shouldBeFalse();
    }	
    
}