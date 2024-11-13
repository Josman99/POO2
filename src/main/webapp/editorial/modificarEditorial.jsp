<%@page import="com.unu.proyectoWebGB.beans.Editorial"%>
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

<div class="container">
<%@ include file='/cabeceraMenu.jsp' %> ;
	<% 

	Editorial editorial;
	HttpSession sesion = request.getSession();
	if(request.getAttribute("editorial")==null){
		editorial = new Editorial();
	}else{ 
		editorial = (Editorial)request.getAttribute("editorial");
		System.out.println(editorial.getIdEditorial());
		System.out.println(editorial.getNombreEditorial());
	}
	%>
	
	<h3>Nueva Editorial</h3>
	<form role="form" action="<%=url%>EditorialController?op=modificar" method="POST">
	
	<input type="hidden" name="op" value="insertar">
	<input type="hidden" name="id" value="<%=editorial.getIdEditorial()%>">
	
	<div class="mb-3">
		<label for="" class="form-label">Nombre de editorial:</label> 
		<input type="text" class="form-control" value="<%=editorial.getNombreEditorial()%>" name="nombre" id="nombre" placeholder="NOMBRE">
	</div>
	<div class="mb-3">
		<label for="" class="form-label">Contacto:</label> 
		<input type="text" class="form-control" value="<%=editorial.getContactoEditorial()%>" name="contacto" id="contacto" placeholder="CONTACTO">
	</div>
	<div class="mb-3">
		<label for="" class="form-label">Telefono:</label> 
		<input type="text" class="form-control" value="<%=editorial.getTelefonoEditorial()%>" name="telefono" id="telefono" placeholder="TELEFONO">
	</div>

	<input type="submit" value="guardar" name="guardar"><br>
	
	<a href="<%=url%>EditorialController?op=listar">Volver</a>
</div>


</body>
</html>