<%@page import="java.nio.file.FileSystemNotFoundException"%>
<%@page import="com.unu.proyectoWebGB.beans.Autor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.unu.proyectoWebGB.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<script src="assets/js/bootstrap.min.js"></script>
<title>editar Autores</title>
</head>
<body>

<div class="container">
	<%
	String url = "http://localhost:8080/proyectoWebGB/";
	Autor autor;
	HttpSession sesion = request.getSession();
	if (request.getAttribute("autor") == null) {
		autor = new Autor();
	} else {
		autor = (Autor) request.getAttribute("autor");
		System.out.println(autor.getIdAutor());
		System.out.println(autor.getNombre());
	}
	%>

	<h3>modificar Autor</h3>
	<form role="form" action="<%=url%>AutoresController?op=modificar"
		method="POST">

		<input type="hidden" name="op" value="insertar"> <input
			type="hidden" name="id" value="<%=autor.getIdAutor()%>">

		<div class="mb-3">
			<label for="" class="form-label">Nombre del autor:</label> 
			<input type="text" class="form-control" value="<%=autor.getNombre()%>" name="nombre" id="nombre" placeholder="NOMBRE">
		</div>
		<div class="mb-3">
			<label for="" class="form-label">Nacionalidad:</label>
			<input type="text" class="form-control" value="<%=autor.getNacionalidad()%>" name="nacionalidad" id="nacionalidad" placeholder="NACIONALIDAD">
		</div>
		
		<input type="submit" value="modificar" name="modificar"><br>
		
		<a href="<%=url%>AutoresController?op=listar">Volver</a>
			

	</form>
</div>	

</body>
</html>