package dominio;

public class Semestre extends EntidadeDominio {
	
	private String ano;
	private String semestre;
	
	public Semestre() {	}
	
	public Semestre(String ano, String semestre) {
		this.ano = ano;
		this.semestre = semestre;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getSemestreEnum() {
		return semestre;
	}

	public void setSemestreEnum(String semestre) {
		this.semestre = semestre;
	}
}
