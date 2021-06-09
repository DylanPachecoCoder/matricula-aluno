package negocio;

import dominio.Endereco;
import dominio.EntidadeDominio;

public class ValidarEndereco implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
Endereco endereco = (Endereco)entidade;
		
		StringBuilder sb = new StringBuilder();
		
		if(endereco.getLogradouro() == null || 
				endereco.getLogradouro().trim().equals("")) {
			sb.append("Logradouro é um campo obrigatório<br>");			
		}
		
		if(endereco.getCep() == null || 
				endereco.getCep().trim().equals("")) {
			sb.append("CEP  é um campo obrigatório<br>");			
		}
		
		if(endereco.getNumero() == null || 
				endereco.getNumero().trim().equals("")) {
			sb.append("Número  é um campo obrigatório<br>");			
		}
		
		if(endereco.getCidade().getDescricao() == null || 
				endereco.getCidade().getDescricao().trim().equals("")) {
			sb.append("Cidade  é um campo obrigatório<br>");			
		}
		
		if(endereco.getCidade().getEstado().getDescricao() == null || 
				endereco.getCidade().getEstado().getDescricao().trim().equals("")) {
			sb.append("Estado  é um campo obrigatório<br>");			
		}
		
		if(sb.length()==0)
			return null;
		else
			return sb.toString();
	}

}
