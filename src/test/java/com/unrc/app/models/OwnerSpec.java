/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */

package com.unrc.app.models;

import java.util.LinkedList;

import com.unrc.app.models.Owner;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
public class OwnerSpec {

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

    //creo un Owner invalido y lo corroboro
    @Test
    public void shouldValidateMandatoryFields(){
    	Owner o = new Owner();
        //check errors
        the(o).shouldNotBe("valid");
        the(o.errors().get("first_name")).shouldBeEqual("Falta nombre");
        the(o.errors().get("last_name")).shouldBeEqual("Falta apellido");
        the(o.errors().get("dni")).shouldBeEqual("Falta dni");
        the(o.errors().get("address_id")).shouldBeEqual("value is missing");

    }
    
    /*Creo un Owner valido y corroboro esto, luego creo un Owner con el mismo
     * Dni, con lo que no tendria q crear un nuevo Owner y retortan el Owner
     * ya existente en la BD.
     */
    @Test
    public void shouldValidateCreate(){
    	City c = City.createCity("example City", 1);
    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
    	LinkedList<String> list = new LinkedList<String>();
    	Owner o = Owner.createOwner("first name Owner", "last name Owner", "dni Owner", "email Owner", a,list);
    	the(o).shouldBe("valid");
    	the(o).shouldNotBeNull();
    	the(o).shouldContain("first name Owner");
    	the(o).shouldContain("last name Owner");
    	the(o).shouldContain("email Owner");
    	Owner o2 = Owner.createOwner("first name Owner2", "last name Owner", "dni Owner", "email Owner", a, null);
    	the(o2).shouldNotContain("first name Owner2");
    }
    
    /*Eliminamos un Owner existente y corroboramos que sea borre, luego eliminamos
     * e intento eliminarlo nuevamente
     */
    @Test
    public void shouldValidateDelete(){
    	City c = City.createCity("example City", 1);
    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
    	LinkedList<String> list = new LinkedList<String>();
    	Owner r = Owner.createOwner("first name Owner", "last name Owner", "dni Owner", "email Owner", a, list);
    	the(Owner.deleteOwner(r.getDni())).shouldBeTrue();
    	the((Owner.deleteOwner("dni Owner"))).shouldBeFalse();
    }
//    
    
    /*Creo un Owner, luego la busco y me fijo si son el mismo Owner
     */
    @Test
    public void shouldValidateFindByDni(){
    	City c = City.createCity("example City", 1);
    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
    	LinkedList<String> list = new LinkedList<String>();
    	Owner r = Owner.createOwner("first name Owner", "last name Owner", "dni Owner", "email Owner", a, list);
    	Owner r2 = Owner.findByDni("dni Owner");
    	the(r2.getFirstName()).shouldBeEqual(r.getFirstName());	
    }
    
   /*Creo un Owner y corroboro que exista, luego busco una Owner inexistente
    */
    @Test
    public void shouldValidateExistOwner(){
    	City c = City.createCity("example City", 1);
    	Address a = Address.createAddress("st Address","num Address","nghd Address",c);
    	LinkedList<String> list = new LinkedList<String>();
    	Owner r = Owner.createOwner("first name Owner", "last name Owner", "dni Owner", "email Owner", a, list);
    	the(Owner.existOwner(r.getDni())).shouldBeTrue();
    	the(Owner.existOwner("No existe")).shouldBeFalse();
    }	
    
    
}
