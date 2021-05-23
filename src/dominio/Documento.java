package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import util.Conexao;

public class Documento {
	private int id;
	private String codigo;
	private Date validade;
	private TipoDocumento tpDocumento;
	private Pessoa pessoa;
	
	public Documento() {}
	
	public Documento(String codigo, Date validade, TipoDocumento tpDocumento) {
		this.codigo = codigo;
		this.validade = validade;
		this.tpDocumento = tpDocumento;
	}
	
	public Boolean validaDocumento() {
		if(tpDocumento.getDocumentoEnum() == DocumentoEnum.CPF) {
			// valida se existe o cpf no banco
			return this.codigo.length() == 11;
		}
		return true;
	}
	
	public TipoDocumento getTpDocumento() {
		return tpDocumento;
	}
	public void setTpDocumento(TipoDocumento tpDocumento) {
		this.tpDocumento = tpDocumento;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	
	public void salvar(Connection connection) {
		
		PreparedStatement pst=null;
		boolean ctrTransacao = false;
		try {
			
			if(connection == null) {
				connection = Conexao.getConnectionPostgres();	
				ctrTransacao = true;
			}						
	
			tpDocumento.salvar(connection, "tpdocumento");			
					
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_documento(cli_id, tpdoc_id, codigo, ");
			sql.append("validade) VALUES (?,?,?,?)");		
					
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, pessoa.getId()); 
			pst.setInt(2, tpDocumento.getId());
			pst.setString(3, codigo);		
			Timestamp time = new Timestamp(validade.getTime());
			pst.setTimestamp(4, time);
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	
}
