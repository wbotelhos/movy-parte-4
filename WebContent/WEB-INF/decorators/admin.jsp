<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<title><decorator:title default="Movy | A Movie Manager"/></title>

		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.14.css"/>

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.14.min.js"></script>
	</head>
	<body>
		<div id="topo"><jsp:include page="../../topo.jsp" /></div>
		<div id="menu"><jsp:include page="../../menu.jsp" /></div>

		<div id="conteudo">
			<c:if test="${not empty error}">${error}</c:if>
			<c:if test="${not empty message}">${message}</c:if>

			<decorator:body/>
		</div>

		<div id="rodape"><jsp:include page="../../rodape.jsp" /></div>
	</body>
</html>