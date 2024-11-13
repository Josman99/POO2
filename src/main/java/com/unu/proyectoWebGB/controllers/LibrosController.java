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
import com.unu.proyectoWebGB.beans.Libro;
import com.unu.proyectoWebGB.models.LibroModel;

/**
 * Servlet implementation class LibrosController
 */
public class LibrosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LibroModel modelo = new LibroModel();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrosController() {
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
				request.getRequestDispatcher("/libro/nuevoLibro.jsp").forward(request, response);
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
				ListError.add("Ingrese el nombre del LIBRO");
			}
			if(request.getParameter("existencias").equals("")) {
				res = true;
				ListError.add("Ingrese las existencias del LIBRO");
			}
			if(request.getParameter("precio").equals("")) {
				res = true;
				ListError.add("Ingrese el precio del LIBRO");
			}
			if(request.getParameter("idautor").equals("0")) {
				res = true;
				ListError.add("Ingrese el autor del LIBRO");
			}
			if(request.getParameter("editorial").equals("0")) {
				res = true;
				ListError.add("Ingrese la editorial del LIBRO");
			}
			if(request.getParameter("genero").equals("0")) {
				res = true;
				ListError.add("Ingrese el genero del LIBRO");
			}
			if(request.getParameter("descripcion").equals("")) {
				res = true;
				ListError.add("Ingrese la descripcion del LIBRO");
			}
			request.setAttribute("respuesta", res);
			request.setAttribute("listaError", ListError);
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
    	
    	return res;
    }
    
    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int total = modelo.totalLibros();
			request.setAttribute("listaLibros", modelo.listarLibros());
			Iterator<Libro> it = modelo.listarLibros().iterator();
			while(it.hasNext()) {
				Libro l = it.next();
				System.out.println(l.getIdLib()+" "+l.getNombreLib()+" "+l.getExistencias()+" "+l.getIdAut());
			}
			System.out.println("total de libros: "+total);
			request.getRequestDispatcher("/libro/listaLibros.jsp").forward(request, response);
			
		} catch (SQLException ex) {
			java.util.logging.Logger.getLogger(AutoresController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
    
    private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {
			if(!validar(request, response)) {
				
				Libro milibro = new Libro();
				//miautor.setIdAutor(Integer.parseInt(request.getParameter("codigo")));
				milibro.setNombreLib(request.getParameter("nombre"));
				milibro.setExistencias(Integer.parseInt(request.getParameter("existencias")));
				milibro.setPrecio(Integer.parseInt(request.getParameter("precio")));
				milibro.setIdAut(Integer.parseInt(request.getParameter("idautor")));
				milibro.setIdEdi(Integer.parseInt(request.getParameter("editorial")));
				milibro.setIdGene(Integer.parseInt(request.getParameter("genero")));
				milibro.setDescripcion(request.getParameter("descripcion"));
				
				if(modelo.insertarLibro(milibro)>0) {
					request.getSession().setAttribute("exito", "Libro registrado exitosamente");
					response.sendRedirect(request.getContextPath()+"/LibrosController?op=listar");
				} else {
					request.getSession().setAttribute("Fracaso", "Libro no registrado ya que hay otro autor con ese codigo ");
					response.sendRedirect(request.getContextPath()+"/LibrosController?op=listar");
				}
				
			} else {
				request.getRequestDispatcher("/libro/nuevoLibro.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println("error en ingresear: "+e.getMessage());
		}
		
	}
    private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			Libro miLibro = modelo.obtenerLibro(Integer.parseInt(id));
			if(miLibro != null) {
				request.setAttribute("libro", miLibro);
				request.getRequestDispatcher("/libro/modificarLibro.jsp").forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath()+"/error404.jsp");
			}
		} catch (Exception e) {
			System.out.println("error al obtener: "+e.getMessage());
		}
	}
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			Libro miLibro = new Libro();
			miLibro.setIdLib(Integer.parseInt(request.getParameter("id")));;
			miLibro.setNombreLib(request.getParameter("nombre"));
			miLibro.setExistencias(Integer.parseInt(request.getParameter("existencias")));
			miLibro.setPrecio(Double.parseDouble(request.getParameter("precio")));
			miLibro.setIdAut(Integer.parseInt(request.getParameter("idautor")));
			miLibro.setIdEdi(Integer.parseInt(request.getParameter("editorial")));
			miLibro.setIdGene(Integer.parseInt(request.getParameter("genero")));
			miLibro.setDescripcion(request.getParameter("descripcion"));
			request.setAttribute("libro", miLibro);
			if(modelo.modificarrLibro(miLibro)>0) {
				request.getSession().setAttribute("exito", "Libro modificado exitosamente");
				response.sendRedirect(request.getContextPath() + "/LibrosController?op=listar");
			}
		} catch (Exception e) {
			System.out.println("Error al modificar: "+e.getMessage());
		}
		
	}
	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			int idLibro = Integer.parseInt(request.getParameter("id"));
			if (modelo.eliminarLibro(idLibro)>0) {
				request.setAttribute("Exito", "Libro eliminado");
			} else {
				request.setAttribute("Fracaso", "No se puede eliminar este Libro");
			}
			request.getRequestDispatcher("/AutoresController?op=listar").forward(request, response);
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
