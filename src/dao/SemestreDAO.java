package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexao;
import util.IExecutaQuery;
import dominio.Aluno;
import dominio.EntidadeDominio;
import dominio.Semestre;

public class SemestreDAO extends AbstractDAO{

	@Override
	public void salvar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		Semestre semestre = aluno.getSemestreInicial();
		executa(new IExecutaQuery() {

			@Override
			public Boolean executa() throws ClassNotFoundException, SQLException {
				connection = Conexao.getConnectionPostgres();	
				connection.setAutoCommit(false);				
						
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO semestre(ano_semestre, sem_semestre, semestre_aluno)");
				sql.append("VALUES (?, ?, ?)");		
						
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, semestre.getAno());
				pst.setString(2, semestre.getSemestreEnum());
				pst.setInt(3, aluno.getId());
				pst.executeUpdate();
				
				ResultSet rs = pst.getGeneratedKeys();			
				if(rs.next())
					semestre.setId(rs.getInt(1));
				
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
				String sql = "select * from semestre where semestre_aluno = ?";
						
				pst = connection.prepareStatement(sql);
				pst.setInt(1, aluno.getId());
				ResultSet rs = pst.executeQuery();
				Semestre semestre = new Semestre();
				
				while(rs.next()) {
					semestre.setId(Integer.parseInt(rs.getString(1)));
					semestre.setAno(rs.getString(2));
					semestre.setSemestreEnum(rs.getString(3));
					aluno.setSemestreInicial(semestre);
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
				String sql = "delete from semestre where semestre_aluno=?";
						
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
