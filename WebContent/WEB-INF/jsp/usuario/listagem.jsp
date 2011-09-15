<head>
	<title>Movy | Usu&aacute;rio [listagem]</title>
</head>
<body>
	<c:forEach items="${usuarioList}" var="usuario">
		<h2>${usuario.nome}</h2>

		(${usuario.email} - ${usuario.senha})<br/>

		<form action="${pageContext.request.contextPath}/usuario/${usuario.id}" method="get">
			<input type="submit" value="exibir"/>
		</form>

		<form action="${pageContext.request.contextPath}/usuario/${usuario.id}/editar" method="get">
			<input type="submit" value="editar"/>
		</form>

		<form action="${pageContext.request.contextPath}/usuario/${usuario.id}" method="post">
			<input type="hidden" name="_method" value="delete"/>

			<input type="submit" value="remover"/>
		</form>
	</c:forEach>
</body>