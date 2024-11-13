<%@page import="com.unu.proyectoWebGB.beans.Genero"%>
<%@page import="com.unu.proyectoWebGB.models.GeneroModel"%>
<%@page import="com.unu.proyectoWebGB.beans.Autor"%>
<%@page import="com.unu.proyectoWebGB.models.AutoresModel"%>
<%@page import="com.unu.proyectoWebGB.beans.Editorial"%>
<%@page import="com.unu.proyectoWebGB.models.EditorialModel"%>
<%@page import="com.unu.proyectoWebGB.beans.Libro"%>
<%@page import="com.unu.proyectoWebGB.models.LibroModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<script src="assets/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<script>
 function validarFormulario() {
	const nombre = document.getElementById('nombre').value.trim();
	const existencias = document.getElementById('existencias').value.trim();
	const precio = document.getElementById('precio').value.trim();
	const idautor = document.getElementById('idautor').value.trim();
	const editorial = document.getElementById('editorial').value.trim();
	const genero = document.getElementById('genero').value.trim();
	const descripcion = document.getElementById('descripcion').value.trim();
	
	if(nombre === ''){
		alert('Ingrese nombre del libro');
		document.getElementById('nombre').focus();
		return false;
	}
	if(existencias === ''){
		alert('Ingrese existencias del libro');
		document.getElementById('existencias').focus();
		return false;
	}
	if(precio === ''){
		alert('Ingrese precio del libro');
		document.getElementById('precio').focus();
		return false;
	}
	if(idautor == 0){
		alert('Ingrese autor del libro');
		document.getElementById('idautor').focus();
		return false;
	}
	if(editorial == 0){
		alert('Ingrese editorial del libro');
		document.getElementById('editorial').focus();
		return false;
	}
	if(genero == 0){
		alert('Ingrese genero del libro');
		document.getElementById('genero').focus();
		return false;
	}
	if(descripcion === ''){
		alert('Ingrese descripcion del libro');
		document.getElementById('descripcion').focus();
		return false;
	}
	return true;
}
</script>

<%@ include file='/cabeceraMenu.jsp' %> 


	<div class="container">
		<h3>Nuevo Libro</h3>
		<form role="form" action="<%=url%>LibrosController?op=ingresar"
			method="POST" onsubmit="return validarFormulario()">

			<div class="mb-3">
				<label for="" class="form-label">Nombre del libro:</label> 
				<input type="text" class="form-control" name="nombre" id="nombre" placeholder="NOMBRE">
			</div>
			<div class="mb-3">
				<label for="" class="form-label">Existencias:</label>
				<input type="text" class="form-control" name="existencias" id="existencias" placeholder="EXISTENCIAS">
			</div>
			<div class="mb-3">
				<label for="" class="form-label">Precio:</label> 
				<input type="text" class="form-control" name="precio" id="precio" placeholder="PRECIO">
			</div>
			<label for="" class="form-label">Autor:</label>
			<select class="form-select mb-3" aria-label="Default select example" name="idautor" id="idautor">
			  <option value="0" selected>Seleccione Autor</option> 
			  <%
			  AutoresModel modeloA = new AutoresModel();
			  List<Autor> listaAutor = modeloA.listarAutores();
			  if(listaAutor != null) {
				  for(Autor autor : listaAutor) {
					  %>
					  <option value="<%=autor.getIdAutor()%>"><%=autor.getNombre() %></option>	  
					  <%
				  }
			  } else {
				  %>
				  <option >No hay datos</option>
				  <%
			  }
			  
			  %>
			</select>
			<label for="" class="form-label">Editorial:</label>
			<select class="form-select mb-3" aria-label="Default select example" name="editorial" id="editorial">
			  <option value="0" selected>Seleccione Editorial</option>
			  <%
			  EditorialModel modelo = new EditorialModel();
			  List<Editorial> listaEditorial = modelo.listaEditorial();
			  if(listaEditorial != null) {
				  for(Editorial editorial : listaEditorial) {
					  %>
					  <option value="<%=editorial.getIdEditorial()%>"><%=editorial.getNombreEditorial() %></option>
					  
					  <%
				  }
			  } else {
				  %>
				  <option >No hay datos</option>
				  <%
			  }
			  
			  %>
			</select>
			<label for="" class="form-label">Genero:</label>
			<select class="form-select mb-3" aria-label="Default select example" name="genero" id="genero">
			  <option value="0" selected>Seleccione Genero</option>
			  <%
			  GeneroModel modeloG = new GeneroModel();
			  List<Genero> listaGenero = modeloG.listarGenero();
			  if(listaAutor != null) {
				  for(Genero genero : listaGenero) {
					  %>
					  <option value="<%=genero.getIdGenero()%>"><%=genero.getNombreGenero()%></option>	  
					  <%
				  }
			  } else {
				  %>
				  <option >No hay datos</option>
				  <%
			  }
			  
			  %>
			</select>

			<div class="mb-3">
				<label for="" class="form-label">Descripcion:</label>
				<input type="text" class="form-control" name="descripcion" id="descripcion" placeholder="DESCRIPCION">
			</div>

			<input type="submit" value="guardar" name="guardar"><br>

			<a href="<%=url%>LibrosController?op=listar">Volver</a>

		</form>
	</div>
<%
	if(request.getAttribute("respuesta") !=null) {
		boolean res = (boolean) request.getAttribute("respuesta");
		if(res){
			List<String> listaError = (List<String>) request.getAttribute("listaError");
			for(String error : listaError) {
				%>
<span><%=error %></span>	
<br>	
			<%			
			}
		}
	}
%>
</body>
</html>