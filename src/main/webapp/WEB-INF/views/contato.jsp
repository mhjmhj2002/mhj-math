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
		<section id="about">
		<div class="container">
			<form:form action="${s:mvcUrl('HC#contatar').build() }" method="POST"
				commandName="contato">
				<div class="form-group">
					<div class="col-md-12">
						<div class="form-group row">
							<div class="col-md-1">
								<form:select path="sinalA" id="sinalA">
									<form:option value=""></form:option>
									<c:forEach var="sinal" items="${sinais}">
										<option value="${sinal}" label="${sinal.texto}"
											${sinal.texto == '+' ? 'selected="selected"' : ''}></option>
									</c:forEach>
								</form:select>
								<form:errors path="sinalA" />
							</div>
							<div class="col-md-1">
								<form:input path="a" type="number" min="1" max="9999"
									cssClass="form-control" id="a" value="1" />
								<form:errors path="a" />
							</div>
							<div class="col-md-1">
								<label class="control-label">X2 <!-- 								<MATH xmlns="http://www.w3.org/1998/Math/MathML" display="block"> -->
									<!-- 								</MATH> -->
								</label>
							</div>

							<div class="col-md-1">
								<form:select path="sinalB" id="sinalB">
									<form:option value=""></form:option>
									<c:forEach var="sinal" items="${sinais}">
										<option value="${sinal}" label="${sinal.texto}"
											${sinal.texto == '+' ? 'selected="selected"' : ''}></option>
									</c:forEach>
								</form:select>
								<form:errors path="sinalB" />
							</div>
							<div class="col-md-1">
								<form:input path="b" type="number" min="1" max="9999"
									cssClass="form-control" id="b" value="2" />
							</div>
								<form:errors path="b" />
							<div class="col-md-1">
								<label class="control-label">X</label>
							</div>

							<div class="col-md-1">
								<form:select path="sinalC" id="sinalC">
									<form:option value=""></form:option>
									<c:forEach var="sinal" items="${sinais}">
										<option value="${sinal}" label="${sinal.texto}"
											${sinal.texto == '+' ? 'selected="selected"' : ''}></option>
									</c:forEach>
								</form:select>
								<form:errors path="sinalC" />
							</div>
							<div class="col-md-1">
								<form:input path="c" type="number" min="1" max="9999"
									cssClass="form-control" id="c" value="3" />
								<form:errors path="c" />
							</div>

							<div class="col-md-1">
								<label class="control-label">=</label>
							</div>

							<div class="col-md-1">
								<label class="control-label">0</label>
							</div>
						</div>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">
					<fmt:message key="equacao.calculate" />
				</button>
			</form:form>
		</div>
	</section>
</tags:pageTemplate>