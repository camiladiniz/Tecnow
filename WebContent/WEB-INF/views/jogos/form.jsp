<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/app/jogo/salvar" var="salvarJogo" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Interface para o usuário realizar o cadastro de jogos -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
window.onload=function(){
$(document).ready(function() {
    $('select').material_select();
});
}

</script>
<style>
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
	<h1 style="text-align: center">Cadastro de Jogos</h1>
	<form action="${salvarJogo }" method="post" class="input-field col s12">
	
		<%--Campo escondido para guardar o id --%>
		
		<input type="hidden" value="${jogo.id }" name="id">
		Nome:
		<input type="text" name="nome" value="${jogo.nome }">
		Categoria:
		<select name="categoria" value="${jogo.categoria }" itemlabel="${jogo.categoria }">
			<option value="TIRO">Tiro</option>
			<option value="RPG">RPG</option>
			<option value="PLATAFORMA">Plataforma</option>
			<option value="ESPORTE">Esporte</option>
			<option value="HACK_AND_SLASH">Hack and Slash</option>
			<option value="OUTRO">Outro</option>
		</select>
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