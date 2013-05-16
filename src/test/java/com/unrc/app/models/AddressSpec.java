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

    @Test
    public void shouldValidateMandatoryFields(){
    	Address a = new Address();
        //check errors
        the(a).shouldNotBe("valid");
        the(a.errors().get("street")).shouldBeEqual("Falta calle");
        the(a.errors().get("num")).shouldBeEqual("Falta numero");
        the(a.errors().get("city_id")).shouldBeEqual("value is missing");

    }
}