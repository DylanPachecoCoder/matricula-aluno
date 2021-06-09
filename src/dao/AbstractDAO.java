package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.IExecutaQuery;

public abstract class AbstractDAO implements IDAO{
	
	public static Connection connection;
	public static PreparedStatement pst;

	public Boolean executa(IExecutaQuery executaQuery) {
		try {
			return executaQuery.executa();
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
		return null;
	}
}
