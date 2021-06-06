package controle;

import dominio.EntidadeDominio;

public class ExcluirCommand extends AbstractCommand {

	@Override
	public Object execute(EntidadeDominio entidade) {
		return fachada.deletar(entidade);
	}

}
