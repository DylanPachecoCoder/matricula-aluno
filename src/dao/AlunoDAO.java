package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexao;
import util.IExecutaQuery;
import dominio.Aluno;
import dominio.EntidadeDominio;

public class AlunoDAO extends AbstractDAO{

	@Override
	public void salvar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		executa(new IExecutaQuery() {

			@Override
			public void executa() throws ClassNotFoundException, SQLException {	
				IDAO cursoDAO = new CursoDAO();
				cursoDAO.salvar(aluno.getCurso());
				
				IDAO semestreDAO = new SemestreDAO();
				semestreDAO.salvar(aluno.getSemestreInicial());
				
				IDAO enderecoDAO = new EnderecoDAO();
				enderecoDAO.salvar(aluno.getEndereco());
				
				connection = Conexao.getConnectionPostgres();	
				connection.setAutoCommit(false);	
						
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO aluno(nome_aluno, dt_nasc_aluno, rg_aluno, cpf_aluno, aluno_curso, aluno_semestre, endereco_aluno)");
				sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");		
						
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, aluno.getNome());
				pst.setString(2, aluno.getDataNascimento());
				pst.setString(3, aluno.getRg());
				pst.setString(4, aluno.getCpf());
				pst.setInt(5, aluno.getCurso().getId());
				pst.setInt(6, aluno.getSemestreInicial().getId());
				pst.setInt(7, aluno.getEndereco().getId());
				pst.executeUpdate();
				
				ResultSet rs = pst.getGeneratedKeys();			
				if(rs.next())
					aluno.setId(rs.getInt(1));
				
				connection.commit();	
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
				String sql = "select * from aluno where id_aluno = ?";
						
				pst = connection.prepareStatement(sql);
				pst.setInt(1, aluno.getId());
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					aluno.setId(Integer.parseInt(rs.getString(1)));
					aluno.setNome(rs.getString(2));
					aluno.setDataNascimento(rs.getString(3));
//					aluno.setFone(rs.getString(3));
//					aluno.setEmail(rs.getString(4));
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
				connection = Conexao.getConnectionPostgres();					
				String sql = "update aluno set nome_aluno=? where id_aluno=?";
						
				pst = connection.prepareStatement(sql);
				pst.setString(1, aluno.getNome());
//				pst.setString(2, aluno.getDataNascimento());
//				pst.setString(3, aluno.getEmail());
				pst.setInt(2, aluno.getId());
				pst.executeQuery();
			}
		});
	}
	
	@Override
	public void deletar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		System.out.println(aluno.getId());
		
		executa(new IExecutaQuery(){

			@Override
			public void executa() throws ClassNotFoundException, SQLException {
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
