<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<tags:pageTemplate titulo="Math">

<spring:message code="potencia.soma.add.operacao" var="potenciaAddOperacao"/>
<spring:message code="potencia.soma.del.operacao" var="potenciaDelOperacao"/>

	<header class="bg-primary text-white">
		<div class="container text-center">
			<h1>
				<fmt:message key="potencia.soma.title" />
			</h1>
			<p class="lead">
				<fmt:message key="potencia.soma.description" />
			</p>
		</div>
	</header>

	<section id="about">
		<div class="container">

			<form action="${s:mvcUrl('calcular_ss_potencia').build() }" method="post">
			
				<div id="expressoes" class="form-group">
				
					<div class="form-group row" id="divExpoentes">
						<div>
							<label>Expoentes: </label>
						</div>
						<div>
							<select name="sinaisExpoente">
								<option value="+" label="+" selected="selected" ></option>
								<option value="-" label="-"/>
							</select>
						</div>							
						<div style="padding-left: 5px">
							<input name="expoentes" type="number" min="1" max="999999" class="form-control input-xs" value="2"/> 							
						</div>							
						<div>
							<select name="sinaisExpoente">
								<option value="+" label="+" selected="selected" ></option>
								<option value="-" label="-"/>
							</select>
						</div>							
						<div style="padding-left: 5px">
							<input name="expoentes" type="number" min="1" max="999999" class="form-control input-xs" value="3"/>
						</div>						
					</div>	
				
					<div class="form-group row" id="divBases">
						<div>
							<label>Bases: </label>
						</div>
						<div>
							<select name="sinaisBase">
								<option value="+" label="+" selected="selected" ></option>
								<option value="-" label="-"/>
							</select>
						</div>							
						<div style="padding-left: 5px">
							<input name="bases" type="number" min="1" max="999999" class="form-control input-xs" value="2"/> 							
						</div>							
						<div>
							<select name="sinaisBase">
								<option value="+" label="+" selected="selected" ></option>
								<option value="-" label="-"/>
							</select>
						</div>							
						<div style="padding-left: 5px">
							<input name="bases" type="number" min="1" max="999999" class="form-control input-xs" value="3"/>
						</div>						
					</div>				
					
					<div>
						<p>
							<input class="btn btn-primary" type="button" value="${potenciaAddOperacao}" onclick="add();"/> 
							<input class="btn btn-primary" type="button" value="${potenciaDelOperacao}" onclick="remove();"/>
						</p>
					</div>
					
				</div>
					
				<button type="submit" class="btn btn-primary">
					<fmt:message key="potencia.soma.calculate" />
				</button>
		
			</form>

		</div>
	</section>

	<script type="text/javascript">
		function add() {
			var divExpoentes = document.getElementById("divExpoentes");
			var divBases = document.getElementById("divBases");
			addExpoente(divExpoentes);
			addBase(divBases);
		}

		function remove() {
			var divExpoentes = document.getElementById("divExpoentes");
			var divBases = document.getElementById("divBases");
			
			var qtChilds = divExpoentes.childElementCount;
			
			if (qtChilds <= 5) {
				return;
			}
			
			removeLastChild(divExpoentes);			
			removeLastChild(divExpoentes);
			
			removeLastChild(divBases);			
			removeLastChild(divBases);
		}
		
		function removeLastChild(element) {
			element.removeChild(element.lastChild);
		}
		
		function addSinal(name){
			var divSinal = document.createElement("div");
			var sinal = document.createElement("select");
			sinal.setAttribute("name", name);
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
		
		function addInput(name){
			var input = document.createElement("input");
			input.setAttribute("name", name);
			input.setAttribute("type", "number");
			input.setAttribute("min", "1");
			input.setAttribute("max", "9999");
			input.setAttribute("class", "form-control input-xs");
			input.setAttribute("value", "1");
			return input;
		}
		
		function addExpoente(divExpoente) {
			divExpoente.appendChild(addSinal("sinaisExpoente"));
			divExpoente.appendChild(addInput("expoentes"));
		}
		
		function addBase(divBase) {
			divBase.appendChild(addSinal("sinaisBase"));
			divBase.appendChild(addInput("bases"));
		}
	</script>

</tags:pageTemplate>