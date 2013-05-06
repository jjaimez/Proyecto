package com.unrc.app;


import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unrc.app.models.Address;
import com.unrc.app.models.City;


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
        ad.set("city_id",cit.getId(),"street","Arrayanes","num",2459);
        if(!ad.existAddress()){
        	ad.saveIt();
        }
        System.out.println(cit.existCity());
        System.out.println( "Hello World!" );
    }
}
