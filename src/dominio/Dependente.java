package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Conexao;

public class Dependente extends Pessoa {
	
	private TipoParentesco tipoParentesco;
	private Pessoa pessoa;

	public Dependente(TipoParentesco tipoParentesco) {
		super();
		this.tipoParentesco = tipoParentesco;
	}

	public TipoParentesco getTipoParentesco() {
		return tipoParentesco;
	}

	public void setTipoParentesco(TipoParentesco tipoParentesco) {
		this.tipoParentesco = tipoParentesco;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void salvar(Connection connection) {
		PreparedStatement pst=null;
		boolean ctrTransacao = false;
		try {
			
			if(connection == null) {
				connection = Conexao.getConnectionPostgres();	
				ctrTransacao = true;
			}						
	
			tipoParentesco.salvar(connection, "tpparentesco");
				
					
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_dependente(id_depen, cli_id, tpparent_id)  VALUES (?,?,?)");
					
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, this.getId()); 
			pst.setInt(2, pessoa.getId());
			pst.setInt(3, tipoParentesco.getId());

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
//				pst.close();
				if(ctrTransacao) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		

	}
}
