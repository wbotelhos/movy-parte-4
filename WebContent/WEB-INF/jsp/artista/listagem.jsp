<head>
	<title>Movy | Artista [listagem]</title>
</head>
<body>
	<c:forEach items="${artistaList}" var="artista">
		<h2>${artista.nome}</h2>

		(${artista.sexo.label} - <fmt:formatDate value='${artista.nascimento}' type='date' pattern='dd/MM/yyyy'/>)<br/><br/>

		<form action="${pageContext.request.contextPath}/artista/${artista.id}" method="get">
			<input type="submit" value="exibir"/>
		</form>

		<form action="${pageContext.request.contextPath}/artista/${artista.id}/editar" method="get">
			<input type="submit" value="editar"/>
		</form>

		<form action="${pageContext.request.contextPath}/artista/${artista.id}" method="post">
			<input type="hidden" name="_method" value="delete"/>

			<input type="submit" value="remover"/>
		</form>
	</c:forEach>
</body>