package facade;

import java.util.ArrayList;

import dominio.Aluno;
import dominio.EntidadeDominio;

public interface IFacade {
	
	public void salvar(EntidadeDominio entidade);
	
	public void deletar(EntidadeDominio entidade);
	
	public void atualizar(EntidadeDominio entidade);
	
	public void buscar(EntidadeDominio entidade);
	
	public ArrayList<Aluno> buscarTodos();
}
