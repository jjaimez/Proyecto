package com.unrc.app;


import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unrc.app.models.Address;
import com.unrc.app.models.City;
import com.unrc.app.models.Owner;


public class Inmo {
	 final static Logger logger = LoggerFactory.getLogger(Inmo.class);
	 

		
	
    public static void main( String[] args )
    {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
        City cit= new City();
        cit.set("name","Cordoba","code",5800);
        if(!cit.existCity()){
        	cit.saveIt();
        }
        Address ad= new Address();
        ad.set("city_id",City.findFirst("code = ?", 5800).getId(),"street","Arrayanes","num",2459,"neighborhood","Bimaco"); 
        if(!ad.existAddress()){
        	ad.saveIt();
        	
        }
        Owner owner= new Owner();
        System.out.println(ad.getInteger("id"));
        owner.set("first_name","Nicolás","last_name","Orcasitas","dni","37.134.136","address_id",6);
        System.out.println("Existe owner? : "+owner.existOwner());
        if(!owner.existOwner()){
        	owner.saveIt();
        }
        System.out.println( "Hello World!" );
    }
}
