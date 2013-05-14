/**
 * 
 */
package com.unrc.app.object;

import java.util.Arrays;
import java.util.List;

/**
 * @author jacinto
 *
 */
public class ObjectRealEstate {
	private String name;// nombre de la inmobiliaria
	private String phone_number; //numero de telefono
	private String web_site;//sitio web
	private String email;//email
	private String city;//ciudad
	private int code;//codigo postal
	private String street;//calle
	private String num;//numero de domicilio
	private String neighborhood;//barrio
	private List<String> owners;//lista de dueños asociados
	
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
	}//end constructor

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
	//Constructor con parametros
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

	//----------------------GETTERS------------------------
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}//end getName

	/**
	 * @return the phone_number
	 */
	public String getPhoneNumber() {
		return phone_number;
	}//end getPhoneNumber

	/**
	 * @return the web_site
	 */
	public String getWebSite() {
		return web_site;
	}//end getWebSite

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}//end getEmail

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}//end getCity

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}//end getCode

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}//end getStreet

	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}//end getNum

	/**
	 * @return the neighborhood
	 */
	public String getNeighborhood() {
		return neighborhood;
	}//end getNeighborhood

	/**
	 * @return the owners
	 */
	public List<String> getOwners() {
		return owners;
	}//getOwners

	//--------------SETTERS----------------------------------
	
	public void setOwners(List<String> owners) {
		this.owners = owners;
	}//end setOwners

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}//end setName

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
	}//end setWebSite

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}//end setEmail

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}//end setCity

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}//end setCode

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}//end setStreet

	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}//end setNum

	/**
	 * @param neighborhood the neighborhood to set
	 */
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}//end setNeighborhood

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Inmobiliaria [Nombre= " + name + ", telefono="
				+ phone_number + ", sitio Web= " + web_site + ", email =" + email
				+ ", ciudad =" + city + ", CP= " + code + ", calle =" + street
				+ ", num =" + num + ", barrio=" + neighborhood
				+ ", owners =" + owners + "]";
	}

	
}	