/*  Analisis Y Diseño De Sistemas(3303)
 *         Año 2013
 * Proyecto:Web para informatizar revista inmobiliaria  
 * Jaimez Jacinto, Pereyra Orcasitas Nicolas, Zensich Ezequiel Zensich
 */

package com.unrc.app.object;

public class ObjectBuilding {

	private String description;//Descripcion
	private String price;//Precio
	private String offer;//Oferta
	private String type;//Tipo
	private String street;//calle
	private String num;//numero de domicilio
	private String neighborhood;//barrio
	private String city;//ciudad
	private int code;//codigo postal
	private String dniOwner;//dni del dueño del inmueble
	private String nameRealEstate; //Nombre de la inmobiliaria que lo publicó

	//Constructor vacio
	public ObjectBuilding() {
		description=null;
		price=null;
		offer=null;
		type=null;
		street=null;
		num=null;
		neighborhood=null;
		city=null;
		code=-1;
		dniOwner=null;
		nameRealEstate=null;		
	}//end constructor
		
	/**
	 * @param description
	 * @param price
	 * @param offer
	 * @param type
	 * @param street
	 * @param num
	 * @param neighborhood
	 * @param city
	 * @param code
	 * @param dniOwner
	 * @param nameRealEstate
	 */
	public ObjectBuilding(String description, String price, String offer,
		String type, String street, String num, String neighborhood,
		String city, int code, String dniOwner, String nameRealEstate) {
		this.description = description;
		this.price = price;
		this.offer = offer;
		this.type = type;
		this.street = street;
		this.num = num;
		this.neighborhood = neighborhood;
		this.city = city;
		this.code = code;
		this.dniOwner = dniOwner;
		this.nameRealEstate = nameRealEstate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the offer
	 */
	public String getOffer() {
		return offer;
	}

	/**
	 * @param offer the offer to set
	 */
	public void setOffer(String offer) {
		this.offer = offer;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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

	/**
	 * @return the dniOwner
	 */
	public String getDniOwner() {
		return dniOwner;
	}

	/**
	 * @param dniOwner the dniOwner to set
	 */
	public void setDniOwner(String dniOwner) {
		this.dniOwner = dniOwner;
	}

	/**
	 * @return the nameRealEstate
	 */
	public String getNameRealEstate() {
		return nameRealEstate;
	}

	/**
	 * @param nameRealEstate the nameRealEstate to set
	 */
	public void setNameRealEstate(String nameRealEstate) {
		this.nameRealEstate = nameRealEstate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Inumebe [descripcion=" + description + ", precio="
				+ price + ", tipoOferta=" + offer + ", tipoInmueble=" + type + ", calle="
				+ street + ", numero=" + num + ", barrio=" + neighborhood
				+ ", ciudad=" + city + ", CP=" + code + ", dni dueño="
				+ dniOwner + ", nombre inmobiliaria=" + nameRealEstate + "]";
	}	
	
}