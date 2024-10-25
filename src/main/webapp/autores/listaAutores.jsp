<%@page import="com.unu.proyectoWebGB.beans.Autor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>

<% 
String url="http://localhost:8080/proyectoWebGB/";
%>

<a type="button" href="<%=url%>AutoresController?op=nuevo">Nuevo autor</a>

<table border="1">
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
List<Autor> listaAutores = (List<Autor>)request.getAttribute("listaAutores");
	//Verificar si la lista no es nula 
	if(listaAutores != null){
		//Iterar sobre la lista de autores
		for(Autor autor : listaAutores){
	%>
		<tr>
		<td><%=autor.getIdAutor() %></td>
		<td><%=autor.getNombre() %></td>
		<td><%=autor.getNacionalidad() %></td>
		<td></td>
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


</tbody>
</table>

</body>
</html>