<head>
	<title>Movy | Filme [listagem]</title>
</head>
<body>
	<div style="width: 1000px; margin: 0 auto;">
		<table id="gridy"></table>
	</div>

	<script type="text/javascript">
		$(function() {
	
			$('#gridy').gridy({
				colsWidth:		[130, 130, 70, 60, 220, 90],
				find:			'titulo',
				headersName:	[['titulo', 'Título'], ['tituloOriginal', 'Title'], ['genero', 'Gênero'], ['ano', 'Ano'], ['sinopse', 'Sinopse']],
				loading:		'${pageContext.request.contextPath}/img/loading.gif',
				url:			'${pageContext.request.contextPath}/filme/gridy',
				width:			900
			});

		});
	</script>

	<script id="template" type="text/x-jquery-tmpl">
		<tr>
			<td>\${titulo}</td>
			<td>\${tituloOriginal}</td>
			<td>\${genero}</td>
			<td>\${ano}</td>
			<td>\${sinopse}</td>
	
			<td class="gridy-button">
				<form action="${pageContext.request.contextPath}/filme/\${id}" method="get">
					<input type="submit" value="exibir"/>
				</form>
			</td>

			<td class="gridy-button">
				<form action="${pageContext.request.contextPath}/filme/\${id}/editar" method="get">
					<input type="submit" value="editar"/>
				</form>
			</td>

			<td class="gridy-button">
				<form action="${pageContext.request.contextPath}/filme/\${id}" method="post">
					<input type="hidden" name="_method" value="delete"/>

					<input type="submit" value="remover"/>
				</form>
			</td>
		</tr>
	</script>
</body>