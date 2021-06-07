package dominio;

public class Estado {
	
	private String descricao;
	
	public Estado() {}
	
	public Estado(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
