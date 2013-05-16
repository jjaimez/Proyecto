package com.unrc.app;


import java.util.LinkedList;

import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.unrc.app.enumerado.Offer;
import com.unrc.app.enumerado.Type;
import com.unrc.app.models.Building;
import com.unrc.app.object.*;

public class Inmo {
	 final static Logger logger = LoggerFactory.getLogger(Inmo.class);
	 

    public static void main( String[] args ){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
        ABMOwner abmOwner = new ABMOwner();//Creo instancia de ABM para dueños
        AMBRealEstate ambRealEstate = new AMBRealEstate();  //Creo instancia de ABM para inmobiliarias
        ABMBuilding abmBuilding = new ABMBuilding();//Creo una instancia de ABM para inmuebles
        
        //Creo inmobiliaria Capello en la base de datos
        ObjectRealEstate capello = new ObjectRealEstate("capello S.A","0358 464 5929","capello-sa.com.ar","info@capello-sa.com.ar","Rio Cuarto",5800,"Alvear","1052","Centro");
        ambRealEstate.createRealEstate(capello);
                
        //Creo Nicolas en la base de datos
        ObjectOwner nico = new ObjectOwner("Nicolas","Orcasitas","37.134.136","nico.orcasitas@gmail.com","Cordoba",5000,"Emilio Lamarca","3816","Urca");
        abmOwner.createOwner(nico);
        
        //Creo jazu en la base de datos
        ObjectOwner jazu = new ObjectOwner("Jacinto","Jaimez","38.312.543","example@gmail.com","Rio Cuarto",5800,"Sobremonte","2912","Aceitera");
        abmOwner.createOwner(jazu);
        
        //creo Ezequiel en la base de datos
        ObjectOwner eze = new ObjectOwner("ezequiel","Zensich","36.534.098","eze_zensich@homail.com","Rio Cuarto",5800,"Juan jose Paso","312","Ferroviario");
        abmOwner.createOwner(eze);
        
        //Creo joako en la base de datos con mismo domicilio que ezequiel y sin mail y asociado a la inmobiliaria Capello S.A
        ObjectOwner joako = new ObjectOwner("Joako","Heredia","28.534.098",null, "Rio Cuarto",5800,"Sobremonte","2912","Aceitera","capello S.A");
        abmOwner.createOwner(joako);
      
        //Creo example pero no se debe cargar proque el numero de documento es igual que joako y joako ya está cargado en la base de datos
        ObjectOwner example = new ObjectOwner("example","lastExample","28.534.098","example@gmail.com","Rio Cuarto",5800,"Sobremonte","2912","Ferroviario");
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
        
        //Muestro por pantalla información de joako
        abmOwner.printOwner("28.534.098");
        
        //Actualizo nombre,apellido, mail y calle de jazu 
        abmOwner.updateFirstName("38.312.543","JazuRenombrado");
        
        //Actualizo apellido de jazu
        abmOwner.updateLastName("38.312.543", "ApellidoRenombrado");
        
        //Actualizo email de jazu
        abmOwner.updateEmail("38.312.543", "nuevoEmail@gmail.com");
        
        //Actualizo calle viviendo en la misma ciudad
        abmOwner.updateAddress("38.312.543","nuevaCalle", "Talar 1341", "Bimaco");
        
        //Actualizo direccion que ya esté creada en la base de datos
        abmOwner.updateAddress("38.312.543","Sobremonte","2912","Aceitera");
        
        //Actualizo dirección incluida la ciudad
        abmOwner.updateAddress("38.312.543", "La Carlota", 5461, "Mariano Moreno","7132",null);
        
        //Borro jazu
        abmOwner.deleteOwner("38.312.543");
        
        //Agrego capello S.A y Terra Nova(Ya habia sido borrada) a jazu
        abmOwner.addRealEstates("38.312.543","Terra Nova","capello S.A");
    
        //borro el Real Estate HF inmobiliaria de jazu
        abmOwner.removeRealEstates("38.312.543","HF inmobiliaria");
        
        //Creo inmobiliaria laCasa para probar actualizaciones
        ObjectRealEstate laCasa = new ObjectRealEstate("La casa","(0358) 1550312412","www.lacasa.com","inmob-lacasa@hotmial.com","Rio Cuarto",5800,"Buenos Aires","312","Hipodromo");
        ambRealEstate.createRealEstate(laCasa);
        
        //Actualizo sitio web
        ambRealEstate.updateWebSite("La casa", "www.nuevoSitio.com");
        
        //Actualizo telefono
        ambRealEstate.updatePhoneNumber("La casa","telNuevo");
        
        //Actualizo mail
        ambRealEstate.updateEmail("La casa", "nuevoEmail@gmail.com");
        
        //Actualizo direccion pero sigue viviendo en la misma ciudad
        ambRealEstate.updateAddress("La casa", "nuevaCalle","nuevoNum", "");
        
        //Actualizo ciudad y direccion
        ambRealEstate.updateAddress("La casa", "La carlota",3121,"calleLaCarlota","numLaCarlota", "");
        
        //Actualizo ciudad y direccion
        ambRealEstate.updateAddress("La casa", "Rio Cuarto",5800,"Dean Funes","495", "");
        
        //creo una inmobiliaria con dueño ezequiel e inmobiliaria "La casa"
        ObjectBuilding prueba = new ObjectBuilding("casa 2 pisos", "5000", Offer.SALE, Type.HOUSE, "Martin Quenon", "361", "", "Rio Cuarto", 5800,"36.534.098","La casa");
        abmBuilding.createBuilding(prueba);
        
        //creo un inmueble con dueño ezequiel e inmobiliaria "La casa"
        ObjectBuilding prueba1 = new ObjectBuilding("casa 4 habit", "34500", Offer.RENT, Type.DEPARTAMENT, "Lavalle", "500", "Centro", "Rio Cuarto", 5800,"36.534.098","La casa");
        abmBuilding.createBuilding(prueba1);
        
        //creo un inmueble con dueño ezequiel e inmobiliaria "La casa"
        ObjectBuilding prueba2 = new ObjectBuilding("Monoambiente", "1000", Offer.SALE, Type.FARM, "Rivadavia", "125", "Centro", "Rio Cuarto", 5800,"36.534.098","La casa");
        abmBuilding.createBuilding(prueba2);
        
        //creo un inmueble con dueño ezequiel e inmobiliaria "La casa"
        ObjectBuilding prueba3 = new ObjectBuilding("dpto 2 baños", "2000", Offer.RENT, Type.OFFICE, "Maipu", "384", "Tiro Federal", "Rio Cuarto", 5800,"36.534.098","La casa");
        abmBuilding.createBuilding(prueba3);
        
        //Borro inmueble prueba3
        abmBuilding.deleteBuilding("Maipu", "384", 5800);
        
        //actualizo oferta inmueble prueba1
        abmBuilding.updateOffer("Lavalle", "500", 5800, Offer.SALE);
        
        //actualizo tipo inmueble prueba1
        abmBuilding.updateType("Lavalle", "500", 5800, Type.OFFICE);
        
        //actualizo precio inmueble prueba1
        abmBuilding.updatePrice("Lavalle", "500", 5800, "312983");
        
        //actualizo dueño inmueble prueba1
        abmBuilding.updateOwner("Lavalle", "500", 5800, "28.534.098");
        
        //actualizo inmobiliaria inmueble prueba1
        abmBuilding.updateRealEstate("Lavalle", "500", 5800, "capello S.A");
        
        //actualizo descripcion inmueble prueba1
        abmBuilding.updateDescription("Lavalle", "500", 5800, "La nueva descripcion es!");
    
        //borro Ezequiel
        abmOwner.deleteOwner("36.534.098");
        

        //busca la cantidad de buildings asociados a la inmobiliaria la casa (tiene que ser cero)
        LinkedList<Building> buildings = ambRealEstate.getBuildings(laCasa.getName());
        System.out.println("Building publicados por inmobiliarai la casa: " + buildings.size());
    
    }
}