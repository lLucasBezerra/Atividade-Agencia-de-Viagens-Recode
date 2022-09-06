package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import factory.ConnectionFactory;
import model.Destino;

public class DestinoDAO {
 private Connection connection;
 
 public DestinoDAO() {
	 this.connection = new ConnectionFactory().getConnection();
 }
 
 public void save(Destino destino) {
	 //CREATE
	 String sql = "INSERT INTO destinos(paises,cidade,obraR) VALUES(?,?,?)";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setString(1, destino.getPais());
		 stmt.setString(2, destino.getCidade());
		 stmt.setString(3, destino.getObraR());
		 
		 stmt.execute();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 public void removeById(int id) {
	 //DELETE
	 String sql = "DELETE FROM destinos WHERE codDest=?";
	 try {
	 PreparedStatement stmt = connection.prepareStatement(sql);
	 stmt.setInt(1, id);
	 
	 stmt.execute();
	 stmt.close();
	 }catch (SQLException e) {
		 //ISSO TUDO PARA Q NÃO OCORRA O MESMO PROBLEMA COM O CLIENTE
		 System.out.println("[ERROR] ocorreu um erro ao tentar remover a destino: " +e.getMessage());
		 System.out.println("--- TEREMOS QUE REMOVER A ASSOCIAÇÃO A UM(S) DETERMINADO CLIENTE ---");
		 System.out.println("removendo . . .");
		 String delete =  "DELETE FROM escolher WHERE fk_codDest=?";
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
 
 public void update(Destino destino, int id) {
	 //UPDATE															
	 String sql = "UPDATE destinos SET paises = ?, cidade = ?, obraR = ?  WHERE codDest = ?";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setString(1, destino.getPais());
		 stmt.setString(2, destino.getCidade());
		 stmt.setString(3, destino.getObraR());
		 stmt.setInt(4, id);
		 
		 stmt.execute();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 public void adcUmaPromo(int idDest, int idPromo) {
	 //UPDATE															
	 String sql = "UPDATE destinos SET fk_codPromo = ? WHERE codDest = ?";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setInt(1, idPromo);
		 stmt.setInt(2, idDest);
		 
		 
		 
		 stmt.execute();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 public ResultSet getDestinos() throws SQLException {
	 //READ (TUDO)
	 String sql = "SELECT * FROM destinos";
	 Statement stmt = null;
	 try {
		 stmt = connection.createStatement();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 return stmt.executeQuery(sql);
	 
 }
 public ResultSet getUmDestino(int id) throws SQLException{
	 //READ (UNICO)
	 String sql = "SELECT * FROM destinos WHERE codDest="+id;
	 ResultSet resultado = null;
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 resultado = stmt.executeQuery(sql);
		 if(resultado.next()) {
			 System.out.println("ID -- >"+resultado.getInt(1));
			 System.out.println("PAÍS -- >"+resultado.getString(2));
			 System.out.println("CIDADE -- >"+resultado.getString(3));
		 }
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 return resultado;
 }
}



