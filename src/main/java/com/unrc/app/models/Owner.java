package com.unrc.app.models;
public class Owner {

	private String firstName;

	private String lastName;

	private String email;

	private Integer phone;

	private String dni;
	
	public Owner(){
		firstName=null;
		lastName=null;
		email=null;
		phone=null;
		dni=null;	
	}
	
	public Owner(String name, String lastName, String mail, Integer numPhone,String numDni ){
		firstName=name;
		this.lastName=lastName;
		email=mail;
		phone=numPhone;
		dni=numDni;		

	}
	public void setFirstName(String name) {
		firstName=name;
	}

	public void setLastName(String name) {
		lastName=name;
	}

	public void setEmail(String mail) {
		email=mail;
	}

	public void setPhone(Integer number) {
		phone=number;
	}

	public void setDni(String numdni) {
		dni=numdni;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Integer getPhone() {
		return phone;
	}

	public String getDni(){
		return dni;
	}


}
