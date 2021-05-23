package dominio;

import dominio.Tipo;

public class TipoParentesco extends Tipo {
	
	public TipoParentesco() {}
	
	
	public TipoParentesco(String descricao, Parentesco parentesco) {
		super(descricao, parentesco.toString());
	}

}

enum Parentesco{
	CONJUGUE,
	FILHO
}
