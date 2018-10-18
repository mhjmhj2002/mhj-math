<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
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

	<section id="operacoes">
	<div class="container">
			<div class="row">
				<div class="col-lg-8 mx-auto">
					<h2>Operações</h2>
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

	<section id="features">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 mx-auto">
					<h2>Atualizações</h2>
					<p class="lead">
					- 05/2017: Add Equações de Segundo Grau.
					</p>
					<p class="lead">
					- 06/2017: Add MMC.
					</p>
					<p class="lead">
					- 07/2017: Add MDC.
					</p>
					<p class="lead">
					- 08/2107: Add Simplificação de Frações.
					</p>
					<p class="lead">
					- 09/2017: Add Soma/Subtração de Frações.
					</p>
					<p class="lead">
					- 10/2017: Add Multiplicação de Frações.
					</p>
					<p class="lead">
					- 11/2017: Adicionando Divisão de Frações.
					</p>
				</div>
			</div>
		</div>
	</section>

</tags:pageTemplate>



















