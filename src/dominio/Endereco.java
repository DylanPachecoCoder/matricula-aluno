package dominio;

public class Endereco {
	private int id;
	private String logradouro;
	private String numero;
	private String cep;
	private String complemento;
	private Cidade cidade;
	private TipoEndereco tpEndereco;
	
	public Endereco() {}
	
	public Endereco(String logradouro, String numero, String cep, 
			String complemento, Cidade cidade, TipoEndereco tpEndereco) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.cidade = cidade;
		this.tpEndereco = tpEndereco;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoEndereco getTpEndereco() {
		return tpEndereco;
	}

	public void setTpEndereco(TipoEndereco tpEndereco) {
		this.tpEndereco = tpEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}	
}
