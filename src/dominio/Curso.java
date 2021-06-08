package dominio;

import java.util.List;

public class Curso extends EntidadeDominio {
	
	private String descricao;
	private List<Materia> materias;
	private Periodo periodo;
	
	public Curso() {}
	
	public Curso(String descricao, Periodo periodo) {
		this.descricao = descricao;
		this.periodo = periodo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
}
