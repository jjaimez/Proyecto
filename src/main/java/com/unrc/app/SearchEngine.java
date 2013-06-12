/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */
package com.unrc.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.unrc.app.models.Address;
import com.unrc.app.models.Building;
import com.unrc.app.models.City;
import com.unrc.app.models.Owner;
import com.unrc.app.models.RealEstate;

public class SearchEngine {
	
	/* Retorna una lista con todos los modelos Buildings que cumplen con los filtros aplicados (dni dueño, nombre inmobiliaria
	 * tipo, oferta, codigo postal, precio minimo y maximo), los filtros pueden ser aplicados de todas las combinaciones
	 * posibles, solo se traeran de la base de datos los objetos con los filtros descriptos */
	public List<Building> search(String dni, String name, String type, String offer, int code, String minPrice,String maxPrice){
		List<Building> listBuildings = new LinkedList<Building>(); //lista de los buildings filtrados
		ArrayList<Object> params = new ArrayList<Object>(); // array que recogera los parametros de los filtros
		String query = ""; //string que recogera las consultas de cada filtro
		if (dni != null){
			Integer ow = Owner.findByDni(dni).getInteger("id");
			params.add(ow);
			if (query != ""){
				query += " and owner_id = ?";
			}
			else {
				query += "owner_id = ?";
			}
		}
		if (name != null){
			Integer rs = RealEstate.findByName(name).getInteger("id");
			params.add(rs);
			if (query != ""){
				query += " and real_estate_id = ?";
			}
			else {
				query += "real_estate_id = ?";
			}
		}
		if (type != null){
			params.add(type);
			if (query != ""){
				query += " and type = ?";
			}
			else {
				query += "type = ?";
			}
		}
		if (offer != null){
			params.add(offer);
			if (query != ""){
				query += " and offer = ?";
			}
			else {
				query += "offer = ?";
			}
		}
		if (minPrice != null){
			params.add(minPrice);
			if (query != ""){
				query += " and price >= ?";
			}
			else {
				query += "price >= ?";
			}
		}
		if (maxPrice != null){
			params.add(maxPrice);
			if (query != ""){
				query += " and price <= ?";
			}
			else {
				query += "price <= ?";
			}
		}
		if (code == -1){    // si no se filtrara por ciudad, realizo la busqueda corroborando cuantos filtros hay en el array
			switch ( params.size() ) {
			case 1:
				listBuildings = Building.where(query, params.get(0));
				break;
			case 2:
				listBuildings = Building.where(query, params.get(0),params.get(1));
				break;
			case 3:
				listBuildings = Building.where(query, params.get(0),params.get(1),params.get(2));
				break;
			case 4:
				listBuildings = Building.where(query, params.get(0),params.get(1),params.get(2),params.get(3));
				break;
			case 5:
				listBuildings = Building.where(query, params.get(0),params.get(1),params.get(2),params.get(3),params.get(4));
				break;
			case 6:
				listBuildings = Building.where(query, params.get(0),params.get(1),params.get(2),params.get(3),params.get(4),params.get(5));
				break;
			default:
				System.out.println("error" );
				break;
			}
		} else { // si se filtrara por ciudad, realizo la busqueda corroborando cuantos filtros hay en el array incluyendo el filtro por ciudad
			Integer cit = City.findByCode(code).getInteger("id");
			List<Address> listAddress = Address.where("city_id = ?", cit);
			Iterator<Address> itrAddress = listAddress.iterator();
			switch ( params.size() ) {
			case 0:
				while (itrAddress.hasNext()) {
					Address ad = itrAddress.next();
					System.out.println("ADDRESS: "+ad+ " EXISTE EDIFICIO: "+ Building.existBuilding(ad.getStreet(), ad.getNum(), cit));
					if (Building.existBuilding(ad.getStreet(), ad.getNum(), cit)){
						List<Building> subListBuildings = Building.where("address_id = ?", ad.getInteger("id"));
						listBuildings.addAll(subListBuildings);
					}
				} 
				break;
			case 1:
				while (itrAddress.hasNext()) {
					Address ad = itrAddress.next();
					System.out.println("ADDRESS: "+ad+ " EXISTE EDIFICIO: "+ Building.existBuilding(ad.getStreet(), ad.getNum(), cit));
					if (Building.existBuilding(ad.getStreet(), ad.getNum(), cit)){
						List<Building> subListBuildings = Building.where("address_id = ? and "+query, ad.getInteger("id"),params.get(0));
						listBuildings.addAll(subListBuildings);
					}
				} 
				break;
			case 2:
				while (itrAddress.hasNext()) {
					Address ad = itrAddress.next();
					System.out.println("ADDRESS: "+ad+ " EXISTE EDIFICIO: "+ Building.existBuilding(ad.getStreet(), ad.getNum(), cit));
					if (Building.existBuilding(ad.getStreet(), ad.getNum(), cit)){
						List<Building> subListBuildings = Building.where("address_id = ? and "+query, ad.getInteger("id"),params.get(0),params.get(1));
						listBuildings.addAll(subListBuildings);
					}
				} 
				break;
			case 3:
				while (itrAddress.hasNext()) {
					Address ad = itrAddress.next();
					System.out.println("ADDRESS: "+ad+ " EXISTE EDIFICIO: "+ Building.existBuilding(ad.getStreet(), ad.getNum(), cit));
					if (Building.existBuilding(ad.getStreet(), ad.getNum(), cit)){
						List<Building> subListBuildings = Building.where("address_id = ? and "+query, ad.getInteger("id"),params.get(0),params.get(1),params.get(2));
						listBuildings.addAll(subListBuildings);
					}
				} 
				break;
			case 4:
				while (itrAddress.hasNext()) {
					Address ad = itrAddress.next();
					System.out.println("ADDRESS: "+ad+ " EXISTE EDIFICIO: "+ Building.existBuilding(ad.getStreet(), ad.getNum(), cit));
					if (Building.existBuilding(ad.getStreet(), ad.getNum(), cit)){
						List<Building> subListBuildings = Building.where("address_id = ? and "+query, ad.getInteger("id"),params.get(0),params.get(1),params.get(2),params.get(3));
						listBuildings.addAll(subListBuildings);
					}
				} 
				break;
			case 5:
				while (itrAddress.hasNext()) {
					Address ad = itrAddress.next();
					System.out.println("ADDRESS: "+ad+ " EXISTE EDIFICIO: "+ Building.existBuilding(ad.getStreet(), ad.getNum(), cit));
					if (Building.existBuilding(ad.getStreet(), ad.getNum(), cit)){
						List<Building> subListBuildings = Building.where("address_id = ? and "+query, ad.getInteger("id"),params.get(0),params.get(1),params.get(2),params.get(3),params.get(4));
						listBuildings.addAll(subListBuildings);
					}
				} 
				break;
			case 6:
				while (itrAddress.hasNext()) {
					Address ad = itrAddress.next();
					System.out.println("ADDRESS: "+ad+ " EXISTE EDIFICIO: "+ Building.existBuilding(ad.getStreet(), ad.getNum(), cit));
					if (Building.existBuilding(ad.getStreet(), ad.getNum(), cit)){
						List<Building> subListBuildings = Building.where("address_id = ? and "+query, ad.getInteger("id"),params.get(0),params.get(1),params.get(2),params.get(3),params.get(4),params.get(5));
						listBuildings.addAll(subListBuildings);
					}
				} 
				break;
			default:
				System.out.println("error" );
				break;
			}
		}
		return listBuildings;
	}
}