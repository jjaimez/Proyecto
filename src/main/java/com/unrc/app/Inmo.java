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
        ABMOwner abmOwner = new ABMOwner();
        AMBRealEstate ambRealEstate = new AMBRealEstate();  
        

        ObjectRealEstate capello = new ObjectRealEstate("capello S.A","0358 464 5929","capello-sa.com.ar","info@capello-sa.com.ar","Rio Cuarto",5800,"Alvear","1052","Centro");
        ambRealEstate.createRealEstate(capello);
        
        ObjectRealEstate terra = new ObjectRealEstate("Terra Nova","(0358) 155075659","www.terranova.com","inmob-terra-nova@hotmial.com","Rio Cuarto",5800,"San Martin","695","centro");
        ambRealEstate.createRealEstate(terra);
        
        ObjectOwner nico = new ObjectOwner("Nicolas","Orcasitas","37.134.136","nico.orcasitas@gmail.com",
        						"Cordoba",5000,"Emilio Lamarca","3816","Urca");
        abmOwner.createOwner(nico);
        
        ObjectOwner jazu = new ObjectOwner("Jacinto","Jaimez","38.312.543","example@gmail.com",
				"Rio Cuarto",5800,"Sobremonte","2912","Aceitera");
        abmOwner.createOwner(jazu);
        
        ObjectOwner eze = new ObjectOwner("ezequiel","Zensich","36.534.098","kkjkjdas@homail.com",
				"Rio Cuarto",5800,"Juan jose Paso","312","Ferroviario");
        abmOwner.createOwner(eze);
        
        ObjectOwner joako = new ObjectOwner("Joako","Heredia","28.534.098","example@example.com",
        		"Rio Cuarto",5800,"Sobremonte","2912","Aceitera","Terra Nova","capello S.A");
        abmOwner.createOwner(joako);
      
        ObjectOwner example = new ObjectOwner("dasdas","eqweq","28.534.098","example@gmail.com",
				"Rio Cuarto",5800,"Sobremonte","2912","Ferroviario");
        abmOwner.createOwner(example);//No se debe cargar porque tiene el mismo numero de documento que tuViejaAlta
       
      //  abmOwner.deleteOwner("37.134.136");
        abmOwner.ConsultOwner("28.534.098");
        abmOwner.ConsultOwner("28.534.123"); //Debe decir queno existe

        
        ObjectRealEstate nf = new ObjectRealEstate("HF inmobiliaria","4631511","www.nfinmobiliaria.com","nelsonfuentespropiedades@gmail.com","Rio Cuarto",5800,"Dean Funes","495","","38.312.543","28.534.098");
        ambRealEstate.createRealEstate(nf);
        
        
        //ambRealEstate.deleteRealEstate("HF inmobiliaria");// tirra errror si no existe
        ambRealEstate.ConsultRealEstate("capello S.A");
        ambRealEstate.ConsultRealEstate("noexiste"); //Debe decir queno existe
    }
}
