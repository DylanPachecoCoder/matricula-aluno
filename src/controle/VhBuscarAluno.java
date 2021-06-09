package controle;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Aluno;
import dominio.EntidadeDominio;

public class VhBuscarAluno implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Aluno aluno = new Aluno();
		int idAluno = Integer.parseInt(request.getParameter("id"));
		aluno.setId(idAluno);
		return aluno;
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		try {
			if (resultado != null) {
				Aluno aluno = (Aluno)resultado;
				
				request.setAttribute("id", aluno.getId());
				request.setAttribute("nome", aluno.getNome());
				request.setAttribute("dataNascimento", aluno.getDataNascimento());
				request.setAttribute("cpf", aluno.getCpf());
				request.setAttribute("rg", aluno.getRg());
				request.setAttribute("logradouro", aluno.getEndereco().getLogradouro());
				request.setAttribute("cep", aluno.getEndereco().getCep());
				request.setAttribute("numero", aluno.getEndereco().getNumero());
				request.setAttribute("cidade", aluno.getEndereco().getCidade().getDescricao());
				request.setAttribute("estado", aluno.getEndereco().getCidade().getEstado().getDescricao());
				
				if(aluno.getEndereco().getComplemento().equals("")) {
					request.setAttribute("complemento", "Complemento");
				}else {
					request.setAttribute("complemento", aluno.getEndereco().getComplemento());
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
				rd.forward(request, response);
				
			} else {
				response.sendRedirect("main");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

	}

}
