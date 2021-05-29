package dominio;

import java.util.List;

public class AlunoNovo extends Pessoa {
	
	private Semestre semestreAtual;
	private Semestre semestreInicial;
	
	public AlunoNovo() {}
	
	public AlunoNovo(String nome, String dataNascimento, Endereco endereco, List<Documento> documentos, 
			Semestre semestreAtual, Semestre semestreInicial) {
		super(nome, dataNascimento, endereco, documentos);
		this.semestreAtual = semestreAtual;
		this.semestreInicial = semestreInicial;
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
}
