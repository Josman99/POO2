package com.unu.proyectoWebGB.controllers;

import jakarta.servlet.RequestDispatcher;
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
		case "obtener":
			obtener(request, response);
			break;
		
		case "modificar":
			modificar(request, response);
			break;
			
		case "eliminar":
			eliminar(request, response);
			break;

		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int total = modelo.totalAutores();
			request.setAttribute("listaAutores", modelo.listarAutores());
			Iterator<Autor> it = modelo.listarAutores().iterator();
			while(it.hasNext()) {
				Autor a = it.next();
				System.out.println(a.getIdAutor()+" "+a.getNombre()+" "+a.getNacionalidad());
			}
			System.out.println("total de autores: "+total);
			request.getRequestDispatcher("/autores/listaAutores.jsp").forward(request, response);
			
		} catch (SQLException ex) {
			java.util.logging.Logger.getLogger(AutoresController.class.getName()).log(Level.SEVERE, null, ex);
		}
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
	
	private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			Autor miAutor = modelo.obtenerAutor(Integer.parseInt(id));
			if(miAutor != null) {
				request.setAttribute("autor", miAutor);
				request.getRequestDispatcher("/autores/editarAutores.jsp").forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath()+"/error404.jsp");
			}
		} catch (Exception e) {
			System.out.println("error al obtener: "+e.getMessage());
		}
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			Autor miAutor = new Autor();
			miAutor.setIdAutor(Integer.parseInt(request.getParameter("id")));
			miAutor.setNombre(request.getParameter("nombre"));
			miAutor.setNacionalidad(request.getParameter("nacionalidad"));
			request.setAttribute("autor", miAutor);
			if(modelo.modificarrAutor(miAutor)>0) {
				request.getSession().setAttribute("exito", "autor modificado exitosamente");
				response.sendRedirect(request.getContextPath() + "/AutoresController?op=listar");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			int idAutor = Integer.parseInt(request.getParameter("id"));
			if (modelo.eliminarAutor(idAutor)>0) {
				request.setAttribute("Exito", "Autor eliminado");
			} else {
				request.setAttribute("Fracaso", "No se puede eliminar este autor");
			}
			request.getRequestDispatcher("/AutoresController?op=listar").forward(request, response);
		} catch (Exception e) {
			java.util.logging.Logger.getLogger(AutoresController.class.getName()).log(Level.SEVERE, null, e.getMessage());
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
