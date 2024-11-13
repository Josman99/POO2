<%@page import="com.unu.proyectoWebGB.beans.Libro"%>
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
			location.href = "LibrosController?op=eliminar&id="+id;
		}
	}
	</script>
	
</head>
<body>
<%@ include file='/cabeceraMenu.jsp' %>
<div class="container">

<a class="btn btn-success mt-2" href="<%=url%>LibrosController?op=nuevo"> Nuevo</a> <br>

	<table class="table mt-2">
		<thead>
		<tr>
			<th>Id libro</th>
			<th>Nombre libro</th>
			<th>Existencias</th>
			<th>Precio libro</th>
			<th>Autor</th>
			<th>Editorial</th>
			<th>Genero</th>
			<th>Descripcion</th>
			<th>Operaciones</th>
		</tr>
		
		</thead>
		
		<tbody>
		<%
		List<Libro> listaLibros = (List<Libro>) request.getAttribute("listaLibros");
		//Verificar si la lista no es nula
		if(listaLibros != null){
			//Iterar sobre la lista de autores
			for(Libro libro : listaLibros){
				%>
				<tr>
					<td><%=libro.getIdLib()%></td>
					<td><%=libro.getNombreLib()%></td>
					<td><%=libro.getExistencias()%></td>
					<td><%=libro.getPrecio()%></td>
					<td><%=libro.getNombreAut()%></td>
					<td><%=libro.getNombreEdito()%></td>
					<td><%=libro.getNombreGene()%></td>
					<td><%=libro.getDescripcion()%></td>
					<td>
						<button onclick="window.location.href='<%=url%>LibrosController?op=obtener&id=<%=libro.getIdLib()%>';"  class="btn btn-warning">modificar</button>
						<a href="javascript:eliminar('<%=libro.getIdLib()%>')" class="btn btn-danger">eliminar</a>
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