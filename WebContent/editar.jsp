<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cadastro de Alunos</title>
<link rel="icon" href="imagens/student.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar Aluno</h1>
	<form name="frmAluno" action="update">
		<table>
			<tr>
				<td><input type="text" name="id" class="Caixa1" value="<%out.print(request.getAttribute("id")); %>" readonly></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1" value="<%out.print(request.getAttribute("nome")); %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="Caixa2" value="<%out.print(request.getAttribute("fone")); %>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
<script src="scripts/validador.js"></script>
</body>
</html>