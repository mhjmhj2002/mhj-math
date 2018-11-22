<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<tags:pageTemplate titulo="Math">

	<header class="bg-primary text-white">
		<div class="container text-center">
			<h1><fmt:message key="contact.title" /></h1>
			<p class="lead"><fmt:message key="contact.description"/></p>
		</div>
	</header>
	
	<section id="resultado">
	<div class="container">
			<h1><fmt:message key="contact.response"/></h1>
		</div>
	</section>
</tags:pageTemplate>