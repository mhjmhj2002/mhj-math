<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/resources" var="resourcesPath" />
<c:url value="/math" var="mathPath" />
<c:url value="/" var="homePath" />

<tags:pageTemplate titulo="Math">

	<header class="bg-primary text-white">
		<div class="container text-center">
			<h1><fmt:message key="home.welcome" /></h1>
			<p class="lead"><fmt:message key="home.welcome.description" /></p>
		</div>
	</header>

	<section id="menu">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mx-auto">
					<h2>EF2</h2>
					<p class="lead">6 Ano</p>
					<a class="dropdown-item" href="${mathPath}/fracao/divisao">- Frações Divisão</a>
					<a class="dropdown-item" href="${mathPath}/fracao/multiplicacao">- Frações Multiplicação</a>
					<a class="dropdown-item" href="${mathPath}/fracao/simplificacao">- Frações Simplificação</a>
					<a class="dropdown-item" href="${mathPath}/fracao/soma">- Frações Soma</a>
					<a class="dropdown-item" href="${mathPath}/mdc">- MDC</a>
					<a class="dropdown-item" href="${mathPath}/mmc">- MMC</a>
					<h2>EM</h2>
					<p class="lead">1 Ano</p>
					<a class="dropdown-item" href="${mathPath}/equacao">Equação</a>
				</div>
			</div>
		</div>
	</section>
	<section id="about">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mx-auto">
					<h2><fmt:message key="home.about.title"/></h2>
					<p class="lead"><fmt:message key="home.about.title.description"/></p>
				</div>
			</div>
		</div>
	</section>
	

</tags:pageTemplate>



















