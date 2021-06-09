package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexao;
import util.IExecutaQuery;
import dominio.Aluno;
import dominio.Cidade;
import dominio.Curso;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Estado;
import dominio.Semestre;
import dominio.TipoEndereco;

public class AlunoDAO extends AbstractDAO{

	@Override
	public void salvar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		executa(new IExecutaQuery() {

			@Override
			public void executa() throws ClassNotFoundException, SQLException {	
				IDAO cursoDAO = new CursoDAO();
				cursoDAO.salvar(aluno.getCurso());
				
				connection = Conexao.getConnectionPostgres();	
				connection.setAutoCommit(false);	
						
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO aluno(nome_aluno, dt_nasc_aluno, rg_aluno, cpf_aluno, aluno_curso)");
				sql.append("VALUES (?, ?, ?, ?, ?)");		
						
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, aluno.getNome());
				pst.setString(2, aluno.getDataNascimento());
				pst.setString(3, aluno.getRg());
				pst.setString(4, aluno.getCpf());
				pst.setInt(5, aluno.getCurso().getId());
				pst.executeUpdate();
				
				ResultSet rs = pst.getGeneratedKeys();			
				if(rs.next())
					aluno.setId(rs.getInt(1));
				
				connection.commit();	
				
				
				IDAO enderecoDAO = new EnderecoDAO();
				enderecoDAO.salvar(aluno);
				
				IDAO semestreDAO = new SemestreDAO();
				semestreDAO.salvar(aluno);
			}
			
		});
	}
	
	@Override
	public void selecionar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		executa(new IExecutaQuery(){

			@Override
			public void executa() throws ClassNotFoundException, SQLException {
				connection = Conexao.getConnectionPostgres();					
				StringBuilder sql = new StringBuilder();
				sql.append("Select * from aluno ")
				.append("INNER JOIN endereco ON endereco.endereco_aluno = aluno.id_aluno ")
				.append("LEFT JOIN curso ON curso.id_curso = aluno.aluno_curso ")
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
					endereco.setNumero(Integer.valueOf(rs.getString("numero")));
					endereco.setComplemento(rs.getString("complemento"));
					endereco.setCidade(cidade);
					endereco.setTpEndereco(rs.getString("tp_endereco"));
					aluno.setEndereco(endereco);
				}
			}
		});
	}
	
	@Override
	public void alterar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		executa(new IExecutaQuery(){

			@Override
			public void executa() throws ClassNotFoundException, SQLException {
				
				IDAO enderecoDAO = new EnderecoDAO();
				enderecoDAO.alterar(aluno);
				
				connection = Conexao.getConnectionPostgres();					
				
				StringBuilder sql = new StringBuilder();
				sql
				.append("update aluno set ")
				.append("nome_aluno=?, ")
				.append("dt_nasc_aluno=? ")
				.append(" where id_aluno=?");
						
				pst = connection.prepareStatement(sql.toString());
				pst.setString(1, aluno.getNome());
				pst.setString(2, aluno.getDataNascimento());
				pst.setInt(3, aluno.getId());
				pst.executeQuery();
			}
		});
	}
	
	@Override
	public void deletar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		executa(new IExecutaQuery(){

			@Override
			public void executa() throws ClassNotFoundException, SQLException {
				
				IDAO enderecoDAO = new EnderecoDAO();
				enderecoDAO.deletar(aluno);
				
				IDAO semestreDAO = new SemestreDAO();
				semestreDAO.deletar(aluno);
				
				connection = Conexao.getConnectionPostgres();					
				String sql = "delete from aluno where id_aluno=?";
				pst = connection.prepareStatement(sql);
				pst.setInt(1, aluno.getId());
				pst.executeQuery();
			}
		});		
	}

	@Override
	public ArrayList<EntidadeDominio> listar(EntidadeDominio entidadeDominio) {
		ArrayList<EntidadeDominio> alunos = new ArrayList<>();
		
		executa(new IExecutaQuery(){

			@Override
			public void executa() throws ClassNotFoundException, SQLException {
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
				}
			}
		});
		return alunos;
	}
}
