<%@page import="com.unu.proyectoWebGB.beans.Editorial"%>
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

<div class="container">
	<% 
String url="http://localhost:8080/proyectoWebGB/";
%>

<a type="button" href="<%=url%>EditorialController?op=nuevo">Nueva editorial</a>

<table class="table">
<thead>
	<tr>
    <th>Codigo De Editorial</th>
    <th>Nombre De Editorial</th>
    <th>Contacto</th>
    <th>Telefono</th>
    <th>operaciones</th>
  </tr>

</thead>
<tbody>

<%
List<Editorial> listaEditorial = (List<Editorial>)request.getAttribute("listaEditorial");
	//Verificar si la lista no es nula 
	if(listaEditorial != null){
		//Iterar sobre la lista de autores
		for(Editorial editorial : listaEditorial){
	%>
		<tr>
		<td><%=editorial.getIdEditorial() %></td>
		<td><%=editorial.getNombreEditorial() %></td>
		<td><%=editorial.getContactoEditorial() %></td>
		<td><%=editorial.getTelefonoEditorial() %></td>
		<td>
		<button onclick="window.location.href='<%=url%>EditorialController?op=eliminar&id=<%=editorial.getIdEditorial()%>';" >eliminar</button>
		<button onclick="window.location.href='<%=url%>EditorialController?op=obtener&id=<%=editorial.getIdEditorial()%>';" >modificar</button>
		
		</td>
		</tr>
		<%
		}
	} else {
		%>
		<tr>
		<td>No hay datos</td>
		<td>No hay datos</td>
		<td>No hay datos</td>
		<td>No hay datos</td>
		<td></td>
		</tr>
		
		
			
	<%	
	}
	%>

</tbody>
</table>

<%	

	%>
</div>




</body>
</html>