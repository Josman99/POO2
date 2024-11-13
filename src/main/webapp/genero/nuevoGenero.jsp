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
	const descripcion = document.getElementById('descripcion').value.trim();
	
	if(nombre === ''){
		alert('Ingrese nombre de genero');
		document.getElementById('nombre').focus();
		return false;
	}
	if(descripcion === ''){
		alert('Ingrese descripcion de genero');
		document.getElementById('nombre').focus();
		return false;
	}

}
</script>

<%@ include file='/cabeceraMenu.jsp' %> 

	<div class="container">
		<h3>Nuevo Genero</h3>
		<form role="form" action="<%=url%>GeneroController?op=ingresar" method="POST" onsubmit="return validarFormulario()">

			<div class="mb-3">
				<label for="" class="form-label">Nombre del genero:</label> 
				<input type="text" class="form-control" name="nombre" id="nombre" placeholder="NOMBRE">
			</div>
			<div class="mb-3">
				<label for="" class="form-label">Descripcion:</label>
				<input type="text" class="form-control" name="descripcion" id="descripcion" placeholder="DESCRIPCION">
			</div>

			<input type="submit" value="guardar" name="guardar"><br>

			<a href="<%=url%>GeneroController?op=listar">Volver</a>

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