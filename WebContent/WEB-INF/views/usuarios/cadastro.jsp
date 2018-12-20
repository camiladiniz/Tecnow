<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/usuario/salvar" var="salvarNovoUsuario" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Página jsp de cadastro e/ou alteração de dados do usuário -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TecNow</title>
<c:url value="/assets/css" var="cssRoot" />
<c:url value="/assets/js" var="jsRoot" />
<link rel="stylesheet" href="${cssRoot}/materialize.min.css" />
<script src="${jsRoot}/jquery-3.2.1.min.js"></script>
<script src="${jsRoot}/materialize.min.js"></script>
<%-- Estilização da página --%>
<style>
	*{
		background-color: black;
		margin-left: 20%;
		margin-right: 20%;
		color:white;
	}
	h2{
		text-align: center;
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
	<h2>Cadastro</h2>
	<form method="post" action="${salvarNovoUsuario}">
		<input type="hidden" value="${usuario.id }" name="id">
		<label>
			Nome:
			<input type="text" name="nome" maxlength="60" value="${usuario.nome }" placeholder="Nome Completo">
		</label>
		<br/>
		<br/> 
		<c:if test="${! empty usuario.sexo}">
		Sexo:
			<input type=radio name="sexo" value="${usuario.sexo }" class="with-gap" id="sexo" checked >
			<label for="sexo">${usuario.sexo }</label>
		</c:if>
		<c:if test="${empty usuario.sexo }">
		Sexo:
			<input type=radio name="sexo" value="FEMININO"class="with-gap" id="sexoFeminino">
			<label for="sexoFeminino">Feminino</label>
			<input type=radio name="sexo" value="MASCULINO"class="with-gap" id="sexoMasculino">
			<label for="sexoMasculino">Masculino</label>
		</c:if>
		<br/>
		<br/>
		<label>
			Data de Nascimento
			<input type="date" name="dataNascimento" value="${usuario.dataNascimento }">
		</label>
		<br/>
		<br/>
		<label>
			Email
			<input <c:if test="${usuario.id != null && ! empty usuario.email }"> readonly</c:if> type="email" name="email" value="${usuario.email }" placeholder="email@email.com">
		</label>
		<br/>
		<label <c:if test="${usuario.id != null && ! empty usuario.senha }"> disabled  style="color:black"</c:if>>
			Senha
			<input <c:if test="${usuario.id != null && ! empty usuario.senha }"> type="hidden"</c:if> type="password" name="senha" maxlength="20" placeholder="********">
		</label>
		<br/>
		<br/>
		<button type="submit" id="btn">Cadastrar</button>
	</form>
	<!-- Se o usuário cadastrou algum campo de forma inválida mostra na tela -->
	<c:if test="${not empty erros}">
		<div style="color:red">
			<c:forEach items="${erros }" var = "erro">
				<p>${erro }</p>
			</c:forEach>
		</div>
	</c:if>
</body>
</html>