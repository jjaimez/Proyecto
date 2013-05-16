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

    @Test
    public void shouldValidateMandatoryFields(){
    	City c = new City();
        //check errors
        the(c).shouldNotBe("valid");
        the(c.errors().get("name")).shouldBeEqual("Falta nombre ciudad");
        the(c.errors().get("code")).shouldBeEqual("Falta codigo postal");

    }
}