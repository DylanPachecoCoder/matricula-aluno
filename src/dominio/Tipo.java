package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.Conexao;

public abstract class Tipo {
	protected int id;
	protected String nome;
	protected String descricao;

		
	public Tipo() {	}
	
	protected Tipo(String descricao, String nome) {
		this.descricao = descricao;
		this.nome = nome;		
	}	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void salvar(Connection connection, String nmClass) {		
		PreparedStatement pst=null;		
		boolean ctrTransacao = false;
		try {
//			String nmClass = this.getClass().getSimpleName().toLowerCase();
			if(connection == null) {
				connection = Conexao.getConnectionPostgres();	
				ctrTransacao = true;
			}			
			connection.setAutoCommit(false);						
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO ");
			sql.append("tb_");
			sql.append(nmClass);
			sql.append("(nome, descricao) ");
			sql.append("VALUES (?,?)");		
					
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, nome);
			pst.setString(2, descricao);
			
			pst.executeUpdate();	
			
			ResultSet rs = pst.getGeneratedKeys();			
			if(rs.next())
				id = rs.getInt(1);		
			
			if(ctrTransacao) {
				connection.commit();	
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
				if(ctrTransacao) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
}
