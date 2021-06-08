package controle;

import dominio.EntidadeDominio;


public class ConsultarCommand extends AbstractCommand{
	
	public Object execute(EntidadeDominio entidade) {
		
		return fachada.buscarTodos(entidade);
	}
}
