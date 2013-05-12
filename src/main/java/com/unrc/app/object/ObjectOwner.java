package com.unrc.app.object;

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
	}

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
	 */
	public ObjectOwner(String firstName, String lastName, String dni,
			String email, String city, int code, String street, String num,
			String neighborhood) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.email = email;
		this.city = city;
		this.code = code;
		this.street = street;
		this.num = num;
		this.neighborhood = neighborhood;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}

	/**
	 * @return the neighborhood
	 */
	public String getNeighborhood() {
		return neighborhood;
	}

	/**
	 * @param neighborhood the neighborhood to set
	 */
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Nombre: " + firstName + ", Apellido: " + lastName
				+ ", dni: " + dni  + ", Ciudad: " + city
				+ ", CP: " + code + ", Direccion: " + street + " " + num
				+ ",  Barrio " + neighborhood + ", email: " + email;
	}

	
}
