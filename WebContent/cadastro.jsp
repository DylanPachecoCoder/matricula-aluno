<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="dominio.Aluno"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<Aluno> lista = (ArrayList<Aluno>) request.getAttribute("alunos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Cadastro de Alunos</title>
<link rel="icon" href="imagens/student.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Cadastro de Alunos</h1>
	<a href="novo.html" class="Botao1">Novo Aluno</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Cpf</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<%
				for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getCpf()%></td>
				<td>
				<a href="select?id=<%= lista.get(i).getId()%>" class="Botao1">Editar</a>
				<a href="javascript: confirmar(<%= lista.get(i).getId()%>)" class="Botao2">Excluir</a>
				
				</td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>