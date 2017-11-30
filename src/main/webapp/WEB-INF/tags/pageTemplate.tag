<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="titulo" required="true"%>
<%@ attribute name="bodyClass" required="false"%>
<%@ attribute name="extraScripts" fragment="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="pt-br">
<head>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-110489225-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-110489225-1');
</script>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="matematica passo a passo">
<meta name="keywords" content="matematica, passo a passo, equação, equações, mmc, mdc, fração, frações, ef1, ef2, em, ensino fundmental, ensino medio">

<title>${titulo}</title>

<meta name="robots" content="index, follow">
<meta name="googlebot" content="index, follow">


<c:url value="/resources" var="resourcesPath" />
<c:url value="/math" var="mathPath" />
<c:url value="/" var="homePath" />

<!-- Bootstrap core CSS -->
<link href="${resourcesPath}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${resourcesPath}/css/scrolling-nav.css" rel="stylesheet">

<!-- Custom styles for mhj-math -->
<link href="${resourcesPath}/css/mhj-math.css" rel="stylesheet">

</head>
<body id="page-top">

	<%@include file="/WEB-INF/views/cabecalho.jsp"%>

	<jsp:doBody />

	<%@include file="/WEB-INF/views/rodape.jsp"%>

	<jsp:invoke fragment="extraScripts" />

	<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
	<ins class="adsbygoogle"
	     style="display:block"
	     data-ad-format="fluid"
	     data-ad-layout-key="-hb-f+32-2f-57"
	     data-ad-client="ca-pub-3680058830798497"
	     data-ad-slot="5210124612"></ins>
	<script>
	     (adsbygoogle = window.adsbygoogle || []).push({});
	</script>

</body>
</html>
