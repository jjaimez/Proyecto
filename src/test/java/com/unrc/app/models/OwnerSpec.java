package com.unrc.app.models;

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
}
