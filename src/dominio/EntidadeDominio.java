package dominio;

public class EntidadeDominio {
	
	protected int id;
	
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
}
