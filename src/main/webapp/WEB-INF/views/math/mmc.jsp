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
			<h1>
				<fmt:message key="equacao.title" />
			</h1>
			<p class="lead">
				<fmt:message key="equacao.description" />
			</p>
		</div>
	</header>

	<section id="about">
		<div class="container">
		
		<form action="${s:mvcUrl('MC#calcular').build() }" method="post">
		<div class="form-group">
					<div>
						<p>
							<span id="myspan"> 
								<input name="numeros" type="number" min="1" max="999999" class="form-control" value="1" />
								<input name="numeros" type="number" min="1" max="999999" class="form-control" value="1" /> 
<%-- 								<form:input path="numeros" type="number" min="1" max="999999" class="form-control" value="1" /> --%>
							</span> <br> <br>
						</p>
					</div>
					<div>
						<p>
							<input class="btn btn-primary" type="button" value="Add Rows"
								onclick="add();" /> <input class="btn btn-primary"
								type="button" value="Delete Rows" onclick="remove();" />
						</p>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">
					<fmt:message key="equacao.calculate" />
				</button>
		</form>
		
<%-- 			<form:form action="${s:mvcUrl('MC#calcular').build() }" method="POST" --%>
<%-- 				commandName="mmcDto"> --%>
<!-- 				<div class="form-group"> -->
<!-- 					<div> -->
<!-- 						<p> -->
<%-- 							<span id="myspan">  --%>
<%-- 								<form:input path="numeros" type="number" min="1" max="999999" class="form-control" value="1" />  --%>
<%-- 								<form:input path="numeros" type="number" min="1" max="999999" class="form-control" value="1" /> --%>
<%-- 							</span> <br> <br> --%>
<!-- 						</p> -->
<!-- 					</div> -->
<!-- 					<div> -->
<!-- 						<p> -->
<!-- 							<input class="btn btn-primary" type="button" value="Add Rows" -->
<!-- 								onclick="add();" /> <input class="btn btn-primary" -->
<!-- 								type="button" value="Delete Rows" onclick="remove();" /> -->
<!-- 						</p> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<button type="submit" class="btn btn-primary"> -->
<%-- 					<fmt:message key="equacao.calculate" /> --%>
<!-- 				</button> -->
<%-- 			</form:form> --%>

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