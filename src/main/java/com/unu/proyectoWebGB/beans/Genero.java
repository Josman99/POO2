package com.unu.proyectoWebGB.beans;

public class Genero {
	
	private int idGenero;
	private String nombreGenero;
	private String descripGenero;
	
	public Genero(int idGenero, String nombreGenero, String descripGenero) {
		super();
		this.idGenero = idGenero;
		this.nombreGenero = nombreGenero;
		this.descripGenero = descripGenero;
	}

	public Genero() {
		this(0, "", "");
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

	public String getDescripGenero() {
		return descripGenero;
	}

	public void setDescripGenero(String descripGenero) {
		this.descripGenero = descripGenero;
	}
	
}
