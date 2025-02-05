package com.model;

public class Patient {
	private int id;
	private String name;
	private int age;
	private String contact;
	private String ailment;

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(int id, String name, int age, String contact, String ailment) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.contact = contact;
		this.ailment = ailment;
	}

	public Patient( String name, int age, String contact, String ailment) {
		this.name = name;
		this.age = age;
		this.contact = contact;
		this.ailment = ailment;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAilment() {
		return ailment;
	}

	public void setAilment(String ailment) {
		this.ailment = ailment;
	}
}