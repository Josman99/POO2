package com.unu.proyectoWebGB.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class Conexion {
	private String url = "jdbc:mysql://localhost:3306/nuevo_esquema";// URL de la base de datos
	private String usuario = "root";// Usuario de la base de datos
	private String contraseña = "admin";// Contraseña de la base de datos
	protected Connection conexion;
	
	// Método para abrir la conexión
	public void abrirConexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver de MySQL
			conexion = DriverManager.getConnection(url,usuario,contraseña);
			
			// Establecer la conexión
			System.out.println("conexion exitosa a la base de datos");
		} catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
		}
	}
	
	// Método para cerrar la conexión
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close(); // Cierra la conexión
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
