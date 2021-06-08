package negocio;

import dominio.EntidadeDominio;

public class ValidadorExistencia implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
//		IDAO dao = new AlunoDAO();
//		List<EntidadeDominio> alunos = dao.listar(entidade);
//		if(alunos != null && alunos.size()>0) {
//			return "Cliente já cadastrado!";
//		}
		return null;
	}

}
