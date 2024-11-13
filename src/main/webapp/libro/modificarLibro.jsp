<%@page import="java.lang.foreign.Linker.Option"%>
<%@page import="com.unu.proyectoWebGB.beans.Genero"%>
<%@page import="com.unu.proyectoWebGB.models.GeneroModel"%>
<%@page import="com.unu.proyectoWebGB.beans.Editorial"%>
<%@page import="com.unu.proyectoWebGB.models.EditorialModel"%>
<%@page import="com.unu.proyectoWebGB.beans.Autor"%>
<%@page import="java.util.List"%>
<%@page import="com.unu.proyectoWebGB.models.AutoresModel"%>
<%@page import="com.unu.proyectoWebGB.beans.Libro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file='/cabeceraMenu.jsp' %> 
	<%
	Libro libro;
	HttpSession sesion = request.getSession();
	if (request.getAttribute("libro") == null) {
		libro = new Libro();
	} else {
		libro = (Libro) request.getAttribute("libro");
		System.out.println(libro.getIdLib());
		System.out.println(libro.getNombreLib());
		System.out.println(libro.getNombreAut());
		System.out.println(libro.getNombreEdito());
		System.out.println(libro.getNombreGene());
	}
	%>

	<div class="container">
		<h3>Modificar Libro</h3>
		<form role="form" action="<%=url%>LibrosController?op=modificar"
			method="POST" onsubmit="return validarFormulario()">
			
			<input type="hidden" name="op" value="modificar"> <input
			type="hidden" name="id" value="<%=libro.getIdLib()%>">

			<div class="mb-3">
				<label for="" class="form-label">Nombre del libro:</label> 
				<input type="text" class="form-control" name="nombre" id="nombre" value="<%=libro.getNombreLib()%>" placeholder="NOMBRE">
			</div>
			<div class="mb-3">
				<label for="" class="form-label">Existencias:</label>
				<input type="text" class="form-control" name="existencias" id="existencias" value="<%=libro.getExistencias()%>" placeholder="EXISTENCIAS">
			</div>
			<div class="mb-3">
				<label for="" class="form-label">Precio:</label> 
				<input type="text" class="form-control" name="precio" id="precio" value="<%=libro.getPrecio()%>" placeholder="PRECIO">
			</div>
			<label for="" class="form-label">Autor:</label>
			<select class="form-select mb-3" aria-label="Default select example" name="idautor" id="idautor">
			  <%
			  AutoresModel modeloA = new AutoresModel();
			  
			  List<Autor> listaAutor = modeloA.listarAutores();
			  
			  if (listaAutor != null) {
				  
			      for (Autor autor : listaAutor) {
			    	  
			    	  if(libro.getIdAut()==autor.getIdAutor()){
			    		  %>
						  <option value="<%=libro.getIdAut()==autor.getIdAutor()%>" selected><%= libro.getNombreAut()%></option>
						  <%
			    	  }
			          %>
			          <option value="<%=autor.getIdAutor()%>">
			              <%= autor.getNombre()%>
			          </option>
			          <%
			      }
			      
			  }  else {
				  %>
				  <option >No hay datos</option>
				  <%
			  }
			  
			  %>
			</select>
			<label for="" class="form-label">Editorial:</label>
			<select class="form-select mb-3" aria-label="Default select example" name="editorial" id="editorial">
			  <%
			  EditorialModel modelo1 = new EditorialModel();
			  List<Editorial> listaEditorial1 = modelo1.listaEditorial();
				if (listaAutor != null) {
					 %>
					  <option value="<%=libro.getIdEdi()%>" selected><%= libro.getNombreEdito()%></option>
					  <%
			      for (Editorial editorial : listaEditorial1) {
			    	  
			          %>
			          <option value="<%=editorial.getIdEditorial()%>">
			              <%= editorial.getNombreEditorial()%>
			          </option>
			          <%
			      }
			      
			  }  else {
				  %>
				  <option >No hay datos</option>
				  <%
			  }
			  
			  %>
			</select>
			<label for="" class="form-label">Genero:</label>
			<select class="form-select mb-3" aria-label="Default select example" name="genero" id="genero">
			  <%
			  GeneroModel modeloG = new GeneroModel();
			  List<Genero> listaGenero = modeloG.listarGenero();
			  if(listaAutor != null) {
				  %>
				  <option value="<%=libro.getIdGene()%>" selected><%= libro.getNombreGene()%></option>
				  <%
				  for(Genero genero : listaGenero) {
					  
					  %>
					  <option value="<%=genero.getIdGenero()%>"><%=genero.getNombreGenero()%></option>	  
					  <%
				  }
			  } else {
				  %>
				  <option >No hay datos</option>
				  <%
			  }
			  
			  %>
			</select>

			<div class="mb-3">
				<label for="" class="form-label">Descripcion:</label>
				<input type="text" class="form-control" name="descripcion" id="descripcion"  value="<%=libro.getDescripcion()%>" placeholder="DESCRIPCION">
			</div>

			<input type="submit" value="guardar" name="guardar"><br>

			<a href="<%=url%>LibrosController?op=listar">Volver</a>

		</form>
	</div>
</body>
</html>