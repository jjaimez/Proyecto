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
        ABMOwner abmOwner = new ABMOwner();//Creo instancia de ABM para dueños
        AMBRealEstate ambRealEstate = new AMBRealEstate();  //Creo instancia de ABM para inmobiliarias
        
        //Creo inmobiliaria Capello en la base de datos
        ObjectRealEstate capello = new ObjectRealEstate("capello S.A","0358 464 5929","capello-sa.com.ar","info@capello-sa.com.ar","Rio Cuarto",5800,"Alvear","1052","Centro");
        ambRealEstate.createRealEstate(capello);
                
        //Creo Nicolas en la base de datos
        ObjectOwner nico = new ObjectOwner("Nicolas","Orcasitas","37.134.136","nico.orcasitas@gmail.com","Cordoba",5000,"Emilio Lamarca","3816","Urca");
        abmOwner.createOwner(nico);
        
        //Creo jazu en la base de datos
        ObjectOwner jazu = new ObjectOwner("Jacinto","Jaimez","38.312.543","example@gmail.com",
				"Rio Cuarto",5800,"Sobremonte","2912","Aceitera");
        abmOwner.createOwner(jazu);
        
        //creo Ezequiel en la base de datos
        ObjectOwner eze = new ObjectOwner("ezequiel","Zensich","36.534.098","eze_zensich@homail.com","Rio Cuarto",5800,"Juan jose Paso","312","Ferroviario");
        abmOwner.createOwner(eze);
        
        //Creo joako en la base de datos con mismo domicilio que ezequiel y sin mail y asociado a la inmobiliaria Capello S.A
        ObjectOwner joako = new ObjectOwner("Joako","Heredia","28.534.098",null, "Rio Cuarto",5800,"Sobremonte","2912","Aceitera","capello S.A");
        abmOwner.createOwner(joako);
      
        //Creo example pero no se dbe cargar proque el numero de documento es igual que joako y joako ya está cargado en la base de datos
        ObjectOwner example = new ObjectOwner("dasdas","eqweq","28.534.098","example@gmail.com","Rio Cuarto",5800,"Sobremonte","2912","Ferroviario");
        abmOwner.createOwner(example);
       
        //Borro nicolás
        abmOwner.deleteOwner("37.134.136");
        
        //Creo inmobiliaria terra
        ObjectRealEstate terra = new ObjectRealEstate("Terra Nova","(0358) 155075659","www.terranova.com","inmob-terra-nova@hotmial.com","Rio Cuarto",5800,"San Martin","695","centro");
        ambRealEstate.createRealEstate(terra);
        
        //Creo inmobiliaria nf con 2 dueños asociados, joako y jazu
        ObjectRealEstate nf = new ObjectRealEstate("HF inmobiliaria","4631511","www.nfinmobiliaria.com","nelsonfuentespropiedades@gmail.com","Rio Cuarto",5800,"Dean Funes","495","","38.312.543","28.534.098");
        ambRealEstate.createRealEstate(nf);
         
        //borro Terra Nova
        ambRealEstate.deleteRealEstate("Terra Nova");
        
        //muestro por pantalla información de capello S.A
        ambRealEstate.consultRealEstate("capello S.A");
        
        //Trato de mostrar informacion de una inmobiliaria que no existe
        ambRealEstate.consultRealEstate("noexiste"); 
        
        //Actualizo nombre,apellido, mail y calle de jazu 
        abmOwner.updateOwner("38.312.543","Jazu","JaimezRenom","mailRenom@gmail.com","Rio Cuarto",5800,"Cabrera","3012","Microcentro"); //Modifico datos
        
        //Actualizo ciudad y direccion de jazu
        abmOwner.updateOwner("38.312.543","Jazu","JaimezRenom","mailRenom@gmail.com","La rioja",312,"Jujuy","312","alberdi"); //Modifico ciudad
    }
}