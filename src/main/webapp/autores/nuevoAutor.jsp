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

<h3>Nuevo Autor</h3>
<form role="form" action="<%=url%>AutoresController?op=ingresar" method="POST">

<input type="hidden" name="op" value="insertar">

Nombre del autor: <input type="text" name="nombre" id="nombre"><br>
Nacionalidad: <input type="text" name="nacionalidad" id="nacionalidad"><br>
<input type="submit" value="guardar" name="guardar"><br>

<a href="<%=url%>AutoresContoller?op=lista">Volver</a>

</form>

</body>
</html>