package dominio;

import java.util.ArrayList;
import java.util.List;

public abstract class Pessoa extends EntidadeDominio {

	protected List<Documento> documentos;

	public Pessoa() {
	}

	public Pessoa(List<Documento> documentos) {
		this.documentos = documentos;
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
	
	public abstract void salvar();

}
