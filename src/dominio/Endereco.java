package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import util.Conexao;

public class Endereco {
	private int id;
	private String logradouro;
	private String numero;
	private String cep;
	private String complemento;
	private Cidade cidade;
	private TipoEndereco tpEndereco;
	
	public Endereco() {}
	
	public Endereco(String logradouro, String numero, String cep, 
			String complemento, Cidade cidade, TipoEndereco tpEndereco) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.cidade = cidade;
		this.tpEndereco = tpEndereco;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoEndereco getTpEndereco() {
		return tpEndereco;
	}

	public void setTpEndereco(TipoEndereco tpEndereco) {
		this.tpEndereco = tpEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public void salvar(Connection connection) {
		
//		PreparedStatement pst=null;
//		boolean ctrTransacao = false;
//		try {
//			
//			if(connection == null) {
//				connection = Conexao.getConnectionPostgres();	
//				ctrTransacao = true;
//			}						
//	
//			tpEndereco.salvar(connection, "tpendereco");			
//					
//			StringBuilder sql = new StringBuilder();
//			sql.append("INSERT INTO tb_endereco(cli_id, tpend_id, cidade, estado, ");
//			sql.append("logradouro, numero, cep) VALUES (?,?,?,?,?,?,?)");		
//					
//			pst = connection.prepareStatement(sql.toString());
//			pst.setInt(1, pessoa.getId()); 
//			pst.setInt(2, tpEndereco.getId());
//			pst.setString(3, cidade.getDescricao());	
//			pst.setString(4, cidade.getEstado().getDescricao());	
//			pst.setString(5, logradouro);
//			pst.setString(6, numero);
//			pst.setString(7, cep);
//			pst.executeUpdate();	
//			
//			ResultSet rs = pst.getGeneratedKeys();			
//			if(rs.next())
//				id = rs.getInt(1);		
//			
//			if(ctrTransacao) {
//				connection.commit();	
//			}				
//			
//		} catch (SQLException | ClassNotFoundException e) {
//			try {
//				connection.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();			
//		}finally{
//			try {
//				pst.close();
//				if(ctrTransacao) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}		

	}
	
	
}
