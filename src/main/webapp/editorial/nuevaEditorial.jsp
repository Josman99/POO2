<%@page import="java.util.List"%>
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
<script>
 function validarFormulario() {
	const nombre = document.getElementById('nombre').value.trim();
	const contacto = document.getElementById('contacto').value.trim();
	const telefono = document.getElementById('telefono').value.trim();
	
	if(nombre === ''){
		alert('Ingrese nombre de editorial');
		document.getElementById('nombre').focus();
		return false;
	}
	if(contacto === ''){
		alert('Ingrese contacto de editorial');
		document.getElementById('nacionalidad').focus();
		return false;
	}
	if(telefono === ''){
		alert('Ingrese telefono de editorial');
		document.getElementById('nacionalidad').focus();
		return false;
	}
	return true;
}
</script>

<%@ include file='/cabeceraMenu.jsp' %> 

<div class="container">

	
	<h3>Nueva Editorial</h3>
	<form role="form" action="<%=url%>EditorialController?op=ingresar" method="POST" onsubmit="return validarFormulario()">
	
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