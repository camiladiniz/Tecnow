<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/autenticar" var="autenticarUsuario"></c:url>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:url value="/assets/css" var="cssRoot" />
<c:url value="/assets/js" var="jsRoot" />
<link rel="stylesheet" href="${cssRoot}/materialize.min.css" />
<script src="${jsRoot}/jquery-3.2.1.min.js"></script>
<script src="${jsRoot}/materialize.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
	*{
		background-color: black;
		margin-left: 20%;
		margin-right: 20%;
		margin-top: 5%;
	}
	input, h1, h2{
		color: white;
	}
	#btn{
    padding: 12px 20px;
	background-color: red;
	color: white;
	margin-left: 40%;
	}
	#btn:hover{
    color: black;
    background-color: white;
}
	h1, h2{
		text-align: center;
	}
</style>
</head>
<body>
	<h1>TecNow</h1>
	<h2>Login</h2>
	<%-- Inputs necessários para a autenticação do usuário na sessão --%>
	<form method="post" action="${autenticarUsuario }">
		<label>
			Email
			<input type="email" name="email" >
		</label>
		<label>
			Senha
			<input type="password" name="senha" >
		</label>
		<%--Verifica se tem erro e faz foreach apresentando cada erro (validação manual de erro)--%>
		<button id="btn" type="submit">Entrar</button>
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