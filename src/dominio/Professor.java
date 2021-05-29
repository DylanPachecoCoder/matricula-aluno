package dominio;

import java.util.List;

public class Professor extends Pessoa{
	
	private String qualificacao;

	public Professor() {}
	
	public Professor(String nome, String dataNascimento, Endereco endereco, List<Documento> documentos, String qualificacao) {
		super(nome, dataNascimento, endereco, documentos);
		this.qualificacao = qualificacao;
	}

	public String getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(String qualificacao) {
		this.qualificacao = qualificacao;
	}
}
