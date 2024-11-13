<%@page import="com.unu.proyectoWebGB.beans.Genero"%>
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

<%@ include file='/cabeceraMenu.jsp' %> 
	<%
	Genero genero;
	HttpSession sesion = request.getSession();
	if (request.getAttribute("genero") == null) {
		genero = new Genero();
	} else {
		genero = (Genero) request.getAttribute("genero");
		System.out.println(genero.getIdGenero());
		System.out.println(genero.getNombreGenero());
	}
	%>

	<div class="container">
		<h3>Modificar Genero</h3>
		<form role="form" action="<%=url%>GeneroController?op=modificar" method="POST">
		
			<input type="hidden" name="op" value="insertar"> 
			
			<input type="hidden" name="id" value="<%=genero.getIdGenero()%>">

			<div class="mb-3">
				<label for="" class="form-label">Nombre del genero:</label> 
				<input type="text" class="form-control" value="<%=genero.getNombreGenero()%>" name="nombre" id="nombre" placeholder="NOMBRE">
			</div>
			<div class="mb-3">
				<label for="" class="form-label">Descripcion:</label>
				<input type="text" class="form-control" value="<%=genero.getDescripGenero()%>" name="descripcion" id="descripcion" placeholder="NACIONALIDAD">
			</div>

			<input type="submit" value="guardar" name="guardar"><br>

			<a href="<%=url%>GeneroController?op=listar">Volver</a>

		</form>
	</div>
</body>
</html>