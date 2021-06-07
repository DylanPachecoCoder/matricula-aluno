package dominio;

public class Semestre {
	
	private int ano;
	private SemestreEnum semestreEnum;
	
	public Semestre(int ano, SemestreEnum semestreEnum) {
		this.ano = ano;
		this.semestreEnum = semestreEnum;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public SemestreEnum getSemestreEnum() {
		return semestreEnum;
	}

	public void setSemestreEnum(SemestreEnum semestreEnum) {
		this.semestreEnum = semestreEnum;
	}
}
