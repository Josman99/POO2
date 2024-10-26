package com.unu.proyectoWebGB.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.System.Logger;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;

import com.unu.proyectoWebGB.beans.Autor;
import com.unu.proyectoWebGB.models.AutoresModel;

/**
 * Servlet implementation class AutoresController
 */
public class AutoresController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AutoresModel modelo = new AutoresModel();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AutoresController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("op") == null) {
			listar(request, response);
			return;
		}
		String operacion = request.getParameter("op");
		switch (operacion) {

		case "listar":
			listar(request, response);
			break;

		case "nuevo":
			try {
				request.getRequestDispatcher("/autores/nuevoAutor.jsp").forward(request, response);
			} catch (Exception e) {
				System.out.println("error en: "+e.getMessage());
			}
			
			break;
		case "ingresar":
			insertar(request, response);
			break;

		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listaAutores", modelo.listarAutores());
			Iterator<Autor> it = modelo.listarAutores().iterator();
			while(it.hasNext()) {
				Autor a = it.next();
				System.out.println(a.getIdAutor()+" "+a.getNombre()+" "+a.getNacionalidad());
			}
			request.getRequestDispatcher("/autores/listaAutores.jsp").forward(request, response);
			
		} catch (SQLException ex) {
			java.util.logging.Logger.getLogger(AutoresController.class.getName()).log(Level.SEVERE, null, ex);
		}
		request.getRequestDispatcher("/autores/listaAutores.jsp").forward(request, response);
	}
	
	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {
			Autor miautor = new Autor();
			//miautor.setIdAutor(Integer.parseInt(request.getParameter("codigo")));
			miautor.setNombre(request.getParameter("nombre"));
			miautor.setNacionalidad(request.getParameter("nacionalidad"));
			
			if(modelo.insertarAutor(miautor)>0) {
				request.getSession().setAttribute("exito", "Autor registrado exitosamente");
				response.sendRedirect(request.getContextPath()+"/AutoresController?op=listar");
			} else {
				request.getSession().setAttribute("Fracaso", "Autor no registrado ya que hay otro autor con ese codigo ");
				response.sendRedirect(request.getContextPath()+"/AutoresController?op=listar");
			}
		} catch (Exception e) {
			System.out.println("error en ingresear: "+e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
