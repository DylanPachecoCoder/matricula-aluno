package dominio;

import java.util.List;

public class Aluno extends Pessoa  {
	
	private Semestre semestreAtual;
	private Semestre semestreInicial;
	private Curso curso;
	
	public Aluno() {}
	
	public Aluno(String nome, String dataNascimento, Endereco endereco, List<Documento> documentos, 
			Semestre semestreAtual, Semestre semestreInicial, Curso curso) {
		super(nome, dataNascimento, endereco, documentos);
		this.semestreAtual = semestreAtual;
		this.semestreInicial = semestreInicial;
		this.curso = curso;
	}

	public Semestre getSemestreAtual() {
		return semestreAtual;
	}

	public void setSemestreAtual(Semestre semestreAtual) {
		this.semestreAtual = semestreAtual;
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
