package dominio;

public class TipoDocumento extends Tipo{
	
	private DocumentoEnum documentoEnum;

	public TipoDocumento() {}
	
	public TipoDocumento(String descricao, DocumentoEnum documentoEnum) {
		super(descricao, documentoEnum.toString());
		this.documentoEnum = documentoEnum;
	}
	
	public DocumentoEnum getDocumentoEnum() {
		return documentoEnum;
	}

	public void setDocumentoEnum(DocumentoEnum documentoEnum) {
		this.documentoEnum = documentoEnum;
	}

}

enum DocumentoEnum{
	RG,
	CPF,
	PASSAPORTE,
	CNH
}