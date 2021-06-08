package controle;

import dominio.EntidadeDominio;

public interface ICommand {
	public Object execute(EntidadeDominio entidade);
	
}
