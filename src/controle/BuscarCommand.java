package controle;

import dominio.EntidadeDominio;

public class BuscarCommand extends AbstractCommand{
	
	public Object execute(EntidadeDominio entidade) {		
		return fachada.buscar(entidade);
	}

}
