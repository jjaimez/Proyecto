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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;



import freemarker.template.Template;
import freemarker.template.TemplateException;

import freemarker.template.Configuration;

public class Inmo {
	public static void main(String[] args) {

		interfaz("/inmo","InmoApp.html");
		interfaz("/inmo/building","Inmueble.html");
		interfaz("/inmo/newBuilding","FormBuilding.html");
		interfaz("/inmo/deleteBuilding","EliminarInmueble.html");
		interfaz("/inmo/owner","Dueño.html");
		interfaz("/inmo/newOwner","FormOwner.html");
		interfaz("/inmo/deleteOwner","EliminarDueño.html");
		interfaz("/inmo/realEstate","Inmobiliaria.html");
		interfaz("/inmo/newRealEstate","FormRealEstate.html");
		interfaz("/inmo/deleteRealEstate","EliminarInmobiliaria.html");
		interfaz("/inmo/perfilDuenio","PerfilDueño.html");
		interfaz("/inmo/perfilInmueble","PerfilInmueble.html");
		interfaz("/inmo/perfilInmobiliaria","PerfilInmobiliaria.html");



		//Lista todo los owners
		get(new Route("/inmo/owners") {
			@Override
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				String ret = "Owners not found";
				LazyList<Owner> ol = Owner.findAll();
				if (ol!=null){
					ret = "";
					Iterator<Owner> itro = ol.iterator();
					ret += "<html><head>" +
							"<meta content='text/html; charset=ISO-8859-1' http-equiv='content-type'>" +
							"<title>ListaDuenios</title>" +
							"</head><body>" +
							"<div style='text-align: center;'><big style='font-weight: bold; font-style: italic;'><big><big><big>Listado" +
							" de Duenios</big></big></big></big><br>" +
							"</div>" +
							"<br>" +
							"<big><big style='font-weight: bold; font-style: italic;'><big>Duenios:</big></big></big><br>";
					while (itro.hasNext()){
						Owner ow = itro.next();
						ObjectOwner o = ABMOwner.toObject(ow);
						ret += "<meta http-equiv='content-type' content='text/html; charset=utf-8'>" +
								"<big><span style='font-weight: bold;'> Nombre: </span>"+o.getFirstName()+"<span style='font-weight: bold;'> Apellido: </span>"+o.getLastName()+" <span style='font-weight: bold;'> Dni: </span>"+o.getDni()+" <span style='font-weight: bold;'> Email:</span> "+o.getEmail()+"</big>"+" <span style='font-weight: bold;'> Calle: </span>"+o.getStreet()+" <span style='font-weight: bold;'> Num: </span> "+o.getNum()+"<span style='font-weight: bold;'> Barrio: </span>"+o.getNeighborhood()+" <span style='font-weight: bold;'> Ciudad: </span>"+o.getCity()+" <span style='font-weight: bold;'> CP: </span>"+o.getCode()+" <span style='font-weight: bold;'> Inmobiliarias: </span>"+o.getRealEstates() +
								"<br>" +
								"<br>" ;
					}
					ret += "</body></html>" ;
				}	
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}
		});

