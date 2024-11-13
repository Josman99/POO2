package com.unu.proyectoWebGB.models;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.unu.proyectoWebGB.beans.Libro;
import com.unu.proyectoWebGB.controllers.LibrosController;

public class LibroModel extends Conexion {
	CallableStatement cs;
	ResultSet rs;
	
	public List<Libro> listarLibros() throws SQLException{
		try {
			List<Libro> lista = new ArrayList<>();
			String sql = "CALL sp_listarLibro2()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Libro libro = new Libro();
				libro.setIdLib(rs.getInt("id_libro"));
				libro.setNombreLib(rs.getString("nombre_libro"));
				libro.setExistencias(rs.getInt("existencias"));
				libro.setPrecio(rs.getDouble("precio_libro"));
				//libro.setIdAut(rs.getInt("idAutor"));
				libro.setNombreAut(rs.getString("nombre_autor"));
				//libro.setIdEdi(rs.getInt("idEditorial"));
				libro.setNombreEdito(rs.getString("nombre_editorial"));
				//libro.setIdGene(rs.getInt("idGenero"));
				libro.setNombreGene(rs.getString("nombre_genero"));
				libro.setDescripcion(rs.getString("descripcion"));
				lista.add(libro);
			}
			this.cerrarConexion();
			return lista;
			
		} catch (SQLException ex) {
			
			java.util.logging.Logger.getLogger(LibrosController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			this.cerrarConexion();
			return null;
		}
	}
	
	public int insertarLibro(Libro libro) {
		try {
			int filasAfectadas=0;
			String sql="CALL sp_insertarLibro(?,?,?,?,?,?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			//cs.setInt(1, autor.getIdAutor());
			cs.setString(1, libro.getNombreLib());
			cs.setInt(2, libro.getExistencias());
			cs.setDouble(3, libro.getPrecio());
			cs.setInt(4, libro.getIdAut());
			cs.setInt(5, libro.getIdEdi());
			cs.setInt(6, libro.getIdGene());
			cs.setString(7, libro.getDescripcion());
			filasAfectadas=cs.executeUpdate();
			return filasAfectadas;
			
		} catch (Exception e) {
			System.out.println("error en: "+e.getMessage());
			this.cerrarConexion();
			return 0;
		}

	}
	
	public int modificarrLibro(Libro libro) {
		try {
			int filasAfectadas=0;
			String sql="CALL sp_modificarLibro(?,?,?,?,?,?,?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, libro.getIdLib());
			cs.setString(2, libro.getNombreLib());
			cs.setInt(3, libro.getExistencias());
			cs.setDouble(4, libro.getPrecio());
			cs.setInt(5, libro.getIdAut());
			cs.setInt(6, libro.getIdEdi());
			cs.setInt(7, libro.getIdGene());
			cs.setString(8, libro.getDescripcion());
			filasAfectadas=cs.executeUpdate();
			this.cerrarConexion();
			return filasAfectadas;
			
		} catch (Exception e) {
			System.out.println("error en: "+e.getMessage());
			this.cerrarConexion();
			return 0;
		}

	}
	
	public Libro obtenerLibro(int idlibro) {
		Libro libro = new Libro();
		try {
			String sql = "CALL sp_obtenerLibro2(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idlibro);
			rs = cs.executeQuery();
			if(rs.next()) {
				
				libro.setIdLib(rs.getInt("id_libro"));
				libro.setNombreLib(rs.getString("nombre_libro"));
				libro.setExistencias(rs.getInt("existencias"));
				libro.setPrecio(rs.getDouble("precio_libro"));
				libro.setIdAut(rs.getInt("idAutor"));
				libro.setNombreAut(rs.getString("nombre_autor"));
				libro.setIdAut(rs.getInt("idEditorial"));
				libro.setNombreEdito(rs.getString("nombre_editorial"));
				libro.setIdAut(rs.getInt("idGenero"));
				libro.setNombreGene(rs.getString("nombre_genero"));
				libro.setDescripcion(rs.getString("descripcion"));
				this.cerrarConexion();
				return libro;
			}
		} catch (Exception e) {
			this.cerrarConexion();
			return null;
		}
		return libro;
	}
	
	public int eliminarLibro(int id)  {
		try {
			int filasEliminadas=0;
			String sql = "CALL sp_eliminarLibro(?)";
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
	
	public int totalLibros() throws SQLException {
		try {
			int totallib = 0;
			String sql = "SELECT COUNT(id_libro) as totallib FROM libro";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();

			while (rs.next()) {
				totallib = rs.getInt("totallib");
			}
			return totallib;
			
		} catch (Exception e) {
			System.out.println("error al contar autores: "+e.getMessage());
			return 0;
		} finally {
			this.cerrarConexion();
		}
	}
}
