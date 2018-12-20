<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/" var="raiz" />
<c:url value="/assets/css" var="cssRoot" />
<c:url value="/assets/js" var="jsRoot" />
<link rel="stylesheet" href="${cssRoot}/materialize.min.css" />
<script src="${jsRoot}/jquery-3.2.1.min.js"></script>
<script src="${jsRoot}/materialize.min.js"></script>
<script src="${jsRoot}/header.js"></script>

<!-- Cabeçalho -->
<title>TecNow</title>

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
        <li><a href="${raiz }sair"><img src="../../imagens/logout.png" alt="icone de logout" width="30px" height="30px" style="margin-top: 15px"></a></li>
      </ul>
    </div>
  </nav>
  
<!-- <header>
	<a href="${raiz }">index</a>
	<br/>
	<a href="${raiz }app/anime/">Animes</a><br/>
	<a href="${raiz }app/jogo/">Jogos</a><br/>
	<a href="${raiz }app/anime/cadastro">Cadastrar Anime</a><br/>
	<a href="${raiz }app/jogo/cadastro">Cadastrar Jogo</a><br/>
	<a href="${raiz }alterar">Alterar Cadastro</a><br/>
	<a href="${raiz }alterar/senha">Alterar senha</a><br/>
	<a href="${raiz }sair">logout</a>
</header>
 -->