<head>
	<title>Movy | Filme [listagem]</title>
</head>
<body>
	<c:forEach items="${filmeList}" var="filme">
		<h2>${filme.titulo}</h2>

		(${filme.tituloOriginal} - ${filme.genero} - ${filme.ano})
		<br/>

		${filme.sinopse}

		<form action="${pageContext.request.contextPath}/filme/${filme.id}" method="get">
			<input type="submit" value="exibir"/>
		</form>

		<form action="${pageContext.request.contextPath}/filme/${filme.id}/editar" method="get">
			<input type="submit" value="editar"/>
		</form>

		<form action="${pageContext.request.contextPath}/filme/${filme.id}" method="post">
			<input type="hidden" name="_method" value="delete"/>

			<input type="submit" value="remover"/>
		</form>
	</c:forEach>
</body>