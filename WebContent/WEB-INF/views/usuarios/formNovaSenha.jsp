<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/app/usuario/senha/nova" var="trocarSenha" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TecNow</title>
<style>
	*{
		background-color: black;
		color:white;
	}
	p{
		text-align: center;
	}
	h2{
		text-align: center;
		margin-left: 20%;
		margin-right: 20%;
	}
	form{
		margin-left: 20%;
		margin-right: 20%;
	}
	label{
		color: white;
		margin-left: 0px;
		font-family: 16px;
	}
	#btn{
    padding: 12px 20px;
    transition: box-shadow .3s;
	background-color: red;
	color: white;
	margin-left: 40%;
	}
	#btn:hover{
    color: black;
    background-color: white;
}
</style>
</head>
<body>
	<c:import url="../templates/header.jsp"></c:import>
	<h2>Trocar senha</h2>
	<form method="post" action="${trocarSenha }">
		<input type="hidden" name="id" value="${usuario.id }">
		Digite sua senha atual
		<input type="password" name="senha">
		Digite sua nova senha
		<input type="password" name="novaSenha">
		<button type="submit" id="btn">Trocar senha</button>
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