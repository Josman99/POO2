package com.unu.proyectoWebGB.controllers;

import com.unu.proyectoWebGB.beans.Autor;
import com.unu.proyectoWebGB.beans.Editorial;
import com.unu.proyectoWebGB.models.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import javax.management.modelmbean.ModelMBeanOperationInfo;

/**
 * Servlet implementation class EditorialController
 */
public class EditorialController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EditorialModel modelo = new EditorialModel();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditorialController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processReques(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getParameter("op") == null) {
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
				request.getRequestDispatcher("/editorial/nuevaEditorial.jsp").forward(request, response);
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
				ListError.add("Ingrese el nombre de la editorial");
			}
			if(request.getParameter("contacto").equals("")) {
				res = true;
				ListError.add("Ingrese el contacto de la editorial");
			}
			if(request.getParameter("telefono").equals("")) {
				res = true;
				ListError.add("Ingrese el telefono de la editorial");
			}
			request.setAttribute("respuesta", res);
			request.setAttribute("listaError", ListError);
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
    	return res;
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) {
    	try {
			request.setAttribute("listaEditorial", modelo.listaEditorial() );
			Iterator<Editorial> it = modelo.listaEditorial().iterator();
			while (it.hasNext()) {
				Editorial e = it.next();
				System.out.println(e.getIdEditorial()+" "+e.getNombreEditorial()+" "+e.getContactoEditorial()+" "+e.getTelefonoEditorial());
			}
			request.getRequestDispatcher("/editorial/listaEditorial.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("error al mostar desde el controlador: "+e.getMessage());
		}
    }
    
    private void insertar(HttpServletRequest request, HttpServletResponse response) {
		try {
			if(!validar(request, response)) {
				Editorial mieditorial = new Editorial();
				//miautor.setIdAutor(Integer.parseInt(request.getParameter("codigo")));
				mieditorial.setNombreEditorial(request.getParameter("nombre"));
				mieditorial.setContactoEditorial(request.getParameter("contacto"));
				mieditorial.setTelefonoEditorial(request.getParameter("telefono"));
				
				if(modelo.insertarEditorial(mieditorial)>0) {
					request.getSession().setAttribute("exito", "Autor registrado exitosamente");
					response.sendRedirect(request.getContextPath()+"/EditorialController?op=listar");
				} else {
					request.getSession().setAttribute("Fracaso", "Autor no registrado ya que hay otro autor con ese codigo ");
					response.sendRedirect(request.getContextPath()+"/EditorialController?op=listar");
				}
			}else {
				request.getRequestDispatcher("/editorial/nuevaEditorial.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			System.out.println("error en ingresear: "+e.getMessage());
		}
		
	}
    private void obtener(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			Editorial mieditorial = modelo.obtenerEditorial(Integer.parseInt(id));
			if(mieditorial != null) {
				request.setAttribute("editorial", mieditorial);
				request.getRequestDispatcher("/editorial/modificarEditorial.jsp").forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath()+"/error404.jsp");
			}
		} catch (Exception e) {
			System.out.println("error al obtener: "+e.getMessage());
		}
	}
    private void modificar(HttpServletRequest request, HttpServletResponse response) {
		try {
			Editorial mieditorial = new Editorial();
			mieditorial.setIdEditorial(Integer.parseInt(request.getParameter("id")));
			mieditorial.setNombreEditorial(request.getParameter("nombre"));
			mieditorial.setContactoEditorial(request.getParameter("contacto"));
			mieditorial.setTelefonoEditorial(request.getParameter("telefono"));
			request.setAttribute("editorial", mieditorial);
			if(modelo.modificarEditorial(mieditorial)>0) {
				request.getSession().setAttribute("exito", "editorial modificado exitosamente");
				response.sendRedirect(request.getContextPath() + "/EditorialController?op=listar");
			}
		} catch (Exception e) {
			System.out.println("error al modificar : "+e.getMessage());
		}
		
	}
    
    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		try {
			int idEditorial = Integer.parseInt(request.getParameter("id"));
			if (modelo.eliminarEditorial(idEditorial)>0) {
				request.setAttribute("Exito", "Autor eliminado");
			} else {
				request.setAttribute("Fracaso", "No se puede eliminar este Editorial");
			}
			request.getRequestDispatcher("/EditorialController?op=listar").forward(request, response);
		} catch (Exception e) {
			java.util.logging.Logger.getLogger(AutoresController.class.getName()).log(Level.SEVERE, null, e.getMessage());
		}
	}
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processReques(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processReques(request, response);
	}

}
