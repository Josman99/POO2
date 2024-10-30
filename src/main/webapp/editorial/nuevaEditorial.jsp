<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<script src="assets/js/bootstrap.min.js"></script>
<title>AUTORES</title>
</head>
<body>

<div class="container">
	<% 
	String url="http://localhost:8080/proyectoWebGB/";
	%>
	
	<h3>Nueva Editorial</h3>
	<form role="form" action="<%=url%>EditorialController?op=ingresar" method="POST">
	
	<input type="hidden" name="op" value="insertar">
	
	<div class="mb-3">
			<label for="" class="form-label">Nombre de editorial:</label> 
			<input type="text" class="form-control" name="nombre" id="nombre" placeholder="NOMBRE">
		</div>
		<div class="mb-3">
			<label for="" class="form-label">Contacto:</label> 
			<input type="text" class="form-control" name="contacto" id="contacto" placeholder="CONTACTO">
		</div>
		<div class="mb-3">
			<label for="" class="form-label">Telefono:</label> 
			<input type="text" class="form-control" name="telefono" id="telefono" placeholder="TELEFONO">
		</div>
	
	<input type="submit" value="guardar" name="guardar"><br>
	
	<a href="<%=url%>EditorialController?op=listar">Volver</a>
	
	</form>
	
</div>



</body>
</html>