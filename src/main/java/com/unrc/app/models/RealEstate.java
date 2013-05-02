package com.unrc.app.models;

public class RealEstate {
		String name;
		String number_phone;
		String email;
		String web_site;
		Adress adress;
		LinkedList<Owner> list = new LinkedList<Owner>;
	

	public RealEstate() {
		name = null;
		number_phone = null;
		email = null;
		adress = null;
		web_site = null;
	}
	
	public RealEstate(String name, String number_phone, String email, String web
			Adress adress, LinkedList<Owner> list) {
		super();
		this.name = name;
		this.number_phone = number_phone;
		this.email = email;
		this.web_site = web;
		this.adress = adress;
		this.list = list;
	}

	//Getters
	public String getName() {
		return name;
	}
	
	public String getNumber_phone() {
		return number_phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getWeb_site() {
		return web_site;
	}
	
	public Adress getAdress() {
		return adress;
	}
	
	public LinkedList<Owner> getList() {
		return list;
	}
	
	//Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNumber_phone(String number_phone) {
		this.number_phone = number_phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setWeb_site(String web_site) {
		this.web_site = web_site;
	}
	
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
	
	public void setList(LinkedList<Owner> list) {
		this.list = list;
	}

	//Agregar owner a RealEstate
	public void AddList(Owner ow){
		list.Add(ow);
	}
	
}
