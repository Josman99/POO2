package ProyectoWeb.models;

import java.util.ArrayList;
import java.util.List;
import ProyectoWeb.beans.*;

public class AutoresModel {
	public List<Autor> listarAutores(){
		ArrayList<Autor> autores  = new ArrayList<>();
		autores.add(new Autor(1, "Colombiano", "Garcia Marquez"));
		autores.add(new Autor(2, "Argentino", "Borges"));
		autores.add(new Autor(3, "Chilena", "Allende"));
		return autores;
	}
}
