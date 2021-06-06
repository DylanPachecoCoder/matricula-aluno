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
	
	IDAO dao = new AlunoDAO();

	@Override
	public String salvar(EntidadeDominio entidade) {
		String msg = executarRegras(entidade);
		String nmClasse = entidade.getClass().getName();
		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			dao.salvar(entidade);
		} else {
			return msg;
		}
		return null;
	}

	@Override
	public String deletar(EntidadeDominio entidade) {
		dao.deletar(entidade);
		return null;
	}

	@Override
	public String atualizar(EntidadeDominio entidade) {
		dao.alterar(entidade);
		return null;
	}

	@Override
	public String buscar(EntidadeDominio entidade) {
		dao.selecionar(entidade);
		return null;
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

		ValidadorDadosObrigatoriosAluno vAluno = new ValidadorDadosObrigatoriosAluno();
		ValidadorCpf vCpf = new ValidadorCpf();
		ComplementarDtCadastro cDtCadastro = new ComplementarDtCadastro();
		ValidadorExistencia vExistencia = new ValidadorExistencia();

		List<IStrategy> rnsAluno = new ArrayList<IStrategy>();
		rnsAluno.add(vAluno);
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
	
	private String executarRegras(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();

		List<IStrategy> regras = rns.get(nmClasse);

		if (regras != null) {
			for (IStrategy s : regras) {
				String m = s.processar(entidade);

				if (m != null) {
					msg.append(m);
					msg.append("\n");
				}
			}
		}

		if (msg.length() > 0)
			return msg.toString();
		else
			return null;
	}
}
