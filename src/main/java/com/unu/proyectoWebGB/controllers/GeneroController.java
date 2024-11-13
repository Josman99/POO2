package com.unu.proyectoWebGB.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import com.unu.proyectoWebGB.beans.Autor;
import com.unu.proyectoWebGB.beans.Genero;
import com.unu.proyectoWebGB.models.GeneroModel;

/**
 * Servlet implementation class GeneroController
 */
public class GeneroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GeneroModel modelo = new GeneroModel();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeneroController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getParameter("op")==null) {
    		Listar(request, response);
    		return;
    	}
    	
    	String operacion = request.getParameter("op");
    	switch (operacion) {
		case "listar":
			Listar(request, response);
			break;
		case "nuevo":
			try {
				request.getRequestDispatcher("/genero/nuevoGenero.jsp").forward(request, response);
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
    
    private boolean validar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	boolean res = false;
    	List<String> ListError = new ArrayList<>();
    	try {
			if(request.getParameter("nombre").equals("")) {
				res = true;
				ListError.add("Ingrese el nombre del genero");
			}
			if(request.getParameter("descripcion").equals("")) {
				res = true;
				ListError.add("Ingrese la descripcion del genero");
			}
			request.setAttribute("respuesta", res);
			request.setAttribute("listaError", ListError);
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
    	return res;
    }
    
    private void Listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			request.setAttribute("listaGeneros", modelo.listarGenero());
			Iterator<Genero> it = modelo.listarGenero().iterator();
			while (it.hasNext()) {
				Genero g = it.next();
				System.out.println(g.getIdGenero()+" "+g.getNombreGenero()+" "+g.getDescripGenero());
			}
			request.getRequestDispatcher("/genero/listaGeneros.jsp").forward(request, response);
		} catch (SQLException ex) {
			java.util.logging.Logger.getLogger(AutoresController.class.getName()).log(Level.SEVERE, null, ex);
		}
    }

    private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {
			if(!validar(request, response)) {
				Genero miGenero = new Genero();
				//miautor.setIdAutor(Integer.parseInt(request.getParameter("codigo")));
				miGenero.setNombreGenero(request.getParameter("nombre"));
				miGenero.setDescripGenero(request.getParameter("descripcion"));
				
				if(modelo.insertarGenero(miGenero)>0) {
					request.getSession().setAttribute("exito", "Genero registrado exitosamente");
					response.sendRedirect(request.getContextPath()+"/GeneroController?op=listar");
				} else {
					request.getSession().setAttribute("Fracaso", "Autor no registrado ya que hay otro autor con ese codigo ");
					response.sendRedirect(request.getContextPath()+"/GeneroController?op=listar");
				}
			} else {
				request.getRequestDispatcher("/genero/nuevoGenero.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println("error en ingresear: "+e.getMessage());
		}
		
	}
    private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			Genero miGenero = new Genero();
			miGenero.setIdGenero(Integer.parseInt(request.getParameter("id")));
			miGenero.setNombreGenero(request.getParameter("nombre"));
			miGenero.setDescripGenero(request.getParameter("descripcion"));
			
			if(modelo.modificarGenero(miGenero)>0) {
				request.getSession().setAttribute("exito", "Genero registrado exitosamente");
				response.sendRedirect(request.getContextPath()+"/GeneroController?op=listar");
			} else {
				request.getSession().setAttribute("Fracaso", "Genero no registrado ya que hay otro autor con ese codigo ");
				response.sendRedirect(request.getContextPath()+"/GeneroController?op=listar");
			}
		} catch (Exception e) {
			System.out.println("error en ingresear: "+e.getMessage());
		}
		
	}
    private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			Genero miGenero = modelo.obtenerGenero(Integer.parseInt(id));
			if(miGenero != null) {
				request.setAttribute("genero", miGenero);
				request.getRequestDispatcher("/genero/modificarGenero.jsp").forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath()+"/error404.jsp");
			}
		} catch (Exception e) {
			System.out.println("error al obtener: "+e.getMessage());
		}
	}
    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			int idGenero = Integer.parseInt(request.getParameter("id"));
			if (modelo.eliminarGenero(idGenero)>0) {
				request.setAttribute("Exito", "Genero eliminado");
			} else {
				request.setAttribute("Fracaso", "No se puede eliminar este genero");
			}
			request.getRequestDispatcher("/GeneroController?op=listar").forward(request, response);
		} catch (Exception e) {
			java.util.logging.Logger.getLogger(AutoresController.class.getName()).log(Level.SEVERE, null, e.getMessage());
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
