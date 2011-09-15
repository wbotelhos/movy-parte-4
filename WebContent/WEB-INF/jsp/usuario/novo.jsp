<head>
	<title>Movy | Usu&aacute;rio [novo]</title>
</head>
<body>
	<fieldset style="width: 390px;">
		<legend>Usu&aacute;rio</legend>

		<form action="${pageContext.request.contextPath}/usuario" method="post">
			<input type="hidden" name="usuario.id" value="${usuario.id}"/>

			<label>Nome:</label>
			<input type="text" name="usuario.nome" value="${usuario.nome}"/><br/>

			<label>E-mail:</label>
			<input type="text" name="usuario.email" value="${usuario.email}"/><br/>

			<label>Senha:</label>
			<input type="text" name="usuario.senha" value="${usuario.senha}"/><br/>

			<input type="submit" value="salvar"/>
		</form>
	</fieldset>
</body>