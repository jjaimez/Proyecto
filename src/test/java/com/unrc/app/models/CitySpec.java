/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */


package com.unrc.app.models;

import com.unrc.app.models.City;

import org.javalite.activejdbc.Base;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.javalite.test.jspec.JSpec.the;
public class CitySpec {

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

    
    //Creamos un ciudad invalida y corroboramos esto.
    @Test
    public void shouldValidateMandatoryFields(){
    	City c = new City();
        //check errors
        the(c).shouldNotBe("valid");
        the(c.errors().get("name")).shouldBeEqual("Falta nombre ciudad");
        the(c.errors().get("code")).shouldBeEqual("Falta codigo postal");

    }
    
    
    /*Creo una ciudad valida y corroboro esto, luego creo una ciudad con el mismo
     * Code, con lo que no tendria q crear una nueva City y retortan la City ya
     * existente en la BD.
     */
    @Test
    public void shouldValidateCreate(){
    	City c = City.createCity("example City", 1);
    	the(c).shouldBe("valid");
    	the(c).shouldNotBeNull();
    	the(c).shouldContain("example City");
    	the(c).shouldContain(1);
    	City c2 = City.createCity("example City 2", 1);
    	the(c2).shouldContain("example City");
    	
    }
    
    /*Eliminamos una ciudad existente y corroboramos que sea borre, luego eliminamos
     * una ciudad inexistente y corroboramos que sea invalido
     */
    @Test
    public void shouldValidateDelete(){
    	City c = City.createCity("example City", 1);
    	the(c.delete()).shouldBeTrue();
    	City c2 = new City();
    	the(c2.delete()).shouldBeFalse();
    }
    
    
    /*Creo una ciudad, luego la busco y me fijo si son la mismo ciudad
     */
    @Test
    public void shouldValidateFindByCode(){
    	City c = City.createCity("example City", 1);
    	City c2 = City.findByCode(1);
    	the(c2.getName()).shouldBeEqual(c.getName()	);	
    }
    /*Creo una ciudad y corroboro que exista, luego busco una ciudad inexistente
    */
    @Test
    public void shouldValidateExistCity(){
    	City c = City.createCity("example City", 1);
    	the(City.existCity(c.getCode())).shouldBeTrue();
    	the(City.existCity(2)).shouldBeFalse();
    }	
}