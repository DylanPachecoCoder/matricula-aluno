package dominio;

import java.util.List;

public class Curso {
	
	private String descricao;
	private List<Materia> materias;
	
	public Curso() {}
	
	public Curso(String descricao, List<Materia> materias) {
		this.descricao = descricao;
		this.materias = materias;
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
}
