<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="usuario/novo" var="novoUsuario" />
<c:url value="entrar" var="login" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TecNow</title>
<%-- Página pública e principal do sistema --%>
<c:url value="/assets/css" var="cssRoot" />
<c:url value="/assets/js" var="jsRoot" />
<link rel="stylesheet" href="${cssRoot}/materialize.min.css" />
<script src="${jsRoot}/jquery-3.2.1.min.js"></script>
<script src="${jsRoot}/materialize.min.js"></script>
<link rel="stylesheet"  href="${cssRoot }/index.css">
<style type="text/css">
	a:hover {
    	color: red;
	}
</style>
</head>
<body>
	<header class="valign-wrapper">
	    <nav>
		    <div class="nav-wrapper">
		      <p class="brand-logo">TecNow</p>
		      <ul id="nav-mobile" class="right hide-on-med-and-down">
		        <li><a href="${login }">Login</a></li>
		        <li><a href="${novoUsuario }">Cadastro</a></li>
		      </ul>
		    </div>
	    </nav>
	</header>
	<div id="introducao">
		<p>TecNow</p>
		<p>O maior evento Geek do Brasil!
	</div>
  
</body>
</html>