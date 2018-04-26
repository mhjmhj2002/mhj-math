<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<tags:pageTemplate titulo="Math">

	<header class="bg-primary text-white">
		<div class="container text-center">
			<h1>
				<fmt:message key="fracao.div.title" />
			</h1>
			<p class="lead">
				<fmt:message key="fracao.div.description" />
			</p>
		</div>
	</header>

	<section id="about">
		<div class="container">

			<form action="${s:mvcUrl('calcular_div_fracao').build() }" method="post">
			
				<div id="expressoes" class="form-group">
				
					<div class="form-group row">
						<div>
							<select name="sinais">
								<option value="+" label="+" selected="selected" ></option>
								<option value="-" label="-"/>
							</select>
						</div>
						<div style="padding-left: 5px">
							<input name="numeradores" type="number" min="1" max="999999" class="form-control input-xs" value="1"/> 							
							<label>&#x97;&#x97;&#x97;&#x97;</label>
							<input name="denominadores" type="number" min="1" max="999999" class="form-control input-xs" value="2"/>
						</div>						
						<div style="padding: 40px 0px 0px 10px">
							<label class="form-control">&divide;</label>
						</div>
						<div style="padding-left: 0px">
							<select name="sinais">
								<option value="+" label="+" selected="selected" ></option>
								<option value="-" label="-"/>
							</select>
						</div>
						<div style="padding-left: 5px">
							<input name="numeradores" type="number" min="1" max="999999" class="form-control input-xs" value="1"/> 							
							<label>&#x97;&#x97;&#x97;&#x97;</label>
							<input name="denominadores" type="number" min="1" max="999999" class="form-control input-xs" value="2"/>
						</div>
					</div>	
					
				</div>											
					
				<div>
					<p>
						<button type="submit" class="btn btn-primary"><fmt:message key="fracao.div.calculate" /></button>
					</p>
				</div>
		
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