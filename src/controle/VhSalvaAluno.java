package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Aluno;
import dominio.EntidadeDominio;

public class VhSalvaAluno implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
//		Estado estado = new Estado("SP");
//		Cidade cidade = new Cidade("Poá", estado);
//		Endereco endereco = new Endereco("casa", "366", "08560050", "", cidade, new TipoEndereco("casa", "casa"));
//		Semestre semestre = new Semestre();
//		Professor professor = new Professor("Rodrigo", "08/12/1995", endereco, null, "qualificacao");
//		Materia materia = new Materia("ES3", null, StatusMateria.ANDAMENTO, semestre, professor);
//		java.util.List<Materia> materias = new ArrayList<Materia>();
//		materias.add(materia);
//		Curso curso = new Curso("ADS", materias);
//		AlunoNovo aluno = new AlunoNovo("Dylan", "08/12/1995", endereco, null, semestre, semestre);
		
		Aluno aluno = new Aluno();
		aluno.setNome(request.getParameter("nome"));
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
