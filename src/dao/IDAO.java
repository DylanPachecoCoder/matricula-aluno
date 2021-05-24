package dao;

import java.util.ArrayList;

import dominio.EntidadeDominio;

public interface IDAO {
	
	public void salvar(EntidadeDominio entidadeDominio);
	
	public ArrayList<EntidadeDominio> listar();
	
	public void selecionar(EntidadeDominio entidadeDominio);
	
	public void alterar(EntidadeDominio entidadeDominio);
	
	public void deletar(EntidadeDominio entidadeDominio);
}
