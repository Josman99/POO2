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

	<%
	String url = "http://localhost:8080/proyectoWebGB/";
	%>

	<div class="container">
		<h3>Nuevo Autor</h3>
		<form role="form" action="<%=url%>AutoresController?op=ingresar"
			method="POST">

			<div class="mb-3">
				<label for="" class="form-label">Nombre del autor:</label> 
				<input type="text" class="form-control" name="nombre" id="nombre" placeholder="NOMBRE">
			</div>
			<div class="mb-3">
				<label for="" class="form-label">Nacionalidad:</label>
				<input type="text" class="form-control" name="nacionalidad" id="nacionalidad" placeholder="NACIONALIDAD">
			</div>

			<input type="submit" value="guardar" name="guardar"><br>

			<a href="<%=url%>AutoresController?op=listar">Volver</a>

		</form>
	</div>



</body>
</html>