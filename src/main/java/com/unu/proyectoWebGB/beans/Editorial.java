package com.unu.proyectoWebGB.beans;

public class Editorial {
	
	public Editorial(int idEditorial, String nombreEditorial, String contactoEditorial, String telefonoEditorial) {
		super();
		this.idEditorial = idEditorial;
		this.nombreEditorial = nombreEditorial;
		this.contactoEditorial = contactoEditorial;
		this.telefonoEditorial = telefonoEditorial;
	}
	
	public Editorial() {
		this(0, "", "","");
	}

	private int idEditorial;
	private String nombreEditorial;
	private String contactoEditorial;
	private String telefonoEditorial;
	public int getIdEditorial() {
		return idEditorial;
	}

	public void setIdEditorial(int idEditorial) {
		this.idEditorial = idEditorial;
	}

	public String getNombreEditorial() {
		return nombreEditorial;
	}

	public void setNombreEditorial(String nombreEditorial) {
		this.nombreEditorial = nombreEditorial;
	}

	public String getContactoEditorial() {
		return contactoEditorial;
	}

	public void setContactoEditorial(String contactoEditorial) {
		this.contactoEditorial = contactoEditorial;
	}

	public String getTelefonoEditorial() {
		return telefonoEditorial;
	}

	public void setTelefonoEditorial(String telefonoEditorial) {
		this.telefonoEditorial = telefonoEditorial;
	}
	
}
