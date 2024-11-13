<%@page import="com.unu.proyectoWebGB.beans.Genero"%>
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

<a class="btn btn-success mt-2" href="<%=url%>GeneroController?op=nuevo"> Nuevo</a> <br>

	<table class="table mt-2">
		<thead>
		<tr>
			<th>Id Genero</th>
			<th>Nombre Genero</th>
			<th>Descripcion Genero</th>
			<th>Operaciones</th>
		</tr>
		
		</thead>
		
		<tbody>
		<%
		List<Genero> listaGeneros = (List<Genero>) request.getAttribute("listaGeneros");
		//Verificar si la lista no es nula
		if(listaGeneros != null){
			//Iterar sobre la lista de autores
			for(Genero genero : listaGeneros){
				%>
				<tr>
					<td><%=genero.getIdGenero()%></td>
					<td><%=genero.getNombreGenero()%></td>
					<td><%=genero.getDescripGenero()%></td>
					<td>
						<button onclick="window.location.href='<%=url%>GeneroController?op=obtener&id=<%=genero.getIdGenero()%>';"  class="btn btn-warning">modificar</button>
						<a href="javascript:eliminar('<%=genero.getIdGenero()%>')" class="btn btn-danger">eliminar</a>
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
			</tr>
			<%
		}
		%>
		
		
		
		</tbody>
		
	
	</table>
	
</div>

</body>
</html>