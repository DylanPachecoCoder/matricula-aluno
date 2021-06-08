package dominio;

import java.util.ArrayList;

public class Materia {

	private String descricao;
	private ArrayList<Double> notas;
	private StatusMateria status;
	private Semestre semestre;
	private Professor professor;
	
	public Materia() {}

	public Materia(String descricao, ArrayList<Double> notas, StatusMateria status, Semestre semestre,
			Professor professor) {
		this.descricao = descricao;
		this.notas = notas;
		this.status = status;
		this.semestre = semestre;
		this.professor = professor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ArrayList<Double> getNotas() {
		return notas;
	}

	public void setNotas(ArrayList<Double> notas) {
		this.notas = notas;
	}

	public StatusMateria getStatus() {
		return status;
	}

	public void setStatus(StatusMateria status) {
		this.status = status;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
}
