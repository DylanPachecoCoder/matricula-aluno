package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AlunoDAO;
import dominio.Aluno;
import dominio.EntidadeDominio;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlunoDAO dao = new AlunoDAO();
	Aluno aluno = new Aluno();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoAluno(request, response);
		} else if (action.equals("/select")) {
			listarAlunos(request, response);
		} else if (action.equals("/update")) {
			editarAluno(request, response);
		} else if (action.equals("/delete")) {
			removerAluno(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Aluno> alunos = new ArrayList<>();
		ArrayList<EntidadeDominio> lista = dao.listar();

		for (int i = 0; i < lista.size(); i++) {
			Aluno aluno = (Aluno) lista.get(i);
			alunos.add(aluno);
		}
		request.setAttribute("alunos", alunos);
		RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");
		rd.forward(request, response);
	}

	protected void novoAluno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		aluno.setNome(request.getParameter("nome"));
		aluno.setFone(request.getParameter("fone"));
		aluno.setEmail(request.getParameter("email"));

		dao.salvar(aluno);
		response.sendRedirect("main");
	}
	
	protected void listarAlunos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idAluno = Integer.parseInt(request.getParameter("id"));
		aluno.setId(idAluno);
		dao.selecionar(aluno);
		
		request.setAttribute("nome", aluno.getNome());
		request.setAttribute("fone", aluno.getFone());
		request.setAttribute("email", aluno.getEmail());
		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	protected void editarAluno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		aluno.setNome(request.getParameter("nome"));
		aluno.setFone(request.getParameter("fone"));
		aluno.setEmail(request.getParameter("email"));
		
		dao.alterar(aluno);
		response.sendRedirect("main");
		
	}

	protected void removerAluno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		aluno.setId(id);
		dao.deletar(aluno);
		response.sendRedirect("main");
	}
}
