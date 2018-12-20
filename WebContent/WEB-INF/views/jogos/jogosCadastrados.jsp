<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/app/jogo/excluir" var="excluirJogo"/>
<c:url value="/app/jogo/cadastro" var="alterarJogo"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jogos</title>
</head>
<body>
	<c:import url="../templates/header.jsp"></c:import>
	<h1 style="text-align: center">Jogos</h1>
	<table class="highlight">
		<thead>
			<th>Jogo</th>
			<th>Categoria</th>
			<th>Data de Cadastro</th>
			<th>Atualizar</th>
			<th>Excluir</th>
		</thead>
		<tbody>
			<!-- Lista os jogos cadastrados pelo usuário, além das opções de alterar ou excluir tais cadastros -->
			<c:forEach items="${jogos }" var="jogo">
				<tr>
					<td>${jogo.nome }</td>
					<td>${jogo.categoria }</td>
					<td>${jogo.dataCadastro }</td>
					<td><a href="${alterarJogo }?id=${jogo.id}"><img src="../../imagens/update.png" alt="icone de atualizacao" width="30px" height="30px"></a></td>
					<td><a href="${excluirJogo }?id=${jogo.id}"><img src="../../imagens/trash.png" alt="icone de lixo" width="30px" height="30px"></a>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>