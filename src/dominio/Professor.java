package dominio;

public class Professor extends Pessoa{
	
	private String qualificacao;

	public Professor() {}
	
	public Professor(String nome, String dataNascimento, Endereco endereco, String cpf, String rg, String qualificacao) {
		super(nome, dataNascimento, endereco, cpf, rg);
		this.qualificacao = qualificacao;
	}

	public String getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(String qualificacao) {
		this.qualificacao = qualificacao;
	}
}
