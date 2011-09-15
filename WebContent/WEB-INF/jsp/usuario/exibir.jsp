<head>
	<title>Movy | Usu&aacute;rio [exibir]</title>
</head>
<body>
	<img src="${pageContext.request.contextPath}/usuario/${usuario.id}/imagem" alt="${not empty usuario.nome ? usuario.nome : usuario.nomeOriginal}" width="120" height="130" /><br/><br/>

	<form action="${pageContext.request.contextPath}/usuario/${usuario.id}/imagem" enctype="multipart/form-data" method="post">
		<input type="file" name="file"/>
		<input type="submit" value="enviar"/>
	</form><br/><br/>

	<form action="${pageContext.request.contextPath}/usuario/${usuario.id}/imagem" method="post">
		<input type="hidden" name="_method" value="delete"/>
		<input type="submit" value="remover imagem"/>
	</form><br/><br/>

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
</body>