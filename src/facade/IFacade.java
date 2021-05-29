package facade;

import java.util.ArrayList;

import dominio.Aluno;
import dominio.EntidadeDominio;

public interface IFacade {
	
	public String salvar(EntidadeDominio entidade);
	
	public String deletar(EntidadeDominio entidade);
	
	public String atualizar(EntidadeDominio entidade);
	
	public String buscar(EntidadeDominio entidade);
	
	public ArrayList<Aluno> buscarTodos(EntidadeDominio entidade);
}
