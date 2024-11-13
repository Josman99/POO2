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

	<script type="text/javascript">
	</script>
	
	<script type="text/javascript">
	function eliminar(id) {
		if(confirm("¿Desea eliminar el rgistro?")==true){
			location.href = "EditorialController?op=eliminar&id="+id;
		}
	}
	</script>

</head>
<body>

<%@ include file='/cabeceraMenu.jsp' %> 

<div class="container">

<a type="button" href="<%=url%>EditorialController?op=nuevo">Nueva editorial</a>

<table class="table">
<thead>
	<tr>
	    <th>Codigo De Editorial</th>
	    <th>Nombre De Editorial</th>
	    <th>Contacto</th>
	    <th>Telefono</th>
	    <th>Operaciones</th>
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
				<button onclick="window.location.href='<%=url%>EditorialController?op=obtener&id=<%=editorial.getIdEditorial()%>';"  class="btn btn-warning">modificar</button>
				<a href="javascript:eliminar('<%=editorial.getIdEditorial()%>')" class="btn btn-danger">eliminar</a>
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