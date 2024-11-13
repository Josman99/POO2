<%@page import="com.unu.proyectoWebGB.models.AutoresModel"%>
<%@page import="com.unu.proyectoWebGB.beans.Autor"%>
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
		location.href = "AutoresController?op=eliminar&id="+id;
	}
}

</script>
	
</head>
<body>
<%@ include file='/cabeceraMenu.jsp' %> 



	<div class="container">
		<a type="button" href="<%=url%>AutoresController?op=nuevo">Nuevo
			autor</a>

		<table class="table">
			<thead>
				<tr>
					<th>Codigo De Autor</th>
					<th>Nombre De Autor</th>
					<th>Nacionalidad</th>
					<th>Operaciones</th>
				</tr>

			</thead>
			<tbody>

				<%
				List<Autor> listaAutores = (List<Autor>) request.getAttribute("listaAutores");
				//Verificar si la lista no es nula 
				if (listaAutores != null) {
					//Iterar sobre la lista de autores
					for (Autor autor : listaAutores) {
				%>
				<tr>
					<td><%=autor.getIdAutor()%></td>
					<td><%=autor.getNombre()%></td>
					<td><%=autor.getNacionalidad()%></td>
					<td>
						<!-- <button onclick="window.location.href='<%=url%>AutoresController?op=eliminar&id=<%=autor.getIdAutor()%>';">eliminar</button> -->
						
						<button onclick="window.location.href='<%=url%>AutoresController?op=obtener&id=<%=autor.getIdAutor()%>';" class="btn btn-warning">modificar</button>
						<a href="javascript:eliminar('<%=autor.getIdAutor()%>')" class="btn btn-danger">eliminar</a>

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
					<td></td>
				</tr>



				<%
				}
				%>
			
	</div>



	</tbody>
	</table>

	<%
	AutoresModel model = new AutoresModel();
	%>

	Total de autores =
	<%=model.totalAutores()%>



</body>
</html>