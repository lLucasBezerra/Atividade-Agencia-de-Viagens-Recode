package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import factory.ConnectionFactory;
import model.Promocoes;

public class PromocoesDAO {
	private Connection connection;
	
	public PromocoesDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void create(Promocoes promocoes) {
		//CREATE
		String sql = "INSERT INTO promocoes VALUES(null,?,?)";
		 try {
			 PreparedStatement stmt = connection.prepareStatement(sql);
			 stmt.setInt(1, promocoes.getDesconto());
			 stmt.setString(2, promocoes.getNomePromo());
			 
			 stmt.execute();
			 stmt.close();
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
	}
	
	public void removeById(int id) {
		String sql = "DELETE FROM promocoes WHERE codPromo="+id;
		 try {
			 PreparedStatement stmt = connection.prepareStatement(sql);
			 
			 stmt.execute();
			 stmt.close();
		 }catch (SQLException e) {
			 //ISSO TUDO PARA Q NÃO OCORRA O MESMO PROBLEMA COM O CLIENTE
			 System.out.println("[ERROR] ocorreu um erro ao tentar remover a promoção: " +e.getMessage());
			 System.out.println("--- TEREMOS QUE REMOVER A ASSOCIAÇÃO A UM DETERMINADO DESTINO ---");
			 System.out.println("removendo . . .");
			 String update = "UPDATE destinos SET fk_codPromo= NULL WHERE fk_codPromo="+id;
			 try {
				PreparedStatement stmt2 = connection.prepareStatement(update);
				stmt2.execute();
				stmt2.close();
				System.out.println("--- RELAÇÃO RETIRADA, TENTE NOVAMENTE ---");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		 }
	}
	
	 public ResultSet getPromos() throws SQLException{
		 //READ (TUDO)
		 String sql = "SELECT * FROM promocoes";
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
		 String sql = "SELECT * FROM promocoes WHERE codVoo="+id;
		 ResultSet resultado = null;
		 try {
			 PreparedStatement stmt = connection.prepareStatement(sql);
			 resultado = stmt.executeQuery(sql);
			 
			 if(resultado.next()) {
				 System.out.println("ID -- >"+resultado.getInt(1));
				 System.out.println("DESCONTO -- >"+resultado.getInt(2)+"%");
				 System.out.println("NOMEPROMO -- >"+resultado.getDouble(3));  
			 }
		 }catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return resultado;
	 }
}
