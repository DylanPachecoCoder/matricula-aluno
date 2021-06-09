package negocio;

import dao.AlunoDAO;
import dominio.Aluno;
import dominio.EntidadeDominio;

public class ValidadorExistencia implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		AlunoDAO dao = new AlunoDAO();
		
		Boolean usuarioExistente = dao.consultarPorCpf(entidade);
		
		if(usuarioExistente) {
			return "Cliente já cadastrado<br>";
		}
		
		return null;
	}

}
