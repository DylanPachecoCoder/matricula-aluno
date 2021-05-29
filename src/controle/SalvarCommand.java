package controle;

import dominio.EntidadeDominio;

public class SalvarCommand extends AbstractCommand{
	
	public String execute(EntidadeDominio entidade) {		
		return fachada.salvar(entidade);
	}

}
