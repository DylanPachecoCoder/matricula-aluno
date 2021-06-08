package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Aluno;
import dominio.EntidadeDominio;

public class VhAtualizarAluno implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Aluno aluno = new Aluno();
		aluno.setId(Integer.parseInt(request.getParameter("id")));
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
