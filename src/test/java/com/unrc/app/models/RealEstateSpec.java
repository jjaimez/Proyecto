package com.unrc.app.models;

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

    @Test
    public void shouldValidateMandatoryFields(){
    	RealEstate r = new RealEstate();
        //check errors
        the(r).shouldNotBe("valid");
        the(r.errors().get("name")).shouldBeEqual("value is missing");
        the(r.errors().get("address_id")).shouldBeEqual("value is missing");


    }
}