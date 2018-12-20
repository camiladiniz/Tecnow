<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- Página inicial do usuário logado --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TecNow</title>
<c:url value="/" var="raiz" />
<c:url value="/assets/css" var="cssRoot" />
<c:url value="/assets/js" var="jsRoot" />
<link rel="stylesheet" href="${cssRoot}/materialize.min.css" />
<script src="${jsRoot}/jquery-3.2.1.min.js"></script>
<script src="${jsRoot}/materialize.min.js"></script>
<script src="${jsRoot}/header.js"></script>
</head>
<body>
	<nav>
    <div class="nav-wrapper black">
      <a href="#" class="brand-logo">TecNow</a>
      <ul id="nav-mobile" class="right hide-on-med-and-down">
        <li><a href="${raiz }app/anime/">Animes</a></li>
        <li><a href="${raiz }app/jogo/">Jogos</a></li>
        <li><a href="${raiz }app/usuario/alterar">Alterar Cadastro</a></li>
        <li><a href="${raiz }app/alterar/senha">Alterar Senha</a></li>
        <li><a href="${raiz }app/anime/cadastro" class="waves-effect waves-light  red btn">Adicionar Anime</a></li>
        <li><a href="${raiz }app/jogo/cadastro" class="waves-effect waves-light red btn">Adicionar Jogo</a></li>
        <li><a href="${raiz }sair"><img src="../imagens/logout.png" alt="icone de logout" width="30px" height="30px" style="margin-top: 15px"></a></li>
      </ul>
    </div>
  </nav>
	<h2 style="text-align: center;">Bem vindo ao sistema TecNow!</h2>
</body>
</html>