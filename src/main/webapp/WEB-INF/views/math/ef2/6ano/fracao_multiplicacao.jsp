<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<tags:pageTemplate titulo="Math">

<spring:message code="fracao.mult.add.operacao" var="mccAddOperacao"/>
<spring:message code="fracao.mult.del.operacao" var="mccDelOperacao"/>

	<header class="bg-primary text-white">
		<div class="container text-center">
			<h1>
				<fmt:message key="fracao.mult.title" />
			</h1>
			<p class="lead">
				<fmt:message key="fracao.mult.description" />
			</p>
		</div>
	</header>

	<section id="about">
		<div class="container">

			<form action="${s:mvcUrl('calcular_mult_fracao').build() }" method="post">
			
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
							<label class="form-control">*</label>
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
					
					<div>
						<p>
							<input class="btn btn-primary" type="button" value="${mccAddOperacao}" onclick="add();"/> 
							<input class="btn btn-primary" type="button" value="${mccDelOperacao}" onclick="remove();"/>
						</p>
					</div>
					
				</div>
					
				<button type="submit" class="btn btn-primary">
					<fmt:message key="fracao.mult.calculate" />
				</button>
		
			</form>

		</div>
	</section>

	<script type="text/javascript">
		function add() {
			var expressoes = document.getElementById("expressoes");
			var lastRow = expressoes.lastChild;
			var qtChilds = lastRow.childElementCount;
			
			if (qtChilds == 3 ) {
				lastRow.appendChild(addOperando());
				lastRow.appendChild(addSinal());
				lastRow.appendChild(addFracao());
			} else {
				expressoes.appendChild(addRow());
			}
		}

		function remove() {
			var expressoes = document.getElementById("expressoes");
			var qtRows = expressoes.childElementCount;
			
			if (qtRows == 1) {
				return;
			}

			var lastRow = expressoes.lastChild;
			var qtChilds = lastRow.childElementCount;
			
			if (qtChilds == 3 ) {
				removeLastRow(expressoes);
			} else {
				removeLastCel(lastRow);
			}
		}
		
		function removeLastRow(expressoes) {
			expressoes.removeChild(expressoes.lastChild);
		}
		
		function removeLastCel(lastRow) {
			lastRow.removeChild(lastRow.lastChild);
			lastRow.removeChild(lastRow.lastChild);
			lastRow.removeChild(lastRow.lastChild);
		}
		
		function addCel() {
			var expressoes = document.getElementById("expressoes");
			var lastRow = expressoes.lastChild;
			var qtChilds = lastRow.childElementCount;
			
			alert(qtChilds);
			
			if (qtChilds == 3 ) {
				lastRow.appendChild(addOperando());
				lastRow.appendChild(addSinal());
				lastRow.appendChild(addFracao());
			} else {
				expressoes.appendChild(addRow());
			}
			
		}
		
		function addRow() {
			var row = document.createElement("div");
			row.setAttribute("class", "form-group row");
			row.appendChild(addOperando());
			row.appendChild(addSinal());
			row.appendChild(addFracao());
			return row;
		}
		
		function addOperando() {	
			var operando = document.createElement("div");
			operando.setAttribute("style", "40px 0px 0px 10px");
			
			var lbl = document.createElement("label");
			lbl.setAttribute("class", "form-control");
			lbl.innerHTML = "*";
			
			operando.appendChild(lbl);
			
			return operando;
		}
		
		function addFracao() {
			var fracao = document.createElement("div");
			var numerador = document.createElement("input");
			numerador.setAttribute("name", "numeradores");
			numerador.setAttribute("type", "number");
			numerador.setAttribute("min", "1");
			numerador.setAttribute("max", "9999");
			numerador.setAttribute("class", "form-control input-xs");
			numerador.setAttribute("value", "1");
			var divisor = document.createElement("label");
			divisor.innerHTML = "&#x97;&#x97;&#x97;&#x97;";
			var denominador = document.createElement("input");
			denominador.setAttribute("name", "denominadores");
			denominador.setAttribute("type", "number");
			denominador.setAttribute("min", "1");
			denominador.setAttribute("max", "9999");
			denominador.setAttribute("class", "form-control input-xs");
			denominador.setAttribute("value", "2");
			fracao.appendChild(numerador);
			fracao.appendChild(divisor);
			fracao.appendChild(denominador);
			return fracao;
		}
		
		function addSinal(){
			var divSinal = document.createElement("div");
			var sinal = document.createElement("select");
			sinal.setAttribute("name", "sinais");
			var option = document.createElement("option");
			option.setAttribute("value", "+");
			option.setAttribute("label", "+");
			option.setAttribute("selected", "selected");
			sinal.appendChild(option);
			option = document.createElement("option");
			option.setAttribute("value", "-");
			option.setAttribute("label", "-");
			sinal.appendChild(option);
			divSinal.appendChild(sinal);
			return divSinal;
		}
	</script>
	

</tags:pageTemplate>