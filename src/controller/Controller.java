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
import facade.Facade;
import facade.IFacade;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AlunoDAO dao = new AlunoDAO();
	Aluno aluno = new Aluno();
	IFacade facade = new Facade();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoAluno(request, response);
		} else if (action.equals("/select")) {
			buscarAluno(request, response);
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
		
		ArrayList<Aluno> alunos = facade.buscarTodos();
		request.setAttribute("alunos", alunos);
		RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");
		rd.forward(request, response);
	}

	protected void novoAluno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		aluno.setNome(request.getParameter("nome"));
		aluno.setFone(request.getParameter("fone"));
		aluno.setEmail(request.getParameter("email"));
		
		facade.salvar(aluno);
		response.sendRedirect("main");
	}
	
	protected void buscarAluno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idAluno = Integer.parseInt(request.getParameter("id"));
		aluno.setId(idAluno);
		facade.buscar(aluno);
		
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
		
		facade.atualizar(aluno);
		response.sendRedirect("main");
		
	}

	protected void removerAluno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		aluno.setId(id);
		facade.deletar(aluno);
		response.sendRedirect("main");
	}
}
