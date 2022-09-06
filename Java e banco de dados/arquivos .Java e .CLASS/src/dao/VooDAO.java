package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import factory.ConnectionFactory;
import model.Voo;

public class VooDAO {
	private Connection connection;
	
	public VooDAO(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void save(Voo voo) {
		//CREATE
		String sql = "INSERT INTO voo VALUES(null, ?, ?,)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, voo.getCompanhia());
			stmt.setDouble(2, voo.getPrecoVoo());
			
			stmt.execute();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeById(int id) {
		//DELETE
		String sql = "DELETE FROM voo WHERE codVoo=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			
			stmt.execute();
			stmt.close();
		}catch (SQLException e) {
			 //ISSO TUDO PARA Q NÃO OCORRA O MESMO PROBLEMA COM O CLIENTE
			 System.out.println("[ERROR] ocorreu um erro ao tentar remover o Voo: " +e.getMessage());
			 System.out.println("--- TEREMOS QUE REMOVER A ASSOCIAÇÃO A UM(S) DETERMINADO CLIENTE ---");
			 System.out.println("removendo . . .");
			 String delete =  "DELETE FROM escolher WHERE fk_codVoo=?";
			 try {
				PreparedStatement stmt2 = connection.prepareStatement(delete);
				stmt2.execute();
				stmt2.close();
				System.out.println("--- RELAÇÃO RETIRADA, TENTE NOVAMENTE ---");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void update(Voo voo, int id) {
		//UPDATE
		String sql = "UPDATE voo SET companhiaV= ?, preco= ? WHERE codVoo=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, voo.getCompanhia());
			stmt.setDouble(2, voo.getPrecoVoo());
			stmt.setInt(3, id);
			
			stmt.execute();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet getTodosVoo() throws SQLException{
		//READ (TUDO)
		String sql = "SELECT * FROM voo";
		Statement stmt = null;
		try {	
		stmt = connection.createStatement();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt.executeQuery(sql);
	}
	
	public ResultSet getIdVoo(int id) throws SQLException{
		//READ (UNICO)
		String sql = "SELECT * FROM voo WHERE codVoo="+id;
		ResultSet resultado = null;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			resultado = stmt.executeQuery(sql);
			
			if(resultado.next()) {
				 System.out.println("ID -- > "+resultado.getInt(1));
				 System.out.println("COMPANHIA -- > "+resultado.getString(2));
				 System.out.println("PREÇO -- > "+resultado.getDouble(3));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}	
