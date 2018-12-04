<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
	id="mainNav">
	<div class="container">
		<a class="navbar-brand js-scroll-trigger" href="${homePath}#page-top">
			<fmt:message key="header.home" />
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown">
					<a id="dLabel" role="button" data-toggle="dropdown" class="nav-link dropdown-toggle" data-target="#" href="/page.html"><fmt:message	key="header.operations"/> 
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">
						<li class="dropdown-submenu">
							<a class="dropdown-item" tabindex="-1" href="#">EF1</a>
							<ul class="dropdown-menu">
								<li class="dropdown-submenu">
									<a class="dropdown-item" href="#">1 Ano</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Desenvolvendo...</a></li>
									</ul>
								</li>
								<li class="dropdown-submenu">
									<a class="dropdown-item" href="#">2 Ano</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Desenvolvendo...</a></li>
									</ul>
								</li>
								<li class="dropdown-submenu">
									<a class="dropdown-item" href="#">3 Ano</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Desenvolvendo...</a></li>
									</ul>
								</li>
								<li class="dropdown-submenu">
									<a class="dropdown-item" href="#">4 Ano</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Desenvolvendo...</a></li>
									</ul>
								</li>
								<li class="dropdown-submenu">
									<a class="dropdown-item" href="#">5 Ano</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Desenvolvendo...</a></li>
									</ul>
								</li>
							</ul>
						</li>
						<li class="divider"></li>					
						<li class="dropdown-submenu">
							<a class="dropdown-item" tabindex="-1" href="#">EF2</a>
							<ul class="dropdown-menu">
								<li class="dropdown-submenu">
									<a class="dropdown-item" href="#">6 Ano</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="${mathPath}/fracao/divisao">Frações Divisão</a></li>
										<li><a class="dropdown-item" href="${mathPath}/fracao/multiplicacao">Frações Multiplicação</a></li>
										<li><a class="dropdown-item" href="${mathPath}/fracao/simplificacao">Frações Simplificação</a></li>
										<li><a class="dropdown-item" href="${mathPath}/fracao/soma">Frações Soma</a></li>
										<li><a class="dropdown-item" href="${mathPath}/mdc">MDC</a></li>
										<li><a class="dropdown-item" href="${mathPath}/mmc">MMC</a></li>
									</ul>
								</li>
								<li class="dropdown-submenu">
									<a class="dropdown-item" href="#">7 Ano</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Desenvolvendo...</a></li>
									</ul>
								</li>
								<li class="dropdown-submenu">
									<a class="dropdown-item" href="#">8 Ano</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="${mathPath}/potencia/soma">Frações Soma</a></li>
									</ul>
								</li>
								<li class="dropdown-submenu">
									<a class="dropdown-item" href="#">9 Ano</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Desenvolvendo...</a></li>
									</ul>
								</li>
							</ul>
						</li>
						<li class="divider"></li>
						<li class="dropdown-submenu">
							<a class="dropdown-item" tabindex="-1" href="#">EM</a>
							<ul class="dropdown-menu">
<!-- 								<li><a class="dropdown-item" tabindex="-1" href="#">Second level</a></li> -->
								<li class="dropdown-submenu">
									<a class="dropdown-item" href="#">1 Ano</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="${mathPath}/equacao">Equação de Segundo Grau</a></li>
<!-- 										<li><a class="dropdown-item" href="#">3rd level</a></li> -->
									</ul>
								</li>
								<li class="dropdown-submenu">
									<a class="dropdown-item" href="#">2 Ano</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Desenvolvendo...</a></li>
									</ul>
								</li>
								<li class="dropdown-submenu">
									<a class="dropdown-item" href="#">3 Ano</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Desenvolvendo...</a></li>
									</ul>
								</li>
<!-- 								<li><a class="dropdown-item" href="#">Second level</a></li> -->
<!-- 								<li><a class="dropdown-item" href="#">Second level</a></li> -->
							</ul>
						</li>
					</ul>
				</li>
				<li class="nav-item">
					<a class="nav-link js-scroll-trigger" href="${homePath}#about"><fmt:message key="header.about"/></a>
				</li>
				<li class="nav-item">
					<a class="nav-link js-scroll-trigger" href="${homePath}contato"><fmt:message key="header.contact"/></a>
				</li>
				<li class="nav-item">
					<a class="nav-link js-scroll-trigger" href="${homePath}#features">Atualizações</a>
				</li>
<!-- 				<li class="nav-item"><a class="nav-link js-scroll-trigger" -->
<%-- 					href="?locale=pt" rel="nofollow"> <fmt:message key="menu.pt" /> --%>
<!-- 				</a></li> -->
<!-- 				<li class="nav-item"><a class="nav-link js-scroll-trigger" -->
<%-- 					href="?locale=es" rel="nofollow"> <fmt:message key="menu.es" /> --%>
<!-- 				</a></li> -->
<!-- 				<li class="nav-item"><a class="nav-link js-scroll-trigger" -->
<%-- 					href="?locale=en" rel="nofollow"> <fmt:message key="menu.en" /> --%>
<!-- 				</a></li> -->
			</ul>
		</div>
	</div>
</nav>