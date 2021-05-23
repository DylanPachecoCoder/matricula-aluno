package dominio;

public class Aluno extends EntidadeDominio  {

	private String nome;
	private String fone;
	private String email;
	
	public Aluno() {}
	
	public Aluno(String nome, String fone, String email) {
		super();
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}
	
	public Aluno(int id, String nome, String fone, String email) {
		super(id);
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
