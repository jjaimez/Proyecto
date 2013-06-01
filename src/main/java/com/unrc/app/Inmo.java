/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 * 
 *Primer Sprint: este main tiene Altas, Modificaciones y Bajas de las distintas cosas, es utilizado para controlar el devido funcionamiento de todo. 
 * 
 *
 */

package com.unrc.app;


import static spark.Spark.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;


import org.javalite.activejdbc.*;

import com.unrc.app.models.Building;
import com.unrc.app.models.City;
import com.unrc.app.models.Owner;
import com.unrc.app.models.RealEstate;
import com.unrc.app.object.ObjectBuilding;
import com.unrc.app.object.ObjectOwner;
import com.unrc.app.object.ObjectRealEstate;

import spark.*;


public class Inmo {
public static void main(String[] args) {
    
    //Lista todo los owners
    get(new Route("/owner") {
        @Override
        public Object handle(Request request, Response response) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            Base.openTransaction();
            String ret = "Owners not found";
            LazyList<Owner> ol = Owner.findAll();
            if (ol!=null){
            	ret = "";
            	Iterator<Owner> itro = ol.iterator();
            	ABMOwner ambo = new ABMOwner();
            	while (itro.hasNext()){
            		Owner ow = itro.next();
            		ObjectOwner o = ambo.consultOwner(ow.getDni());
            		ret += o.toString()+" || ";
            	}
            }	
            Base.rollbackTransaction();
            Base.close();
            return ret;
        }
    });
    
