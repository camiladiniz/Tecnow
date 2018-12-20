<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/app/anime/salvar" var="cadastrarAnime" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Realiza o cadastro de animes -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	*{
		background-color: black;
		color: white;
	}
	button{
	    padding: 12px 20px;
	    transition: box-shadow .3s;
		background-color: red;
		color: white;
		margin-left: 40%;
	}
	button:hover{
	    color: black;
	    background-color: white;
	}
	form{
		margin-left: 20%;
		margin-right: 20%;
	}
</style>
</head>
<body>
	<c:import url="../templates/header.jsp"></c:import>
	<h1 style="text-align: center">Cadastro de Animes</h1>
	<form action="${cadastrarAnime }" method="post">
	
		<%--Campo escondido para guardar o id --%>
		
		<input type="hidden" value="${anime.id }" name="id">
		<label>
			Nome: 
			<input type="text" name="nome" value="${anime.nome }">
		</label>
		<button type="submit">Salvar</button>
	</form>
	<c:if test="${not empty erros}">
			<div style="color:red">
				<c:forEach items="${erros }" var = "erro">
					<p>${erro }</p>
				</c:forEach>
			</div>
		</c:if>
</body>
</html>