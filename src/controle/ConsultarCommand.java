package controle;

import java.util.ArrayList;

import dominio.Aluno;
import dominio.EntidadeDominio;


public class ConsultarCommand extends AbstractCommand{
	
	public Object execute(EntidadeDominio entidade) {
		
		return fachada.buscarTodos(entidade);
	}
}
