package controle;

import dominio.EntidadeDominio;

public class AtualizarCommand extends AbstractCommand{
	
	public Object execute(EntidadeDominio entidade) {		
		return fachada.atualizar(entidade);
	}

}
