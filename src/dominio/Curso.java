package dominio;

import java.util.List;

public class Curso extends EntidadeDominio {
	
	private String descricao;
	private List<Materia> materias;
	private String periodo;
	
	public Curso() {}
	
	public Curso(String descricao, String periodo) {
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

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
}
