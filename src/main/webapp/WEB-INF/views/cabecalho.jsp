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
										<li><a class="dropdown-item" href="#">Desenvolvendo...</a></li>
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
										<li><a class="dropdown-item" href="#">Desenvolvendo...</a></li>
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
										<li><a class="dropdown-item" href="${mathPath}/equacao">Equação</a></li>
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
					<a class="nav-link js-scroll-trigger" href="#about"><fmt:message key="header.about"/></a>
				</li>
				<li class="nav-item">
					<a class="nav-link js-scroll-trigger" href="${homePath}contato"><fmt:message key="header.contact"/></a>
				</li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="?locale=pt" rel="nofollow"> <fmt:message key="menu.pt" />
				</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="?locale=es" rel="nofollow"> <fmt:message key="menu.es" />
				</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="?locale=en" rel="nofollow"> <fmt:message key="menu.en" />
				</a></li>

				<!-- original -->
<!-- 				<li class="nav-item dropdown"><a -->
<!-- 					class="nav-link dropdown-toggle" href="#" -->
<!-- 					id="navbarDropdownPortfolio" data-toggle="dropdown" -->
<%-- 					aria-haspopup="true" aria-expanded="false"> <fmt:message --%>
<%-- 							key="header.operations" /> --%>
<!-- 				</a> -->
<!-- 					<div class="dropdown-menu dropdown-menu-right" -->
<!-- 						aria-labelledby="navbarDropdownPortfolio"> -->
<%-- 						<a class="dropdown-item" href="${mathPath}/equacao"><fmt:message --%>
<%-- 								key="header.operations.1" /></a> <a class="dropdown-item" --%>
<%-- 							href="portfolio-2-col.html"><fmt:message --%>
<%-- 								key="header.operations.2" /></a> <a class="dropdown-item" --%>
<%-- 							href="portfolio-3-col.html"><fmt:message --%>
<%-- 								key="header.operations.3" /></a> <a class="dropdown-item" --%>
<%-- 							href="portfolio-4-col.html"><fmt:message --%>
<%-- 								key="header.operations.4" /></a> <a class="dropdown-item" --%>
<%-- 							href="portfolio-item.html"><fmt:message --%>
<%-- 								key="header.operations.5" /></a> --%>
<!-- 					</div></li> -->
			</ul>
		</div>
	</div>
</nav>
<!--   <header id="layout-header"> -->
<!--     <div class="clearfix container"> -->
<%--         <a href="${s:mvcUrl('HC#index').build() }" id="logo"> --%>
<!--           <img src="http://cdn.shopify.com/s/files/1/0155/7645/t/177/assets/casa-do-codigo-blue.svg?15063963123751285545" alt="Casa do Codigo"> -->
<!--         </a> -->
<!--       <div id="header-content"> -->
<!--         <nav id="main-nav"> -->
<!--               <ul class="clearfix"> -->
<!--                   <li> -->
<%--                       <a href="${s:mvcUrl('CCC#itens').build() }" rel="nofollow"> --%>
<%--                       	<s:message code="menu.carrinho" --%>
<%--                       		arguments="${carrinhoCompras.quantidade }" /> --%>
<!--                       </a> -->

<!--                   </li> -->
<!--                   <li> -->
<!--                       <a href="/pages/sobre-a-casa-do-codigo" rel="nofollow"> -->
<%--                       	<fmt:message key="menu.sobre" /> --%>
<!--                       </a> -->
<!--                   </li> -->
<!--                   <li> -->
<!--                       <a href="?locale=pt" rel="nofollow"> -->
<%--                       	<fmt:message key="menu.pt" /> --%>
<!--                       </a> -->
<!--                   </li> -->
<!--                   <li> -->
<!--                       <a href="?locale=en_US" rel="nofollow"> -->
<%--                       	<fmt:message key="menu.en" /> --%>
<!--                       </a> -->
<!--                   </li> -->
<!--               </ul> -->
<!--         </nav> -->
<!--       </div> -->
<!--     </div> -->
<!--   </header> -->
<!--   <nav class="categories-nav"> -->
<!--     <ul class="container"> -->
<!--         <li class="category"> -->
<%--         		<a href="${s:mvcUrl('HC#index').build() }"> --%>
<%--         			<fmt:message key="navegacao.categoria.home" /></a> --%>

<!--                 <li class="category"><a href="/collections/livros-de-agile"> -->
<%--                 		<fmt:message key="navegacao.categoria.agile" /></a> --%>
<!--                 <li class="category"><a href="/collections/livros-de-front-end"> -->
<%--                     <fmt:message key="navegacao.categoria.front_end" /></a> --%>
<!--                 <li class="category"><a href="/collections/livros-de-games"> -->
<%--                     <fmt:message key="navegacao.categoria.games" /> --%>
<!--                   </a> -->
<!--                 <li class="category"><a href="/collections/livros-de-java"> -->
<%--                     <fmt:message key="navegacao.categoria.java" /> --%>
<!--                   </a> -->
<!--                 <li class="category"><a href="/collections/livros-de-mobile"> -->
<%--                     <fmt:message key="navegacao.categoria.mobile" /> --%>
<!--                   </a> -->
<!--                 <li class="category"><a href="/collections/livros-desenvolvimento-web"> -->
<%--                     <fmt:message key="navegacao.categoria.web" /> --%>
<!--                   </a> -->
<!--                 <li class="category"><a href="/collections/outros"> -->
<%--                     <fmt:message key="navegacao.categoria.outros" /> --%>
<!--                   </a> -->
<!--     </ul> -->
<!--   </nav> -->
