package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Aluno;
import dominio.Cidade;
import dominio.Curso;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Estado;
import dominio.Semestre;

public class VhAtualizarAluno implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		Estado estado = new Estado(request.getParameter("estado"));
		Cidade cidade = new Cidade(request.getParameter("cidade"), estado);
		Endereco endereco = new Endereco();
		endereco.setLogradouro(request.getParameter("logradouro"));
		endereco.setCep(request.getParameter("cep"));
		endereco.setNumero(request.getParameter("numero"));
		endereco.setComplemento(request.getParameter("complemento"));
		endereco.setCidade(cidade);
		endereco.setTpEndereco(request.getParameter("tipoEndereco"));
		
		Aluno aluno = new Aluno();
		aluno.setId(Integer.parseInt(request.getParameter("id")));
		aluno.setNome(request.getParameter("nome"));
		aluno.setCpf(request.getParameter("cpf"));
		aluno.setRg(request.getParameter("rg"));
		aluno.setDataNascimento(request.getParameter("dt_Nasc"));
		aluno.setEndereco(endereco);
		return aluno;
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		PrintWriter out;
		try {
			out = response.getWriter();
			if (resultado != null) {
				out.println(resultado);
			} else {
				response.sendRedirect("main");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
