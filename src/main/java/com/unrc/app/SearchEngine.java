/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */
package com.unrc.app;

import java.util.Iterator;
import java.util.LinkedList;
import org.javalite.activejdbc.LazyList;
import com.unrc.app.models.Building;
import com.unrc.app.object.ObjectBuilding;

public class SearchEngine {
	
	/*Busqueda que toma como parametros el dni del dueño que se desea buscar, nombre de inmobiliaria a la que pertenece el inmueble
	 * que se desea buscar, dos parametros booleanos que determinan si se desea poner algún filtro
	 * a la búsqueda, código de la ciudad para filtrar por ciudad, y precio minimo y máximo al filtrar por precio
	 */
	public LinkedList<ObjectBuilding> search(String dni, String name, String type,Boolean filterCity,Boolean filterPrice, int code, String minPrice,String maxPrice){
		LinkedList<ObjectBuilding> objectBuildings = new LinkedList<ObjectBuilding>();
		LazyList<Building> buildings=Building.findAll();
		Iterator<Building> itr= buildings.iterator();
		while (itr.hasNext()){
			ObjectBuilding b= ABMBuilding.consultBuilding(itr.next().getInteger("id"));
			if(filter(b,dni,name,type,filterCity,filterPrice,code,minPrice,maxPrice)){
				objectBuildings.add(b);
			}
		}
		return objectBuildings;
	}

	/*Retorna true si un objeto dado con los filtros que se desean cumple con todas
	 * las restricciones que se le ponen en la búsqueda, primero evalúa si se desea buscar 
	 * por ciudad,en caso verdaero, evalua si el objeto pasado cumple con la ciudad, luego hace 
	 * lo mismo para precio y evalúa si el precio minimo es menor o igual que el precio máximo,
	 * en caso de que no cumpla esto retornará falso la búsqueda y por último evalúa que el objeto
	 * cumpla con las restricciones que se le imponen, si cumple todo retorna true*/
	private Boolean filter(ObjectBuilding ob,String dni, String name, String type, Boolean filterCity,Boolean filterPrice,int code, String minPrice,String maxPrice){
		Boolean ret=true;
		if (filterCity){
			if(!(ob.getCode()==code)){
				return false;
			}
		}
	  	if (filterPrice){
	  		if(minPrice.compareTo(maxPrice)<=0){
	  			if(!((ob.getPrice().compareTo(maxPrice)<=0)&&(ob.getPrice().compareTo(minPrice)>=0))){
	  				return false;
	  			}
	  		}
	  		else{
	  			return false;
	  		}	
	  	}
	  	if(dni!=null){
	  		if ((ob.getDniOwner().compareTo(dni))!=0){
	  			return false;
	  		}	
	  	}
	  	if (name!=null){
	  		if((ob.getNameRealEstate().compareTo(name))!=0){
	  			return false;
	  		}
	  	}
	  	if (type!=null){
	  		if(ob.getType().compareTo(type)!=0){
	  			return false;
	  		}
	  	}
	  	return ret;
	}
}
