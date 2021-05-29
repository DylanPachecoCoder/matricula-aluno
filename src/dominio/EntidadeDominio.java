package dominio;

import java.util.Date;

public class EntidadeDominio {
	
	protected int id;
	private Date dtCadastro;
	
	public EntidadeDominio() {}
	
	public EntidadeDominio(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getDtCadastro() {
		return dtCadastro;
	}
	
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}
