package controle;

import dominio.EntidadeDominio;

public class SalvarCommand extends AbstractCommand{
	
	public Object execute(EntidadeDominio entidade) {		
		return fachada.salvar(entidade);
	}

}