    //Muestra el owner correspondiente al dni pasado como parametro
    get(new Route("/owner/:dni") {
        @Override
        public Object handle(Request request, Response response) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            Base.openTransaction();
            String ret = "Owner not found";
            ABMOwner ambo = new ABMOwner();
            if (Owner.existOwner(request.params(":dni"))) {
            	ObjectOwner ow = ambo.consultOwner(request.params(":dni"));
                ret = ow.toString();
            }
            Base.rollbackTransaction();
            Base.close();
            return ret;
        }
    });
    
    
    //Lista todo los realEstates
    get(new Route("/realestate") {
        @Override
        public Object handle(Request request, Response response) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            Base.openTransaction();
            String ret = "Real Estates not found";
            LazyList<RealEstate> ol = RealEstate.findAll();
            if (ol!=null){
            	ret = "";
            	Iterator<RealEstate> itro = ol.iterator();
            	AMBRealEstate ambo = new AMBRealEstate();
            	while (itro.hasNext()){
            		RealEstate ow = itro.next();
            		ObjectRealEstate o = ambo.consultRealEstate(ow.getName());
            		ret += o.toString()+" || ";
            	}
            }	
            Base.rollbackTransaction();
            Base.close();
            return ret;
        }
    });
    
    //Lista el real estate con nombre ":name"
    get(new Route("/realestate/:name") {
        @Override
        public Object handle(Request request, Response response) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            Base.openTransaction();
            AMBRealEstate ambo = new AMBRealEstate();
            String ret = "Nombre invalido  - Real Estate not found";
            if (RealEstate.existRealEstate(request.params(":name").replace("%20"," "))) {
            	ObjectRealEstate o = ambo.consultRealEstate(request.params(":name").replace("%20"," "));
            	ret = o.toString();
            }	
            Base.rollbackTransaction();
            Base.close();
            return ret;
        }
    });
        
    //Lista todo los buildings
   	get(new Route("/building") {
   			@Override
   			public Object handle(Request request, Response response) {
   				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
   			    Base.openTransaction();
   			    String ret = "Buildings not found";
   			    LazyList<Building> bl = Building.findAll();
   			    if (bl!=null){
   			    	ret = "";
   			    	Iterator<Building> itro = bl.iterator();
   			    	ABMBuilding ambb = new ABMBuilding();
   			    	while (itro.hasNext()){
   			    		Building bu = itro.next();
   			    		ObjectBuilding b = ambb.consultBuilding(bu.getInteger("id"));
   			    		ret += b.toString()+" || ";
   			    	}
   			    }	
   				Base.rollbackTransaction();
   			    Base.close();
   			    return ret;
   			}
   		});

   	//Muestra el building de la direccion y ciudad pasado como parametro
   		get(new Route("/building/:code/:street/:num") {
   			@Override
   			public Object handle(Request request, Response response) {
   				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
   			    Base.openTransaction();
   			    String ret = "Ciudad incorrecta - Direction not found";
   			    String street = request.params(":street").replace("%20"," ");
   			    String num = request.params(":num").replace("%20"," ");
   			    Integer code = Integer.parseInt(request.params(":code"));
   			    City city = City.findByCode(code);
   			    if (city != null){
   			    	if (Building.existBuilding(street, num, city.getInteger("id"))){ 
   			    		ObjectBuilding ob = ABMBuilding.consultBuilding(street, num, city.getInteger("id"));
   			    		ret = ob.toString();
   			    	}
   			    	else {
   			    		ret = "Calle incorrecta - Direction not found";
   			    	}
   			    }
   				Base.rollbackTransaction();
   			    Base.close();
   				return ret;
   			}
   		});
   		
   		
   		//Realiza una busqueda
   		get(new Route("/searchengine") {
   			@Override
   			public Object handle(Request request, Response response) {
   				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
   			    Base.openTransaction();
   			    String ret = "Seach empty";
   			    String dni = null;
   			    String name = null;
   			    String type = null;
   			    boolean filterCity = false;
   			    boolean filterPrice = false; 
   			    int code = 0;
   			    String minPrice = null;
   			    String maxPrice = null;
   			    SearchEngine st = new SearchEngine();
   			    Set<String> params = request.queryParams();
   			    if (params.contains("dni")) {
   			    	dni = request.queryParams("dni");
   			    }
   			    if (params.contains("name")){
   			    	name = request.queryParams("name").replace("%20", " ");
   			    }
   			    if (params.contains("type")){
   			    	type = request.queryParams("type");
   			    }
   			    if (params.contains("filterCity")){
   			    	filterCity = Boolean.parseBoolean(request.queryParams("filterCity"));
   			    }
   			    if (params.contains("filterPrice")){
   			    	filterPrice = Boolean.parseBoolean(request.queryParams("filterPrice"));
   			    }
   			    if (params.contains("code")){
   			    	code = Integer.parseInt(request.queryParams("code"));
   			    }
   			    if (params.contains("minPrice")){
   			    	minPrice = request.queryParams("minPrice");
   			    }
   			    if (params.contains("maxPrice")){
   			    	maxPrice = request.queryParams("maxPrice");
   			    }
   			    LinkedList<ObjectBuilding> list = st.search(dni, name, type, filterCity, filterPrice, code, minPrice, maxPrice);
   			    System.out.println(list.size());
   			    if (!list.isEmpty()){
   			    	ret = Integer.toString(list.size());
   			    	Iterator<ObjectBuilding> itr = list.iterator();
   			    	while (itr.hasNext()) {
   			    		ObjectBuilding b = itr.next();
   			    		ret += b.toString()+" || ";
   			    	}
   			    }
   			    Base.rollbackTransaction();
   			    Base.close();
   				return ret;
   			}
   		});
   
   		
   		
   		
   	  //No testeada devido a que no es necesario la entrega de los puts esta semana
   	    put(new Route ("/owner") {        
   	        public Object handle(Request request, Response response) {
   	            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
   	            Base.openTransaction();
   	            Set<String> params = request.queryParams();
   	            String firstName = null;
   	            String lastName = null;
   	            String dni = null;
   	            String email = null;
   	            String city = null;
   	            int code = 0;
   	            String street = null;
   	            String num = null;
   	            String neighborhood = null;
   	            String realEstates = null;
   	            Set<String> params = request.queryParams();
   	            if (params.contains("dni")) {
			    	dni = request.queryParams("dni");
			    }
			    if (params.contains("firstName")){
			    	firstName = request.queryParams("firstName").replace("%20", " ");
			    }
			    if (params.contains("lastName")){
			    	lastName = request.queryParams("lastName").replace("%20", " ");
			    }
			    if (params.contains("email")){
			    	email = request.queryParams("email").replace("%20", " ");
			    }
			    if (params.contains("city")){
			    	city = request.queryParams("city").replace("%20", " ");
			    }
			    if (params.contains("street")){
			    	street = request.queryParams("street").replace("%20", " ");
			    }
			    if (params.contains("num")){
			    	num = request.queryParams("num").replace("%20", " ");
			    }
			    if (params.contains("neighborhood")){
			    	neighborhood = request.queryParams("neighborhood").replace("%20", " ");
			    }
			    if (params.contains("realEstates")){
			    	realEstates = request.queryParams("realEstates").replace("%20", " ");
			    }
			    if (params.contains("code")){
   			    	code = Integer.parseInt(request.queryParams("code"));
   			    }
   	            ObjectOwner ow = new ObjetOwner(firstName, lastName, dni, email, city, code, street, num, neighborhood, realEstates);
   	            ABMOwner ambo = new ABMOwner();
   	            ambo.createOwner(ow);
   	            Base.rollbackTransaction();
   	            Base.close();
   	            String ret = "Owner creado";
   	            return ret;
   	        }  
   	    });
   		
   	    
}   
}




