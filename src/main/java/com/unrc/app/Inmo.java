package com.unrc.app;


import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.unrc.app.object.*;

public class Inmo {
	 final static Logger logger = LoggerFactory.getLogger(Inmo.class);
	 

    public static void main( String[] args )
    {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
        ABMOwner create = new ABMOwner();
        
        ObjectOwner nico = new ObjectOwner("Nicolas","Orcasitas","37.134.136","nico.orcasitas@gmail.com",
        						"Cordoba",5000,"Emilio Lamarca",3816,"Urca");
        create.createOwner(nico);
        
        ObjectOwner jazu = new ObjectOwner("Jacinto","Jaimez","38.312.543","example@gmail.com",
				"Rio Cuarto",5800,"Sobremonte",2912,"Aceitera");
        create.createOwner(jazu);
        
        ObjectOwner eze = new ObjectOwner("ezequiel","Zensich","36.534.098","kkjkjdas@homail.com",
				"Rio Cuarto",5800,"Juan josé Paso",312,"Ferroviario");
        create.createOwner(eze);
        
        ObjectOwner joako = new ObjectOwner("Joako","Heredia","28.534.098","example@example.com",
        		"Rio Cuarto",5800,"Sobremonte",2912,"Aceitera");
        create.createOwner(joako);
        
        ObjectOwner example = new ObjectOwner("dasdas","eqweq","28.534.098","example@gmail.com",
				"Rio Cuarto",5800,"Sobremonte",2912,"Ferroviario");
        create.createOwner(example);//No se debe cargar porque tiene el mismo numero de documento que tuViejaAlta
       
       create.deleteOwner(joako);
       

        System.out.println( "¡SE EJECUTÓ TODO!" );
    }
}
