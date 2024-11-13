package com.unu.proyectoWebGB.models;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unu.proyectoWebGB.beans.Autor;
import com.unu.proyectoWebGB.beans.Genero;
import com.unu.proyectoWebGB.controllers.AutoresController;

public class GeneroModel extends Conexion {
	CallableStatement cs;
	ResultSet rs;
	
	public List<Genero> listarGenero() throws SQLException {
		try {
			List<Genero> lista = new ArrayList<>();
			String sql = "CALL sp_listarGenero()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while (rs.next()) {
				Genero genero = new Genero();
				genero.setIdGenero(rs.getInt("id_genero"));
				genero.setNombreGenero(rs.getNString("nombre_genero"));
				genero.setDescripGenero(rs.getString("descripcion_genero"));
				lista.add(genero);
			}
			this.cerrarConexion();
			return lista;
			
		} catch (SQLException ex) {
			java.util.logging.Logger.getLogger(AutoresController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			this.cerrarConexion();
			return null;
		}
	}
	
	public int insertarGenero(Genero genero) {
		try {
			int filasAfectadas=0;
			String sql="CALL sp_insertarGenero(?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			//cs.setInt(1, autor.getIdAutor());
			cs.setString(1, genero.getNombreGenero());
			cs.setString(2, genero.getDescripGenero());
			filasAfectadas=cs.executeUpdate();
			return filasAfectadas;
			
		} catch (Exception e) {
			System.out.println("error en: "+e.getMessage());
			this.cerrarConexion();
			return 0;
		}

	}
	
	public int modificarGenero(Genero genero) {
		try {
			int filasAfectadas=0;
			String sql="CALL sp_modificarGenero(?,?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, genero.getIdGenero());
			cs.setString(2, genero.getNombreGenero());
			cs.setString(3, genero.getDescripGenero());
			filasAfectadas=cs.executeUpdate();
			return filasAfectadas;
			
		} catch (Exception e) {
			System.out.println("error en: "+e.getMessage());
			this.cerrarConexion();
			return 0;
		}
	}
	public Genero obtenerGenero(int idGenero) {
		Genero genero = new Genero();
		try {
			String sql = "CALL sp_obtenerGenero(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idGenero);
			rs = cs.executeQuery();
			if(rs.next()) {
				
				genero.setIdGenero(rs.getInt("id_genero"));
				genero.setNombreGenero(rs.getString("nombre_genero"));
				genero.setDescripGenero(rs.getString("descripcion_genero"));
				this.cerrarConexion();
				return genero;
			}
		} catch (Exception e) {
			this.cerrarConexion();
			return null;
		}
		return genero;
	}
	public int eliminarGenero(int idGenero) {
		try {
			int filasAfectadas=0;
			String sql="CALL sp_eliminarGenero(?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			cs.setInt(1, idGenero);
			filasAfectadas=cs.executeUpdate();
			return filasAfectadas;
			
		} catch (Exception e) {
			System.out.println("error al eliminar en el modelo: "+e.getMessage());
			this.cerrarConexion();
			return 0;
		}
	}
}
