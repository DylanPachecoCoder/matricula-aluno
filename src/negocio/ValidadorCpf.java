package negocio;

import dominio.Aluno;
import dominio.EntidadeDominio;

public class ValidadorCpf implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Aluno aluno = (Aluno)entidade;
		if(aluno.getCpf().length() != 11) {
			return "CPF inválido\n";
		}
		return null;
	}

}
