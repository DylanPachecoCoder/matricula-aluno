package dominio;

import java.util.ArrayList;
import java.util.List;

public abstract class Pessoa extends EntidadeDominio {

	protected String nome;
	protected String dataNascimento;
	protected Endereco endereco;
	protected List<Documento> documentos;

	public Pessoa() {}

	public Pessoa(String nome, String dataNascimento, Endereco endereco, List<Documento> documentos) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.documentos = documentos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public void addDocumento(Documento documento) {
		if (documentos == null) {
			documentos = new ArrayList<Documento>();
		}
		documentos.add(documento);
	}
}