		//Muestra el perfil de un owner pasando como parametro su dni
		get(new Route("/consultOwner") {
			@Override
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				String ret = "<h1>Owner not found</h1>";
				ABMOwner ambo = new ABMOwner();
				String dni = request.queryParams("dni");
				if (Owner.existOwner(dni) && dni != "") {
					ret = "";
					ret += "<html><head>" +
							"<meta content='text/html; charset=ISO-8859-1' http-equiv='content-type'>" +
							"<title>Duenio</title>" +
							"</head><body>" +
							"<div style='text-align: center;'><big style='font-weight: bold; font-style: italic;'><big><big><big>Duenio" +
							"</big></big></big></big><br>" +
							"</div>" +
							"<br>" +
							"<big><big style='font-weight: bold; font-style: italic;'><big>Perfil del Duenio:</big></big></big><br>";
					ObjectOwner o = ambo.consultOwner(dni);
					ret += "<meta http-equiv='content-type' content='text/html; charset=utf-8'>" +
							"<big><span style='font-weight: bold;'> Nombre: </span>"+o.getFirstName()+"<span style='font-weight: bold;'> Apellido: </span>"+o.getLastName()+" <span style='font-weight: bold;'> Dni: </span>"+o.getDni()+" <span style='font-weight: bold;'> Email:</span> "+o.getEmail()+"</big>"+" <span style='font-weight: bold;'> Calle: </span>"+o.getStreet()+" <span style='font-weight: bold;'> Num: </span> "+o.getNum()+"<span style='font-weight: bold;'> Barrio: </span>"+o.getNeighborhood()+" <span style='font-weight: bold;'> Ciudad: </span>"+o.getCity()+" <span style='font-weight: bold;'> CP: </span>"+o.getCode()+" <span style='font-weight: bold;'> Inmobiliarias: </span>"+o.getRealEstates() +
							"<br>" +
							"<br>" ;
					ret += "</body></html>" ;
				}
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}
		});

		//Lista todo los realEstates
		get(new Route("/inmo/realEstates") {
			@Override
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				String ret = "Real Estates not found";
				LazyList<RealEstate> ol = RealEstate.findAll();
				if (ol!=null){
					ret = "";
					Iterator<RealEstate> itro = ol.iterator();
					ret += "<html><head>" +
							"<meta content='text/html; charset=ISO-8859-1' http-equiv='content-type'>" +
							"<title>ListaInmobiliarias</title>" +
							"</head><body>" +
							"<div style='text-align: center;'><big style='font-weight: bold; font-style: italic;'><big><big><big>Listado" +
							" de Inmobiliarias</big></big></big></big><br>" +
							"</div>" +
							"<br>" +
							"<big><big style='font-weight: bold; font-style: italic;'><big>Inmobiliarias:</big></big></big><br>";
					while (itro.hasNext()){
						RealEstate RS = itro.next();
						ObjectRealEstate rs = AMBRealEstate.toObject(RS);
						ret += "<meta http-equiv='content-type' content='text/html; charset=utf-8'>" +
								"<big><span style='font-weight: bold;'> Nombre: </span>"+rs.getName()+" <span style='font-weight: bold;'> Telefono: </span>"+rs.getPhoneNumber()+" <span style='font-weight: bold;'> Sitio Web: </span>"+rs.getWebSite()+" <span style='font-weight: bold;'> Email:</span> "+rs.getEmail()+"</big>"+" <span style='font-weight: bold;'> Calle: </span>"+rs.getStreet()+" <span style='font-weight: bold;'> Num: </span> "+rs.getNum()+"<span style='font-weight: bold;'> Barrio: </span>"+rs.getNeighborhood()+" <span style='font-weight: bold;'> Ciudad: </span>"+rs.getCity()+" <span style='font-weight: bold;'> CP: </span>"+rs.getCode()+" <span style='font-weight: bold;'> DNI Duenios: </span>"+rs.getOwners() +
								"<br>" +
								"<br>" ;
					}
					ret += "</body></html>" ;
				}	
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}
		});

		//Muestra el perfil de una inmobiliaria pasando como parametro su nombre
		get(new Route("/consultRealEstate") {
			@Override
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				AMBRealEstate ambo = new AMBRealEstate();
				String ret = "<h1>Nombre invalido  - Real Estate not found</h1>";
				String name = request.queryParams("name").replace("%20"," ");
				if (RealEstate.existRealEstate(name) && name != "") {
					ret ="";
					ret += "<html><head>" +
							"<meta content='text/html; charset=ISO-8859-1' http-equiv='content-type'>" +
							"<title>Inmobiliaria</title>" +
							"</head><body>" +
							"<div style='text-align: center;'><big style='font-weight: bold; font-style: italic;'><big><big><big>" +
							"Inmobiliaria</big></big></big></big><br>" +
							"</div>" +
							"<br>" +
							"<big><big style='font-weight: bold; font-style: italic;'><big>Perfil de la Inmobiliaria:</big></big></big><br>";
					ObjectRealEstate rs = ambo.consultRealEstate(name);
					ret += "<meta http-equiv='content-type' content='text/html; charset=utf-8'>" +
							"<big><span style='font-weight: bold;'> Nombre: </span>"+rs.getName()+" <span style='font-weight: bold;'> Telefono: </span>"+rs.getPhoneNumber()+" <span style='font-weight: bold;'> Sitio Web: </span>"+rs.getWebSite()+" <span style='font-weight: bold;'> Email:</span> "+rs.getEmail()+"</big>"+" <span style='font-weight: bold;'> Calle: </span>"+rs.getStreet()+" <span style='font-weight: bold;'> Num: </span> "+rs.getNum()+"<span style='font-weight: bold;'> Barrio: </span>"+rs.getNeighborhood()+" <span style='font-weight: bold;'> Ciudad: </span>"+rs.getCity()+" <span style='font-weight: bold;'> CP: </span>"+rs.getCode()+" <span style='font-weight: bold;'> DNI Duenios: </span>"+rs.getOwners() +
							"<br>" +
							"<br>" ;
					ret += "</body></html>" ;
				}
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}
		});

		//Lista todo los buildings
		get(new Route("/inmo/buildings") {
			@Override
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				String ret = "Buildings not found";
				LazyList<Building> bl = Building.findAll();
				if (bl!=null){
					ret = "";
					Iterator<Building> itro = bl.iterator();
					ret += "<html><head>" +
							"<meta content='text/html; charset=ISO-8859-1' http-equiv='content-type'>" +
							"<title>ListaInmuebles</title>" +
							"</head><body>" +
							"<div style='text-align: center;'><big style='font-weight: bold; font-style: italic;'><big><big><big>Listado" +
							" de Inmuebles</big></big></big></big><br>" +
							"</div>" +
							"<br>" +
							"<big><big style='font-weight: bold; font-style: italic;'><big>Inmuebles:</big></big></big><br>";
					while (itro.hasNext()){
						Building bu = itro.next();
						ObjectBuilding b = ABMBuilding.toObject(bu);
						ret += "<meta http-equiv='content-type' content='text/html; charset=utf-8'>" +
								"<big><span style='font-weight: bold;'> Descripcion: </span>"+b.getDescription()+" <span style='font-weight: bold;'> Oferta: </span>"+b.getOffer()+" <span style='font-weight: bold;'> Tipo: </span>"+b.getType()+" <span style='font-weight: bold;'> Precio: </span>"+b.getPrice()+" <span style='font-weight: bold;'> Calle: </span>"+b.getStreet()+" <span style='font-weight: bold;'> Num: </span> "+b.getNum()+"<span style='font-weight: bold;'> Barrio: </span>"+b.getNeighborhood()+" <span style='font-weight: bold;'> Ciudad: </span>"+b.getCity()+" <span style='font-weight: bold;'> CP: </span>"+b.getCode()+" <span style='font-weight: bold;'> DNI Duenio: </span>"+b.getDniOwner()+" <span style='font-weight: bold;'> Inmobiliaria:</span> "+b.getNameRealEstate()+"</big>" +
								"<br>" +
								"<br>" ;
					}
					ret += "</body></html>" ;
				}	
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}
		});

		//Muestra el building de la direccion y ciudad pasado como parametro
		get(new Route("/consultBuilding") {
			@Override
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				String ret = "<h1>Building not found</h1>";
				String street = request.queryParams("street").replace("%20"," ");
				String num = request.queryParams("num").replace("%20"," ");
				Integer code = -1;
				if (request.queryParams("code") != ""){
					code = Integer.parseInt(request.queryParams("code"));
				}
				City city = City.findByCode(code);
				if (city != null){
					if (Building.existBuilding(street, num, city.getInteger("id")) && street!="" && num!=""){ 
						ret = "";
						ret += "<html><head>" +
								"<meta content='text/html; charset=ISO-8859-1' http-equiv='content-type'>" +
								"<title>Inmueble</title>" +
								"</head><body>" +
								"<div style='text-align: center;'><big style='font-weight: bold; font-style: italic;'><big><big><big>Inmueble" +
								"</big></big></big></big><br>" +
								"</div>" +
								"<br>" +
								"<big><big style='font-weight: bold; font-style: italic;'><big>Datos del Inmueble:</big></big></big><br>";
						ObjectBuilding b = ABMBuilding.consultBuilding(street, num, city.getInteger("id"));
						ret += "<meta http-equiv='content-type' content='text/html; charset=utf-8'>" +
								"<big><span style='font-weight: bold;'> Descripcion: </span>"+b.getDescription()+" <span style='font-weight: bold;'> Oferta: </span>"+b.getOffer()+" <span style='font-weight: bold;'> Tipo: </span>"+b.getType()+" <span style='font-weight: bold;'> Precio: </span>"+b.getPrice()+" <span style='font-weight: bold;'> Calle: </span>"+b.getStreet()+" <span style='font-weight: bold;'> Num: </span> "+b.getNum()+"<span style='font-weight: bold;'> Barrio: </span>"+b.getNeighborhood()+" <span style='font-weight: bold;'> Ciudad: </span>"+b.getCity()+" <span style='font-weight: bold;'> CP: </span>"+b.getCode()+" <span style='font-weight: bold;'> DNI Duenio: </span>"+b.getDniOwner()+" <span style='font-weight: bold;'> Inmobiliaria:</span> "+b.getNameRealEstate()+"</big>" +
								"<br>" +
								"<br>" ;
						ret += "</body></html>" ;
					}
					else{
						ret = "<h1>Building inexistente</h1>";
					}
				}
				else{
					ret = "<h1>Ciudad inexistente</h1>";
				}   			    	
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}
		});

		///Realiza una busqueda
		get(new Route("/inmo/searchengine") {
			@Override
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				String ret = "Search empty";
				String dni = null;
				String name = null;
				String type = null;
				String offer = null;
				int code = -1;
				String minPrice = null;
				String maxPrice = null;
				SearchEngine st = new SearchEngine();
				Set<String> params = request.queryParams();
				if (params.contains("dni") && request.queryParams("dni") != "") {
					dni = request.queryParams("dni");
				}
				if (params.contains("name") && request.queryParams("name") != ""){
					name = request.queryParams("name").replace("%20", " ");
				}
				if (params.contains("type")&& request.queryParams("type") != ""){
					type = request.queryParams("type");
				}
				if (params.contains("offer")&& request.queryParams("offer") != ""){
					offer = request.queryParams("offer");
				}
				if (params.contains("code")&& request.queryParams("code") != ""){
					code = Integer.parseInt(request.queryParams("code"));
				}
				if (params.contains("minPrice")&& request.queryParams("minPrice") != ""){
					minPrice = request.queryParams("minPrice");
				}
				if (params.contains("maxPrice")&& request.queryParams("maxPrice") != ""){
					maxPrice = request.queryParams("maxPrice");
				}
				List<Building> list = st.search(dni, name, type, offer, code, minPrice, maxPrice);
				if (!list.isEmpty()){
					ret = "<html><head>" +
							"<meta content='text/html; charset=ISO-8859-1' http-equiv='content-type'>" +
							"<title>BusquedaInmuebless</title>" +
							"</head><body>" +
							"<div style='text-align: center;'><big style='font-weight: bold; font-style: italic;'><big><big><big>Busqueda" +
							" de Inmuebles</big></big></big></big><br>" +
							"</div>" +
							"<br>" +
							"<big><big style='font-weight: bold; font-style: italic;'><big>Inmuebles encontrados:</big></big></big><br>";
					Iterator<Building> itr = list.iterator();
					while (itr.hasNext()) {
						Building b = itr.next();
						ObjectBuilding OB = ABMBuilding.toObject(b);
						ret += "<meta http-equiv='content-type' content='text/html; charset=utf-8'>" +
								"<big><span style='font-weight: bold;'> Descripcion: </span>"+OB.getDescription()+" <span style='font-weight: bold;'> Oferta: </span>"+OB.getOffer()+" <span style='font-weight: bold;'> Tipo: </span>"+OB.getType()+" <span style='font-weight: bold;'> Precio: </span>"+OB.getPrice()+" <span style='font-weight: bold;'> Calle: </span>"+OB.getStreet()+" <span style='font-weight: bold;'> Num: </span> "+OB.getNum()+"<span style='font-weight: bold;'> Barrio: </span>"+OB.getNeighborhood()+" <span style='font-weight: bold;'> Ciudad: </span>"+OB.getCity()+" <span style='font-weight: bold;'> CP: </span>"+OB.getCode()+" <span style='font-weight: bold;'> DNI Duenio: </span>"+OB.getDniOwner()+" <span style='font-weight: bold;'> Inmobiliaria:</span> "+OB.getNameRealEstate()+"</big>" +
								"<br>" +
								"<br>" ;
					}
					ret += "</body></html>" ;
				}
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}
		});	

		//Inserta un dueño
		post(new Route ("/newOwner") {        
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				Set<String> params = request.queryParams();
				String ret = "<h1>Duenio No Registrado</h1>";
				String firstName = null;
				String lastName = null;
				String dni = null;
				String email = null;
				String city = null;
				int code = 0;
				String street = null;
				String num = null;
				String neighborhood = null;
				List<String> listRealEstates = new LinkedList<String>();
				String [] rsName = null;
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
				if(params.contains("realEstates")){
					rsName = request.queryParams("realEstates").split(";");
				}
				if (params.contains("code")){
					code = Integer.parseInt(request.queryParams("code"));
				}
				//revisar condicion if
				if (firstName != null && lastName != null && dni != null){
					int j=0;
					while(j <= rsName.length-1){
						listRealEstates.add(rsName[j]);
						j++;
					}
					if (!Owner.existOwner(dni)){
						ObjectOwner ow = new ObjectOwner(firstName, lastName, dni, email, city, code, street, num, neighborhood, listRealEstates);
						ABMOwner ambo = new ABMOwner();
						ambo.createOwner(ow);
						ret = "<h1>Duenio Registrado</h1>";
					}
					else{
						ret = "<h1>el Duenio ya existente</h1>";
					}
				}
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}  
		});

		//Inserta una inmobiliaria
		post(new Route("/newRealEstate") {
			@Override
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				Set<String> params = request.queryParams();
				String ret="<h1>No se creo la inmobiliaria</h1>";
				String name = null;
				String phone_number=null;
				String web_site=null;
				String email=null;
				String city = null;
				int code = -1;
				String street = null;
				String num = null;
				String neighborhood=null;
				List<String> listOwners = new LinkedList<String>();
				String [] owDni = null;
				if (params.contains("street")){
					street = request.queryParams("street").replace("%20", " ");
				}
				if (params.contains("phone_number")){
					phone_number = request.queryParams("phone_number").replace("%20", " ");
				}
				if (params.contains("web_site")){
					web_site = request.queryParams("web_site").replace("%20", " ");
				}
				if (params.contains("email")){
					email = request.queryParams("email").replace("%20", " ");
				}
				if (params.contains("num")){
					num = request.queryParams("num");
				}
				if (params.contains("name")){
					name = request.queryParams("name").replace("%20", " ");
				}
				if (params.contains("city")){
					city = request.queryParams("city").replace("%20", " ");
				}
				if (params.contains("neighborhood")){
					neighborhood = request.queryParams("neighborhood").replace("%20", " ");
				}
				if (params.contains("code")){
					code = Integer.parseInt(request.queryParams("code"));
				}
				if(params.contains("owners")){
					owDni = request.queryParams("owners").split(";");
				}
				//revisar condicion if
				if(name != null && code != -1 && street != null && num != null){  
					int j=0;
					while(j <= owDni.length-1){
						listOwners.add(owDni[j]);
						j++;
					}
					if (!RealEstate.existRealEstate(name)){
						ObjectRealEstate ob= new ObjectRealEstate(name,phone_number,web_site,email,city,code,street,num,neighborhood,listOwners);
						AMBRealEstate rs = new AMBRealEstate();
						rs.createRealEstate(ob);
						ret="<h1>La inmobiliaria fue creada</h1>";
					}
					else {
						ret="<h1>La inmobiliaria ya existe</h1>";
					}
				}
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}
		});

		//Inserta un inmueble
		post(new Route("/newBuilding") {
			@Override
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				Set<String> params = request.queryParams();
				String ret = "<h1> Building no creado </h1>";
				String street = null;
				String num = null;
				String offer = null;
				String type = null;
				String dniOwner = null;
				String nameRealEstate = null;
				String description = null;
				int price = -1;
				String neighborhood = null;
				String city = null;
				int code = -1;
				if (params.contains("street")){
					street = request.queryParams("street").replace("%20", " ");
				}
				if (params.contains("num")){
					num = request.queryParams("num");
				}
				if (params.contains("offer")){
					offer = request.queryParams("offer");
				}
				if (params.contains("type")){
					type = request.queryParams("type");
				}
				if (params.contains("dni")){
					dniOwner = request.queryParams("dni");
				}
				if (params.contains("name")){
					nameRealEstate = request.queryParams("name").replace("%20", " ");
				}
				if (params.contains("description")){
					description = request.queryParams("description").replace("%20", " ");
				}
				if (params.contains("price")){
					price = Integer.parseInt(request.queryParams("price"));
				}
				if (params.contains("city")){
					city = request.queryParams("city").replace("%20", " ");
				}
				if (params.contains("neighborhood")){
					neighborhood = request.queryParams("neighborhood").replace("%20", " ");
				}
				if (params.contains("code")){
					code = Integer.parseInt(request.queryParams("code"));
				}
				if ( street != null && num != null && code != -1){
					if (!Building.existBuilding(street, num, City.findByCode(code).getInteger("id"))){
						ObjectBuilding ob = new ObjectBuilding(description, price, offer, type, street, num, neighborhood, city, code, dniOwner, nameRealEstate);
						ABMBuilding abmb = new ABMBuilding();
						abmb.createBuilding(ob);
						ret = "<h1> Building creado </h1>";
					}
					else{
						ret = "<h1> Building ya existente </h1>";
					}

				}
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}
		});

		//Elimina un dueño
		post(new Route ("/deleteOwner") {        
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				Set<String> params = request.queryParams();
				String ret = "<h1>el Dueño no se elimino</h1>";
				String dni = null;
				if (params.contains("dni")) {
					dni = request.queryParams("dni");
					if (Owner.existOwner(dni)){
						ABMOwner ambo = new ABMOwner();
						ambo.deleteOwner(dni);
						ret = "<h1>Duenio eliminado</h1>";
					}
					else{
						ret = "<h1>Duenio inexistente</h1>";
					}
				}   
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}  
		});



		//Elimina una inmobiliaria
		post(new Route("/deleteRealEstate") {
			@Override
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				Set<String> params = request.queryParams();
				String ret="<h1>No se elimino la inmobiliaria</h1>";
				String name = null;
				if (params.contains("name")){
					name = request.queryParams("name").replace("%20", " ");
					if (RealEstate.existRealEstate(name)){
						AMBRealEstate rs = new AMBRealEstate();
						rs.deleteRealEstate(name);
						ret="<h1>La inmobiliaria fue eliminada</h1>";
					}
					else{
						ret="<h1>Inmobiliaria inexistente</h1>";
					}
				}
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}
		});


		//elimina un inmueble
		post(new Route("/deleteBuilding") {
			@Override
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				Set<String> params = request.queryParams();
				String ret = "<h1> Building no eliminado </h1>";
				String street = null;
				String num = null;
				int code = -1;
				if (params.contains("street")){
					street = request.queryParams("street").replace("%20", " ");
				}
				if (params.contains("num")){
					num = request.queryParams("num");
				} 
				if (params.contains("code")){
					code = Integer.parseInt(request.queryParams("code"));
				}
				if ( street != null && num != null && code != -1){
					if (City.existCity(code)){
						City c = City.findByCode(code);
						if (Building.existBuilding(street, num, c.getInteger("id"))){
							ABMBuilding abmb = new ABMBuilding();
							abmb.deleteBuilding(street, num, code);
							ret = "<h1> Building eliminado </h1>";
						}
						else {
							ret = "<h1> Building inexistente </h1>";
						}
					}	
					else{
						ret = "<h1> Building inexistente </h1>";
					}
				}
				Base.commitTransaction();
				Base.close();
				response.type("text/html");
				return ret;
			}

		});
	}	

	/*relaciona una ruta que sera utilizada en el browser con una plantilla html que posee la interfaz grafica 
	 * grafica de esa ruta(ejemplo: formularios, pagina de inicio) utilizando la libreria freemarker */
	public static void interfaz(String route, final String html){
		get(new Route(route) {
			public Object handle(Request request, Response response) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/inmoapp_development", "root", "root");
				Base.openTransaction();
				StringWriter writer = new StringWriter();  //El objeto que recogerá la salida
				final Configuration configuracion = new Configuration();	
				configuracion.setClassForTemplateLoading(Inmo.class, "/");
				Template plantillaInmoApp = null;
				try {
					plantillaInmoApp = configuracion.getTemplate(html); //se carga la plantilla
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Map<String, Object> buildingMap = new HashMap<String, Object>(); //Se carga el map, que se va a utilizar para aplicarlo a la plantilla
				try {
					plantillaInmoApp.process(buildingMap, writer);
				} catch (TemplateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Base.commitTransaction();
				Base.close();
				return writer;
			};
		});
	}

}


