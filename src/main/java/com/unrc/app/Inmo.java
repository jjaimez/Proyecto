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

import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.unrc.app.object.*;

public class Inmo {
	 final static Logger logger = LoggerFactory.getLogger(Inmo.class);
	 
	/*ANTES DE CORRER ESTA CLASE SE DEBERÁ EJECUTAR UN SCRIPT QUE SE ENCUENTRA EN LA CARPETA
	 * CONFIG Y SE LLAMA valoresTabla.sql YA QUE AHORA PROBAMOS BÚSQUEDA SOLAMENTE, EL SCRIPT CARGARÁ 
	 * ALGUNOS VALORES EN LA BASE DE DATOS*/

    public static void main( String[] args ){
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
        ABMBuilding abmBuilding = new ABMBuilding();//Creo una instancia de ABM para inmuebles
        SearchEngine searchEngine= new SearchEngine();
              
        //creo un inmueble con dueño ezequiel e inmobiliaria "La casa"
        ObjectBuilding prueba2 = new ObjectBuilding("Monoambiente", "1000", "sale", "house", "Rivadavia", "125", "Centro", "Rio Cuarto", 5800,"37.134.136","Terra Nova");
        abmBuilding.createBuilding(prueba2);
        System.out.println("Busco inmuebles del dueño de dni 37.134.136");
        System.out.println(searchEngine.search("37.134.136",null,null,false,false,-1,null,null));
        System.out.println("Busco inmuebles de la inmobiliaria Capello S.A");
        System.out.println(searchEngine.search(null,"Capello S.A",null,false,false,-1,null,null));
        System.out.println("Busco inmuebles de tipo DEPARTAMENTO");
        System.out.println(searchEngine.search(null,null,"departament",false,false,-1,null,null));
        System.out.println("Busco inmuebles de tipo CASA");
        System.out.println(searchEngine.search(null,null,"house",false,false,-1,null,null));
        System.out.println("Busco inmuebles de dueño 37.134.136 e inmo Terra Nova");
        System.out.println(searchEngine.search("37.134.136","Terra Nova",null,false,false,-1,null,null));
        System.out.println("Busco inmuebles de  precio entre 15 y 100");
        System.out.println(searchEngine.search(null,null,null,false,true,-1,"15","100"));
        System.out.println("Busco inmuebles de precio entre 1000 y 1000");
        System.out.println(searchEngine.search(null,null,null,false,true,-1,"1000","1000"));
    }
}
