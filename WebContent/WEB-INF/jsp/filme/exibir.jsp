<head>
	<title>Movy | Filme [exibir]</title>
</head>
<body>
	<img src="${pageContext.request.contextPath}/filme/${filme.id}/imagem" alt="${not empty filme.titulo ? filme.titulo : filme.tituloOriginal}" width="120" height="130" /><br/><br/>

	<form action="${pageContext.request.contextPath}/filme/${filme.id}/imagem" enctype="multipart/form-data" method="post">
		<input type="file" name="file"/>
		<input type="submit" value="enviar"/>
	</form><br/><br/>

	<form action="${pageContext.request.contextPath}/filme/${filme.id}/imagem" method="post">
		<input type="hidden" name="_method" value="delete"/>
		<input type="submit" value="remover imagem"/>
	</form><br/><br/>

	<h2>${filme.titulo}</h2>

	(${filme.tituloOriginal} - ${filme.genero} - ${filme.ano})<br/>

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
</body>