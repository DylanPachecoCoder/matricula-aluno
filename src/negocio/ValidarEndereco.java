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
			sb.append("Logradouro � um campo obrigat�rio<br>");			
		}
		
		if(endereco.getCep() == null || 
				endereco.getCep().trim().equals("")) {
			sb.append("CEP  � um campo obrigat�rio<br>");			
		}
		
		if(endereco.getNumero() == null || 
				endereco.getNumero().trim().equals("")) {
			sb.append("N�mero  � um campo obrigat�rio<br>");			
		}
		
		if(endereco.getCidade().getDescricao() == null || 
				endereco.getCidade().getDescricao().trim().equals("")) {
			sb.append("Cidade  � um campo obrigat�rio<br>");			
		}
		
		if(endereco.getCidade().getEstado().getDescricao() == null || 
				endereco.getCidade().getEstado().getDescricao().trim().equals("")) {
			sb.append("Estado  � um campo obrigat�rio<br>");			
		}
		
		if(sb.length()==0)
			return null;
		else
			return sb.toString();
	}

}
