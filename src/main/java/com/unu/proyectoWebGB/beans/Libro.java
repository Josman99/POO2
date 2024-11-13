package com.unu.proyectoWebGB.beans;

public class Libro {
	private int idLib;
	private String nombreLib;
	private int existencias;
	private double precio;
	private int idAut;
	private int idEdi;
	private int idGene;
	private String descripcion;
	
	private String nombreAut;
	private String nombreEdito;
	private String nombreGene;
	
	public Libro(int idLib, String nombreLib, int existencias, double precio, int idAut, int idEdi, int idGene, String descripcion) {
		super();
		this.idLib = idLib;
		this.nombreLib = nombreLib;
		this.existencias = existencias;
		this.precio = precio;
		this.idAut = idAut;
		this.idEdi = idEdi;
		this.idGene = idGene;
		this.descripcion = descripcion;
	}
	
	public Libro() {
		this(0,"",0,0.0,0,0,0,"");
	}

	public int getIdLib() {
		return idLib;
	}

	public void setIdLib(int idLib) {
		this.idLib = idLib;
	}

	public String getNombreLib() {
		return nombreLib;
	}

	public void setNombreLib(String nombreLib) {
		this.nombreLib = nombreLib;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getIdAut() {
		return idAut;
	}

	public void setIdAut(int idAut) {
		this.idAut = idAut;
	}

	public int getIdEdi() {
		return idEdi;
	}

	public void setIdEdi(int idEdi) {
		this.idEdi = idEdi;
	}

	public int getIdGene() {
		return idGene;
	}

	public void setIdGene(int idGene) {
		this.idGene = idGene;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreAut() {
		return nombreAut;
	}

	public void setNombreAut(String nombreAut) {
		this.nombreAut = nombreAut;
	}

	public String getNombreEdito() {
		return nombreEdito;
	}

	public void setNombreEdito(String nombreEdito) {
		this.nombreEdito = nombreEdito;
	}

	public String getNombreGene() {
		return nombreGene;
	}

	public void setNombreGene(String nombreGene) {
		this.nombreGene = nombreGene;
	}
	
	
	
}
