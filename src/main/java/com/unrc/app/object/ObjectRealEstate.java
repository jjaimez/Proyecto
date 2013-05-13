/**
 * 
 */
package com.unrc.app.object;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jacinto
 *
 */
public class ObjectRealEstate {
	private String name;// nombre de la inmobiliaria
	private String phone_number; //numero de telefono
	private String web_site;
	private String email;
	private String city;
	private int code;//codigo postal
	private String street;//calle
	private String num;//numero de domicilio
	private String neighborhood;//barrio
	private List<String> owners;
	
	//Constructor vacio
	public ObjectRealEstate() {
		name = null; 
		phone_number = null;
		web_site = null;
		email = null;
		city = null;
		code = -1;
		street = null;
		num = null;
		neighborhood = null;
		owners = null;
		
	}

	/**
	 * @param name
	 * @param phone_number
	 * @param web_site
	 * @param email
	 * @param city
	 * @param code
	 * @param street
	 * @param num
	 * @param neighborhood
	 * @param owners
	 */
	public ObjectRealEstate(String name,String phone_number,String web_site,String email,String city,int code,
			String street,String num,String neighborhood,String...owners) {
		this.name = name;
		this.phone_number = phone_number;
		this.web_site = web_site;
		this.email = email;
		this.city = city;
		this.code = code;
		this.street = street;
		this.num = num;
		this.neighborhood = neighborhood;
		this.owners = Arrays.asList(owners);
	}

	//Getters
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the phone_number
	 */
	public String getPhoneNumber() {
		return phone_number;
	}


	/**
	 * @return the web_site
	 */
	public String getWebSite() {
		return web_site;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}


	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}


	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}


	/**
	 * @return the neighborhood
	 */
	public String getNeighborhood() {
		return neighborhood;
	}


	public List<String> getOwners() {
		return owners;
	}

	public void setOwners(List<String> owners) {
		this.owners = owners;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	//Setters 
	/**
	 * @param phone_number the phone_number to set
	 */
	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}


	/**
	 * @param web_site the web_site to set
	 */
	public void setWebSite(String web_site) {
		this.web_site = web_site;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}


	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}


	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}


	/**
	 * @param neighborhood the neighborhood to set
	 */
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Nombre: " + name + ", Telefono: " + phone_number
				+ ", Web " + web_site  + ", Ciudad: " + city
				+ ", CP: " + code + ", Direccion: " + street + " " + num
				+ ",  Barrio " + neighborhood + ", email: " + email;
	}
	
	
}	