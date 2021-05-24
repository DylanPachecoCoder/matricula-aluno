package facade;

import java.util.ArrayList;

import dao.AlunoDAO;
import dominio.Aluno;
import dominio.EntidadeDominio;

public class Facade implements IFacade {
	
	AlunoDAO dao = new AlunoDAO();

	@Override
	public void salvar(EntidadeDominio entidade) {
		Aluno aluno = (Aluno) entidade;
		dao.salvar(aluno);
	}

	@Override
	public void deletar(EntidadeDominio entidade) {
		Aluno aluno = (Aluno) entidade;
		dao.deletar(aluno);
	}

	@Override
	public void atualizar(EntidadeDominio entidade) {
		Aluno aluno = (Aluno) entidade;
		dao.alterar(aluno);
	}

	@Override
	public void buscar(EntidadeDominio entidade) {
		Aluno aluno = (Aluno) entidade;
		dao.selecionar(aluno);
	}

	@Override
	public ArrayList<Aluno> buscarTodos() {
		ArrayList<Aluno> alunos = new ArrayList<>();
		ArrayList<EntidadeDominio> lista = dao.listar();

		for (int i = 0; i < lista.size(); i++) {
			Aluno aluno = (Aluno) lista.get(i);
			alunos.add(aluno);
		}
		return alunos;
	}

}
