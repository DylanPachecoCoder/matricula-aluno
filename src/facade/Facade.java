package facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import dao.AlunoDAO;
import dao.IDAO;
import dominio.Aluno;
import dominio.EntidadeDominio;
import negocio.ComplementarDtCadastro;
import negocio.IStrategy;
import negocio.ValidadorCpf;
import negocio.ValidadorDadosObrigatoriosAluno;
import negocio.ValidadorExistencia;

public class Facade implements IFacade {
	private Map<String, IDAO> daos;
	private Map<String, List<IStrategy>> rns;
	
	public Facade() {
		definirDAOS();
		definirRNS();
	}
	
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
	public ArrayList<Aluno> buscarTodos(EntidadeDominio entidade) {
		ArrayList<Aluno> alunos = new ArrayList<>();
		ArrayList<EntidadeDominio> lista = dao.listar(entidade);

		for (int i = 0; i < lista.size(); i++) {
			Aluno aluno = (Aluno) lista.get(i);
			alunos.add(aluno);
		}
		return alunos;
	}
	
	private void definirRNS() {
		rns = new HashMap<String, List<IStrategy>>();

		ValidadorDadosObrigatoriosAluno vCiente = new ValidadorDadosObrigatoriosAluno();
		ValidadorCpf vCpf = new ValidadorCpf();
		ComplementarDtCadastro cDtCadastro = new ComplementarDtCadastro();
		ValidadorExistencia vExistencia = new ValidadorExistencia();

		List<IStrategy> rnsAluno = new ArrayList<IStrategy>();
		rnsAluno.add(vCiente);
		rnsAluno.add(vCpf);		
		rnsAluno.add(vExistencia);
		rnsAluno.add(cDtCadastro);
		rns.put(Aluno.class.getName(), rnsAluno);
		
		/*	List<IStrategy> rnsFornecedor = new ArrayList<IStrategy>();
		rnsFornecedor.add(vCpf);
		rnsFornecedor.add(cDtCadastro);*/
	//	rns.put(Fornecedor.class.getName(), rnsFornecedor);
	}

	private void definirDAOS() {
		daos = new HashMap<String, IDAO>();
		daos.put(Aluno.class.getName(), new AlunoDAO());		
		//daos.put(Produto.class.getName(), new ProdutoDAO());
		//daos.put(Fornecedor.class.getName(), new FornecedorDAO());
	}
}
