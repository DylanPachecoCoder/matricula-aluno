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
				connection = Conexao.getConnectionPostgres();	
				connection.setAutoCommit(false);				
						
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO tb_aluno(nome, fone, email)");
				sql.append("VALUES (?, ?, ?)");		
						
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, aluno.getNome());
				pst.setString(2, aluno.getFone());
				pst.setString(3, aluno.getEmail());
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
				String sql = "select * from tb_aluno where id_alu = ?";
						
				pst = connection.prepareStatement(sql);
				pst.setInt(1, aluno.getId());
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					aluno.setId(Integer.parseInt(rs.getString(1)));
					aluno.setNome(rs.getString(2));
					aluno.setFone(rs.getString(3));
					aluno.setEmail(rs.getString(4));
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
				String sql = "update tb_aluno set nome=?, fone=?, email=? where id_alu=?";
						
				pst = connection.prepareStatement(sql);
				pst.setString(1, aluno.getNome());
				pst.setString(2, aluno.getFone());
				pst.setString(3, aluno.getEmail());
				pst.setInt(4, aluno.getId());
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
				String sql = "delete from tb_aluno where id_alu=?";
						
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
				String sql = "select * from tb_aluno order by nome";
						
				pst = connection.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				
				while(rs.next()) {
					int id = Integer.parseInt(rs.getString(1));
					String nome = rs.getString(2);
					String fone = rs.getString(3);
					String email = rs.getString(4);
					
					alunos.add(new Aluno(id, nome, fone, email));	
				}
			}
		});
		return alunos;
	}
}
