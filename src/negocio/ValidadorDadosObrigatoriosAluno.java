package negocio;

import dominio.Aluno;
import dominio.EntidadeDominio;

public class ValidadorDadosObrigatoriosAluno implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Aluno aluno = (Aluno)entidade;
		StringBuilder sb = new StringBuilder();
		
		if(aluno.getNome() == null || aluno.getNome().trim().equals("")) {
			sb.append("Nome é um campo obrigatório<br>");	
		}
		if(aluno.getDataNascimento() == null ||aluno.getDataNascimento().trim().equals("")) {
			sb.append("Data Nascimento é um campo obrigatório<br>");
			
		} 
		if(aluno.getCpf() == null || aluno.getCpf().trim().equals("")) {
			sb.append("CPF é um campo obrigatório<br>");			
		}
		if(aluno.getRg() == null || aluno.getRg().trim().equals("")) {
			sb.append("Rg é um campo obrigatório<br>");			
		}
		ValidarEndereco vEndereco = new ValidarEndereco();
		
		String validacaoEnd = vEndereco.processar(aluno.getEndereco());
		
		if(validacaoEnd != null) {
			sb.append(validacaoEnd);
		}				
			
		if(sb.length()==0)
			return null;
		else
			return sb.toString();
	}
	
}
