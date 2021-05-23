package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.Conexao;
import dominio.Aluno;
import dominio.EntidadeDominio;

public class AlunoDAO implements IDAO{

	@Override
	public void salvar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		Connection connection = null;
		PreparedStatement pst = null;			
		try {
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
		} catch (SQLException | ClassNotFoundException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<EntidadeDominio> listar() {
		ArrayList<EntidadeDominio> alunos = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pst = null;			
		try {
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
			return alunos;
		} catch (SQLException | ClassNotFoundException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();	
			return null;
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void selecionar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		Connection connection = null;
		PreparedStatement pst = null;			
		try {
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
		} catch (SQLException | ClassNotFoundException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();	
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void alterar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		Connection connection = null;
		PreparedStatement pst = null;			
		try {
			connection = Conexao.getConnectionPostgres();					
			String sql = "update tb_aluno set nome=?, fone=?, email=? where id_alu=?";
					
			pst = connection.prepareStatement(sql);
			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getFone());
			pst.setString(3, aluno.getEmail());
			pst.setInt(4, aluno.getId());
			ResultSet rs = pst.executeQuery();
			
		} catch (SQLException | ClassNotFoundException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();	
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deletar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		
		Connection connection = null;
		PreparedStatement pst = null;			
		try {
			connection = Conexao.getConnectionPostgres();					
			String sql = "delete from tb_aluno where id_alu=?";
					
			pst = connection.prepareStatement(sql);
			pst.setInt(1, aluno.getId());
			pst.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();	
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
