package com.unu.proyectoWebGB.models;
import java.lang.System.Logger;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;
import com.unu.proyectoWebGB.beans.*;
import com.unu.proyectoWebGB.controllers.AutoresController;


public class AutoresModel  extends Conexion{

	CallableStatement cs;
	ResultSet rs;
	
	/*
	public List<Autor> listarAutores(){
		
		ArrayList<Autor> autores = new ArrayList<>();
		autores.add(new Autor(1,"García Marquez", "Colombiana"));
		autores.add(new Autor(2,"Borges", "Argentina"));
		autores.add(new Autor(3,"Allende", "Chilena"));
		return autores;
	}*/
	
	public List<Autor> listarAutores() throws SQLException{
		try {
			List<Autor> lista = new ArrayList<>();
			String sql = "CALL sp_listarAutor()";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				Autor autor = new Autor();
				autor.setIdAutor(rs.getInt("id_autor"));
				autor.setNombre(rs.getString("nombre_autor"));
				autor.setNacionalidad(rs.getString("nacionalidad_autor"));
				lista.add(autor);
			}
			this.cerrarConexion();
			return lista;
			
		} catch (SQLException ex) {
			
			java.util.logging.Logger.getLogger(AutoresController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			this.cerrarConexion();
			return null;
		}
	}
	
	public int insertarAutor(Autor autor) {
		try {
			int filasAfectadas=0;
			String sql="CALL sp_insertarAutor(?,?)";
			this.abrirConexion();
			cs = conexion.prepareCall(sql);
			//cs.setInt(1, autor.getIdAutor());
			cs.setString(1, autor.getNombre());
			cs.setString(2, autor.getNacionalidad());
			filasAfectadas=cs.executeUpdate();
			return filasAfectadas;
			
		} catch (Exception e) {
			System.out.println("error en: "+e.getMessage());
			this.cerrarConexion();
			return 0;
		}

	}
	
}
