package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.ConsultarCommand;
import controle.ICommand;
import controle.IViewHelper;
import controle.SalvarCommand;
import controle.VhAluno;
import dao.AlunoDAO;
import dominio.Aluno;
import dominio.AlunoNovo;
import dominio.Cidade;
import dominio.Curso;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Estado;
import dominio.Materia;
import dominio.Professor;
import dominio.Semestre;
import dominio.StatusMateria;
import dominio.TipoEndereco;
import facade.Facade;
import facade.IFacade;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Aluno aluno = new Aluno();
	IFacade facade = new Facade();
	
	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;

	public Controller() {
		super();
		commands = new HashMap<String, ICommand>();
		commands.put("/insert", new SalvarCommand());
//		commands.put("EXCLUIR", new ExcluirCommand());
//		commands.put("/main", new ConsultarCommand());
//		commands.put("ALTERAR", new AlterarCommand());
		
		vhs = new HashMap<String, IViewHelper>();
		vhs.put("/MatriculaAluno/insert", new VhAluno());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getServletPath();
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		if(uri.equals("/MatriculaAluno/insert")){
			IViewHelper vh = vhs.get(uri);
			EntidadeDominio entidade = vh.getEntidade(request);
			
			ICommand cmd = commands.get(action);
			
			Object msg = cmd.execute(entidade);
			vh.setView(msg, request, response);
		}

		
		if (action.equals("/main")) {
			contatos(request, response);
		} 
//		else if (action.equals("/insert")) {
//			novoAluno(request, response);
//		} 
		else if (action.equals("/select")) {
			buscarAluno(request, response);
		} else if (action.equals("/update")) {
			editarAluno(request, response);
		} else if (action.equals("/delete")) {
			removerAluno(request, response);
		} 
//		else {
//			response.sendRedirect("index.html");
//		}
	}

	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<Aluno> alunos = facade.buscarTodos(aluno);
		request.setAttribute("alunos", alunos);
		RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");
		rd.forward(request, response);
	}

	protected void novoAluno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		aluno.setNome(request.getParameter("nome"));
		aluno.setFone(request.getParameter("fone"));
		aluno.setEmail(request.getParameter("email"));
		
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
