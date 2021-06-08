package dominio;

public class Semestre extends EntidadeDominio {
	
	private int ano;
	private String semestre;
	
	public Semestre(int ano, String semestre) {
		this.ano = ano;
		this.semestre = semestre;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getSemestreEnum() {
		return semestre;
	}

	public void setSemestreEnum(String semestre) {
		this.semestre = semestre;
	}
}
