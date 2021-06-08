package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controle.AtualizarCommand;
import controle.BuscarCommand;
import controle.ConsultarCommand;
import controle.ExcluirCommand;
import controle.ICommand;
import controle.IViewHelper;
import controle.SalvarCommand;
import controle.VhAtualizarAluno;
import controle.VhBuscarAluno;
import controle.VhConsultarAlunos;
import controle.VhDeletaAluno;
import controle.VhSalvaAluno;
import dominio.EntidadeDominio;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;

	public Controller() {
		super();
		commands = new HashMap<String, ICommand>();
		commands.put("/insert", new SalvarCommand());
		commands.put("/delete", new ExcluirCommand());
		commands.put("/main", new ConsultarCommand());
		commands.put("/select", new BuscarCommand());
		commands.put("/update", new AtualizarCommand());
		
		vhs = new HashMap<String, IViewHelper>();
		vhs.put("/insert", new VhSalvaAluno());
		vhs.put("/delete", new VhDeletaAluno());
		vhs.put("/main", new VhConsultarAlunos());
		vhs.put("/select", new VhBuscarAluno());
		vhs.put("/update", new VhAtualizarAluno());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getServletPath();
			
		IViewHelper vh = vhs.get(action);
		EntidadeDominio entidade = vh.getEntidade(request);
		
		ICommand cmd = commands.get(action);
		
		Object obj = cmd.execute(entidade);
	
		vh.setView(obj, request, response);
	}
}
