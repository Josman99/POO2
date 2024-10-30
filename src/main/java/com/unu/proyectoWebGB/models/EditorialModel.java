package com.unu.proyectoWebGB.models;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.unu.proyectoWebGB.beans.Autor;
import com.unu.proyectoWebGB.beans.Editorial;
import com.unu.proyectoWebGB.controllers.AutoresController;

public class EditorialModel extends Conexion {

	CallableStatement cs;
	ResultSet rs;
	
	public List<Editorial> listaEditorial(){
		try {
			List<Editorial> lista = new ArrayList<>();
			String sql = "CALL sp_listarEditorial()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				Editorial editorial = new Editorial();
				editorial.setIdEditorial(rs.getInt("id_editorial"));
				editorial.setNombreEditorial(rs.getString("nombre_editorial"));
				editorial.setContactoEditorial(rs.getString("contacto_editorial"));
				editorial.setTelefonoEditorial(rs.getString("telefono"));
				lista.add(editorial);
			}
			this.cerrarConexion();
			return lista;
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(AutoresController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			this.cerrarConexion();
			return null;
		}
	}
	public int insertarEditorial(Editorial editorial) {
		try {
			int filasAfectadas=0;
			String sql="CALL sp_insertarEditorial(?,?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			//cs.setInt(1, autor.getIdAutor());
			cs.setString(1, editorial.getNombreEditorial());
			cs.setString(2, editorial.getContactoEditorial());
			cs.setString(3, editorial.getTelefonoEditorial());
			filasAfectadas=cs.executeUpdate();
			return filasAfectadas;
			
		} catch (Exception e) {
			System.out.println("error en: "+e.getMessage());
			this.cerrarConexion();
			return 0;
		}
	}
	public int modificarEditorial(Editorial editorial) {
		try {
			int filasAfectadas=0;
			String sql="CALL sp_modificarEditorial(?,?,?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, editorial.getIdEditorial());
			cs.setString(2, editorial.getNombreEditorial());
			cs.setString(3, editorial.getContactoEditorial());
			cs.setString(4, editorial.getTelefonoEditorial());
			filasAfectadas=cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas;
			
		} catch (Exception e) {
			System.out.println("error en modificar edit: "+e.getMessage());
			this.cerrarConexion();
			return 0;
		}

	}
	
	public int eliminarEditorial(int id)  {
		try {
			int filasEliminadas=0;
			String sql = "CALL sp_eliminarEditorial(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, id);
			filasEliminadas=cs.executeUpdate();
			this.cerrarConexion();
			return filasEliminadas;
			
		} catch (Exception e) {
			System.out.println("no se puede eliminar: "+e.getMessage());
			return 0;
		}
	}
	public Editorial obtenerEditorial(int idEditorial) {
		Editorial editorial = new Editorial();
		try {
			String sql = "CALL sp_obtenerEditorial(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idEditorial);
			rs = cs.executeQuery();
			if(rs.next()) {
				
				editorial.setIdEditorial(rs.getInt("id_editorial"));
				editorial.setNombreEditorial(rs.getString("nombre_editorial"));
				editorial.setContactoEditorial(rs.getString("contacto_editorial"));
				editorial.setTelefonoEditorial(rs.getString("telefono"));
				this.cerrarConexion();
				return editorial;
			}
		} catch (Exception e) {
			this.cerrarConexion();
			return null;
		}
		return editorial;
		
	}
}
