package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexao;
import util.IExecutaQuery;
import dominio.Aluno;
import dominio.Endereco;
import dominio.EntidadeDominio;

public class EnderecoDAO extends AbstractDAO{

	@Override
	public void salvar(EntidadeDominio entidadeDominio) {
		Aluno aluno = (Aluno)entidadeDominio;
		Endereco endereco = aluno.getEndereco();
		executa(new IExecutaQuery() {

			@Override
			public void executa() throws ClassNotFoundException, SQLException {
				connection = Conexao.getConnectionPostgres();	
				connection.setAutoCommit(false);				
						
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO endereco(logradouro, cep, numero, complemento, tp_endereco, cidade, estado, endereco_aluno)");
				sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");		
						
				pst = connection.prepareStatement(sql.toString(),
						Statement.RETURN_GENERATED_KEYS);
				pst.setString(1, endereco.getLogradouro());
				pst.setString(2, endereco.getCep());
				pst.setInt(3, endereco.getNumero());
				pst.setString(4, endereco.getComplemento());
				pst.setString(5, endereco.getTpEndereco().toString());
				pst.setString(6, endereco.getCidade().getDescricao());
				pst.setString(7, endereco.getCidade().getEstado().getDescricao());
				pst.setInt(8, aluno.getId());
				pst.executeUpdate();
				
				ResultSet rs = pst.getGeneratedKeys();			
				if(rs.next())
					endereco.setId(rs.getInt(1));
				
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
		Aluno aluno = (Aluno) entidadeDominio;
		Endereco endereco = aluno.getEndereco();
		
		executa(new IExecutaQuery(){

			@Override
			public void executa() throws ClassNotFoundException, SQLException {
				connection = Conexao.getConnectionPostgres();
				
				StringBuilder sql = new StringBuilder();
				sql
				.append("update endereco set ")
				.append("logradouro=?, ")
				.append("cep=?, ")
				.append("numero=?, ")
				.append("complemento=?, ")
				.append("cidade=?, ")
				.append("estado=? ")
				.append("where endereco_aluno=?");
						
				pst = connection.prepareStatement(sql.toString());
				pst.setString(1, endereco.getLogradouro());
				pst.setString(2, endereco.getCep());
				pst.setInt(3, endereco.getNumero());
				pst.setString(4, endereco.getComplemento());
				pst.setString(5, endereco.getCidade().getDescricao());
				pst.setString(6, endereco.getCidade().getEstado().getDescricao());
				pst.setInt(7, aluno.getId());
				pst.executeQuery();
				
				connection.commit();	
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
				String sql = "delete from endereco where endereco_aluno=?";
						
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
