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


import org.javalite.activejdbc.*;

import com.unrc.app.models.Owner;
import com.unrc.app.object.ObjectOwner;

import spark.*;


public class Inmo {
public static void main(String[] args) {
    
    //Lista todo los owners
    get(new Route("/owner") {
        @Override
        public Object handle(Request request, Response response) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            Base.openTransaction();
            String ret = "";
            LazyList<Owner> ol = Owner.findAll();
            Iterator<Owner> itro = ol.iterator();
            ABMOwner ambo = new ABMOwner();
            while (itro.hasNext()){
                Owner ow = itro.next();
                ObjectOwner o = ambo.consultOwner(ow.getDni());
                ret += o.toString()+", ";
            }
            Base.rollbackTransaction();
            Base.close();
            return ret;
        }
    });
    
    //Muestra el owner del dni pasado como parametro
    get(new Route("/owner/:dni") {
        @Override
        public Object handle(Request request, Response response) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            Base.openTransaction();
            ABMOwner ambo = new ABMOwner();
            ObjectOwner ow = ambo.consultOwner(request.params(":dni"));
            String ret = ow.toString();
            System.out.println(ret);
            Base.rollbackTransaction();
            Base.close();
            return ret;
        }
    });
    
    
    put(new Route ("/realestate/:name:/:phone_number/:web_site/:email/:city/:code/:street/:num/:neighborhood/:owners") {        
        public Object handle(Request request, Response response) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
            Base.openTransaction();
            ObjectOwner ow = new ObjectOwner(request.params(":name"),request.params(":phone_number"),
                    request.params(":web_site"),request.params(":email"),request.params(":city"),
                    Integer.parseInt(request.params(":code")),request.params(":street"),request.params(":num"),
                    request.params(":neighborhood"),request.params(":owners"));
            ABMOwner ambo = new ABMOwner();
            ambo.createOwner(ow);
            ow = ambo.consultOwner(request.params(":dni"));
            String ret = ow.toString();
            Base.rollbackTransaction();
            Base.close();
            return ret;
        }  
    });
    
}   
}




