package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Aluno;
import dominio.EntidadeDominio;

public class VhConsultarAlunos implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		return null;
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		PrintWriter out;
		try {
			out = response.getWriter();
			if (resultado != null) {
				
				ArrayList<Aluno> alunos = (ArrayList<Aluno>)resultado;
				request.setAttribute("alunos", alunos);
				RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");
				rd.forward(request, response);
				
			} else {
				response.sendRedirect("main");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

	}

}
