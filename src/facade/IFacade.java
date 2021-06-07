package facade;

import dominio.EntidadeDominio;

public interface IFacade {
	
	public Object salvar(EntidadeDominio entidade);
	
	public Object deletar(EntidadeDominio entidade);
	
	public Object atualizar(EntidadeDominio entidade);
	
	public Object buscar(EntidadeDominio entidade);
	
	public Object buscarTodos(EntidadeDominio entidade);
}
