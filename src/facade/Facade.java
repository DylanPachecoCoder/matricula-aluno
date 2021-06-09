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
	public Object salvar(EntidadeDominio entidade) {
		String msg = executarRegras(entidade, "salvar");
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
	public Object deletar(EntidadeDominio entidade) {
		dao.deletar(entidade);
		return null;
	}

	@Override
	public Object atualizar(EntidadeDominio entidade) {
//		dao.alterar(entidade);
		
		String msg = executarRegras(entidade, "atualizar");
		String nmClasse = entidade.getClass().getName();
		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			dao.alterar(entidade);
		} else {
			return msg;
		}
		return null;
	}

	@Override
	public Object buscar(EntidadeDominio entidade) {
		dao.selecionar(entidade);
		return entidade;
	}

	@Override
	public Object buscarTodos(EntidadeDominio entidade) {
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

		List<IStrategy> rnsSalvaAluno = new ArrayList<IStrategy>();
		rnsSalvaAluno.add(vAluno);
		rnsSalvaAluno.add(vCpf);		
		rnsSalvaAluno.add(vExistencia);
		rnsSalvaAluno.add(cDtCadastro);
		
		List<IStrategy> rnsAtualizaAluno = new ArrayList<IStrategy>();
		rnsAtualizaAluno.add(vAluno);
		rnsAtualizaAluno.add(vCpf);
		rnsAtualizaAluno.add(vExistencia);
		rnsAtualizaAluno.add(cDtCadastro);
		rns.put("salvar", rnsSalvaAluno);
		rns.put("atualizar", rnsAtualizaAluno);
	}

	private void definirDAOS() {
		daos = new HashMap<String, IDAO>();
		daos.put(Aluno.class.getName(), new AlunoDAO());		
	}
	
	private String executarRegras(EntidadeDominio entidade, String chaveValidacao) {
		StringBuilder msg = new StringBuilder();

		List<IStrategy> regras = rns.get(chaveValidacao);

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
