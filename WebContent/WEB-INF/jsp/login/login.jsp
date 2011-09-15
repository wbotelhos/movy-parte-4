<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<title>Movy | A Movie Manager</title>

		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.14.css"/>

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.14.min.js"></script>
	</head>
	<body>
		<c:if test="${not empty error}">${error}</c:if>
		<c:if test="${not empty message}">${message}</c:if>

		<form action="${pageContext.request.contextPath}/autenticar" method="post">
			E-mail: <input type="text" name="usuario.email"/>
			Senha: <input type="text" name="usuario.senha"/>

			<input type="submit" value="acessar"/>
		</form>
	</body>
</html>