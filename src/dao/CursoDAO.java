package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexao;
import util.IExecutaQuery;
import dominio.Aluno;
import dominio.Curso;
import dominio.EntidadeDominio;

public class CursoDAO extends AbstractDAO{

	@Override
	public void salvar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno) entidadeDominio;
		Curso curso = aluno.getCurso();
		executa(new IExecutaQuery() {

			@Override
			public Boolean executa() throws ClassNotFoundException, SQLException {
				connection = Conexao.getConnectionPostgres();	
				connection.setAutoCommit(false);				
						
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO curso(descricao_curso, periodo_curso, curso_aluno)");
				sql.append("VALUES (?, ?, ?)");		
						
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, curso.getDescricao());
				pst.setString(2, curso.getPeriodo().toString());
				pst.setInt(3, aluno.getId());
				pst.executeUpdate();
				
				ResultSet rs = pst.getGeneratedKeys();			
				if(rs.next())
					curso.setId(rs.getInt(1));
				
				connection.commit();
				return null;	
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
				String sql = "select * from curso where curso_aluno = ?";
						
				pst = connection.prepareStatement(sql);
				pst.setInt(1, aluno.getId());
				ResultSet rs = pst.executeQuery();
				Curso curso = new Curso();
				while(rs.next()) {
					curso.setId(Integer.parseInt(rs.getString(1)));
					curso.setDescricao(rs.getString(2));
					curso.setPeriodo(rs.getString(3));
					aluno.setCurso(curso);
				}
				return null;
			}
			
		});
	}
	
	@Override
	public void alterar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		executa(new IExecutaQuery(){

			@Override
			public Boolean executa() throws ClassNotFoundException, SQLException {
				connection = Conexao.getConnectionPostgres();					
				String sql = "update aluno set nome_aluno=? where id_aluno=?";
						
				pst = connection.prepareStatement(sql);
				pst.setString(1, aluno.getNome());
//				pst.setString(2, aluno.getDataNascimento());
//				pst.setString(3, aluno.getEmail());
				pst.setInt(2, aluno.getId());
				pst.executeQuery();
				return null;
			}
		});
	}
	
	@Override
	public void deletar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		executa(new IExecutaQuery(){

			@Override
			public Boolean executa() throws ClassNotFoundException, SQLException {
				connection = Conexao.getConnectionPostgres();					
				String sql = "delete from curso where curso_aluno=?";
						
				pst = connection.prepareStatement(sql);
				pst.setInt(1, aluno.getId());
				pst.executeQuery();
				connection.commit();
				return null;
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
				}
				return null;
			}
		});
		return alunos;
	}
}
