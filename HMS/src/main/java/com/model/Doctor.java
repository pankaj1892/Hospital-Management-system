package com.model;

public class Doctor {
	private int id;
	private String name;
	private String specialization;
	private String contact;

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Doctor(int id, String name, String specialization, String contact) {
		this.id = id;
		this.name = name;
		this.specialization = specialization;
		this.contact = contact;
	}
	public Doctor(String name, String specialization, String contact) {
		this.name = name;
		this.specialization = specialization;
		this.contact = contact;
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public String getContact() {
		return contact;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

}
