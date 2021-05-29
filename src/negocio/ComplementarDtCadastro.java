package negocio;

import java.util.Date;

import dominio.EntidadeDominio;

public class ComplementarDtCadastro implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		entidade.setDtCadastro(new Date());
		return null;
	}

}
