package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexao;
import util.IExecutaQuery;
import dominio.Aluno;
import dominio.Cidade;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Estado;

public class AlunoDAO extends AbstractDAO{

	@Override
	public void salvar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		executa(new IExecutaQuery() {

			@Override
			public Boolean executa() throws ClassNotFoundException, SQLException {	
				connection = Conexao.getConnectionPostgres();	
				connection.setAutoCommit(false);	
						
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO aluno(nome_aluno, dt_nasc_aluno, rg_aluno, cpf_aluno)");
				sql.append("VALUES (?, ?, ?, ?)");		
						
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, aluno.getNome());
				pst.setString(2, aluno.getDataNascimento());
				pst.setString(3, aluno.getRg());
				pst.setString(4, aluno.getCpf());
				pst.executeUpdate();
				
				ResultSet rs = pst.getGeneratedKeys();			
				if(rs.next())
					aluno.setId(rs.getInt(1));
				
				connection.commit();	
				
				
				IDAO enderecoDAO = new EnderecoDAO();
				enderecoDAO.salvar(aluno);
				
				IDAO semestreDAO = new SemestreDAO();
				semestreDAO.salvar(aluno);
				
				IDAO cursoDAO = new CursoDAO();
				cursoDAO.salvar(aluno);
				return true;
			}
			
		});
	}
	
	@Override
	public void selecionar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		executa(new IExecutaQuery(){

			@Override
			public Boolean executa() throws ClassNotFoundException, SQLException {
				connection = Conexao.getConnectionPostgres();					
				StringBuilder sql = new StringBuilder();
				sql.append("Select * from aluno ")
				.append("INNER JOIN endereco ON endereco.endereco_aluno = aluno.id_aluno ")
				.append("LEFT JOIN curso ON curso.curso_aluno = aluno.id_aluno ")
				.append("LEFT JOIN semestre ON semestre.semestre_aluno = aluno.id_aluno ")
				.append("LEFT JOIN materia ON materia.materia_curso = curso.id_curso ")
				.append("LEFT JOIN professor ON professor.id_professor = materia.materia_professor ")
				.append("where aluno.id_aluno = ?");
				
						
				pst = connection.prepareStatement(sql.toString());
				pst.setInt(1, aluno.getId());
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					aluno.setId(Integer.parseInt(rs.getString("id_aluno")));
					aluno.setNome(rs.getString("nome_aluno"));
					aluno.setDataNascimento(rs.getString("dt_nasc_aluno"));
					aluno.setRg(rs.getString("rg_aluno"));
					aluno.setCpf(rs.getString("cpf_aluno"));
					
					Estado estado = new Estado(rs.getString("estado"));
					Cidade cidade = new Cidade(rs.getString("cidade"), estado);
					Endereco endereco = new Endereco();
					endereco.setLogradouro(rs.getString("logradouro"));
					endereco.setCep(rs.getString("cep"));
					endereco.setNumero(rs.getString("numero"));
					endereco.setComplemento(rs.getString("complemento"));
					endereco.setCidade(cidade);
					endereco.setTpEndereco(rs.getString("tp_endereco"));
					aluno.setEndereco(endereco);
				}
				return true;
			}
		});
	}
	
	@Override
	public void alterar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		executa(new IExecutaQuery(){

			@Override
			public Boolean executa() throws ClassNotFoundException, SQLException {
				
				IDAO enderecoDAO = new EnderecoDAO();
				enderecoDAO.alterar(aluno);
				
				connection = Conexao.getConnectionPostgres();					
				
				StringBuilder sql = new StringBuilder();
				sql
				.append("update aluno set ")
				.append("nome_aluno=?, ")
				.append("cpf_aluno=?, ")
				.append("rg_aluno=?, ")
				.append("dt_nasc_aluno=? ")
				.append(" where id_aluno=?");
						
				pst = connection.prepareStatement(sql.toString());
				pst.setString(1, aluno.getNome());
				pst.setString(2, aluno.getCpf());
				pst.setString(3, aluno.getRg());
				pst.setString(4, aluno.getDataNascimento());
				pst.setInt(5, aluno.getId());
				pst.executeQuery();
				return true;
			}
		});
	}
	
	@Override
	public void deletar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		executa(new IExecutaQuery(){

			@Override
			public Boolean executa() throws ClassNotFoundException, SQLException {
				
				IDAO enderecoDAO = new EnderecoDAO();
				enderecoDAO.deletar(aluno);
				
				IDAO semestreDAO = new SemestreDAO();
				semestreDAO.deletar(aluno);
				
				IDAO cursoDAO = new CursoDAO();
				cursoDAO.deletar(aluno);
				
				connection = Conexao.getConnectionPostgres();					
				String sql = "delete from aluno where id_aluno=?";
				pst = connection.prepareStatement(sql);
				pst.setInt(1, aluno.getId());
				pst.executeQuery();
				connection.commit();
				return true;
			}
		});		
	}

	@Override
	public ArrayList<EntidadeDominio> listar(EntidadeDominio entidadeDominio) {
		ArrayList<EntidadeDominio> alunos = new ArrayList<>();
		
		executa(new IExecutaQuery(){

			@Override
			public Boolean executa() throws ClassNotFoundException, SQLException {
				connection = Conexao.getConnectionPostgres();					
				String sql = "select * from aluno order by nome_aluno";
						
				pst = connection.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					int id = Integer.parseInt(rs.getString(1));
					String nome = rs.getString(2);
					String dataNascimento = rs.getString(3);
					String rg = rs.getString(4);
					String cpf = rs.getString(5);
					Aluno aluno = new Aluno();
					aluno.setNome(nome);
					aluno.setId(id);
					aluno.setDataNascimento(dataNascimento);
					aluno.setCpf(cpf);
					aluno.setRg(rg);
					alunos.add(aluno);
					
					IDAO semestreDAO = new SemestreDAO();
					semestreDAO.selecionar(aluno);
					
					IDAO cursoDAO = new CursoDAO();
					cursoDAO.selecionar(aluno);
				}
				return true;
			}
			
		});
		return alunos;
	}
	
	
	public Boolean consultarPorCpf(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		return executa(new IExecutaQuery(){

			@Override
			public Boolean executa() throws ClassNotFoundException, SQLException {
				connection = Conexao.getConnectionPostgres();					
				StringBuilder sql = new StringBuilder();
				sql.append("Select * from aluno ")
				.append("where aluno.cpf_aluno = ?");
				
						
				pst = connection.prepareStatement(sql.toString());
				pst.setString(1, aluno.getCpf());
				ResultSet rs = pst.executeQuery();
		
				return rs.next();		
			}
		});
	}
}
