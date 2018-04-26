<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<tags:pageTemplate titulo="Math">

<spring:message code="mdc.add.operacao" var="mccAddOperacao"/>
<spring:message code="mdc.del.operacao" var="mccDelOperacao"/>

	<header class="bg-primary text-white">
		<div class="container text-center">
			<h1>
				<fmt:message key="mdc.title" />
			</h1>
			<p class="lead">
				<fmt:message key="mdc.description" />
			</p>
		</div>
	</header>

	<section id="about">
		<div class="container">

			<form action="${s:mvcUrl('calcular_mdc').build() }" method="post">
				<div class="form-group">
					<div>
						<p>
							<span id="myspan"> 
								<input name="numeros" type="number" min="1" max="999999" class="form-control" value="1"/> 
								<input name="numeros" type="number" min="1" max="999999" class="form-control" value="1"/>
							</span> <br> <br>
						</p>
					</div>
					<div>
						<p>
							<input class="btn btn-primary" type="button" value="${mccAddOperacao}" onclick="add();"/> 
							<input class="btn btn-primary" type="button" value="${mccDelOperacao}" onclick="remove();"/>
						</p>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">
					<fmt:message key="mdc.calculate" />
				</button>
			</form>

		</div>
	</section>

	<script type="text/javascript">
		function add() {
			var element = document.createElement("input");
			element.setAttribute("name", "numeros");
			element.setAttribute("type", "number");
			element.setAttribute("min", "1");
			element.setAttribute("max", "9999");
			element.setAttribute("class", "form-control");
			element.setAttribute("value", "1");
			var spanvar = document.getElementById("myspan");
			spanvar.appendChild(element);
		}

		function remove() {
			var spanvar = document.getElementById("myspan");
			if (spanvar.children.length > 2) {
				spanvar.removeChild(spanvar.lastChild);
			}
		}
	</script>

</tags:pageTemplate>