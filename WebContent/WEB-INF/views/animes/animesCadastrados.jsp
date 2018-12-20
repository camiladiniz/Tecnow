<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/app/anime/excluir" var="excluirAnime" />
<c:url value="/app/anime/cadastro" var="alterarAnime" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<c:import url="../templates/header.jsp"></c:import>
	<h1 style="text-align: center">Animes</h1>
	<table class="highlight">
		<thead>
			<th>Anime</th>
			<th>Data de Cadastro</th>
			<th>Atualizar</th>
			<th>Excluir</th>
		</thead>
		<tbody>
			<!-- Realiza a listagens dos animes cadastrados pelo usuário -->
			<c:forEach items="${animes }" var="anime">
				<tr>
					<td>${anime.nome }</td>
					<td>${anime.dataCadastro }</td>
					<td><a href="${alterarAnime }?id=${anime.id}"><img src="../../imagens/update.png" alt="icone de atualizacao" width="30px" height="30px"></a></td>
					<td><a href="${excluirAnime }?id=${anime.id}"><img src="../../imagens/trash.png" alt="icone de lixo" width="30px" height="30px"></a>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>