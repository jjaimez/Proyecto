package com.unrc.app.object;

import java.util.Arrays;
import java.util.List;

public class ObjectOwner {
	private String firstName;//Nombre
	private String lastName;//Apellido
	private String dni;//dni
	private String email;//email
	private String city;//ciudad
	private int code;//codigo postal
	private String street;//calle
	private String num;//numero de domicilio
	private String neighborhood;//barrio
	private List<String> realEstates;

	//Constructor vacio
	public ObjectOwner() {
		firstName=null;
		lastName=null;
		dni=null;
		email=null;
		street=null;
		num=null;
		neighborhood=null;
		city=null;
		code=-1;
		realEstates=null;
	}//end constructor

	/**
	 * @param firstName
	 * @param lastName
	 * @param dni
	 * @param email
	 * @param city
	 * @param code
	 * @param street
	 * @param num
	 * @param neighborhood
	 * @param realEstates
	 */
	public ObjectOwner(String firstName, String lastName, String dni,String email, String city, int code, String street, String num,String neighborhood,String...realEstates) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.email = email;
		this.city = city;
		this.code = code;
		this.street = street;
		this.num = num;
		this.neighborhood = neighborhood;
		this.realEstates = Arrays.asList(realEstates);
	}//end constructor

	//--------------GETTERS------------------
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}//end setFirstName

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}//end setLastName

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}//end setDni
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}//end setEmail

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}//end setStreet
	
	/**
	 * @param neighborhood the neighborhood to set
	 */
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}//end setNeighborhood
	
	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}//end setNum
	
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
	
	public void setRealEstates(List<String> realEstates) {
		this.realEstates = realEstates;
	}//end setRealEstates
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}//end getFirstName
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}//end getLastName

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}//end getDni

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}//end getEmail

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

	public List<String> getRealEstates() {
		return realEstates;
	}//end getRealEstates

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dueño [Nombre= " + firstName + ", apellido= " + lastName
				+ ", dni= " + dni + ", email= " + email + ", ciudad= " + city
				+ ", CP= " + code + ", calle= " + street + ", num= " + num
				+ ", barrio= " + neighborhood + ", inmobiliarias= "
				+ realEstates + "]";
	}
}
