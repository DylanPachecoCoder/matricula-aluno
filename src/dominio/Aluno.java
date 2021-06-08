package dominio;

public class Aluno extends Pessoa  {
	
	private Semestre semestreInicial;
	private Curso curso;
	
	public Aluno() {}
	
	public Aluno(String nome, String dataNascimento, Endereco endereco, String cpf, String rg, 
			Semestre semestreInicial, Curso curso) {
		super(nome, dataNascimento, endereco, cpf, rg);
		this.semestreInicial = semestreInicial;
		this.curso = curso;
	}

	public Semestre getSemestreInicial() {
		return semestreInicial;
	}

	public void setSemestreInicial(Semestre semestreInicial) {
		this.semestreInicial = semestreInicial;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
