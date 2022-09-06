package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Date;

import factory.ConnectionFactory;
import model.Cliente;

public class ClienteDAO {
 private Connection connection;
 
 public ClienteDAO() {
	 this.connection = new ConnectionFactory().getConnection();
 }
 
 public void save(Cliente cliente) {
	 //CREATE
	 String sql = "INSERT INTO cliente VALUES(null, ?, ?, ?, ?)";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setString(1, cliente.getCpf());
		 stmt.setString(2, cliente.getOrigem());
		 stmt.setDate(3, new Date(cliente.getDataVolta().getTime()));
		 stmt.setDate(4, new Date(cliente.getDataIda().getTime()));
		 
		 stmt.execute();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 public void removeById(int id) {
	 //DELETE
	 String sql = "DELETE FROM cliente WHERE codCli=?";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setInt(1, id);
		 
		 stmt.execute();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 //CRIADO PARA REMOVER A RELAÇÃO NA TABELA ESCOLHA, POIS COM ELA NÃO DARIA PARA REMOVER
 public void removerRelação(int id) {
	 //DELETE
	 String sql = "DELETE FROM escolher WHERE fk_codCli=?";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setInt(1, id);
		 
		 stmt.execute();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 public void update(Cliente cliente, int id) {
	 //UPDATE
	 String sql = "UPDATE cliente SET cpf= ?, origem= ?, dataVolta= ?, dataIda= ? WHERE codCli= ?";
	 try {
	 PreparedStatement stmt = connection.prepareStatement(sql);
	 stmt.setString(1, cliente.getCpf());
	 stmt.setString(2, cliente.getOrigem());
	 stmt.setDate(3, new Date(cliente.getDataVolta().getTime()));
	 stmt.setDate(4, new Date(cliente.getDataIda().getTime()));
	 stmt.setInt(5, id);
	 
	 stmt.execute();
	 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 
 }
 
 public ResultSet getClientes() throws SQLException{
	 //READ (TUDO)
	 String sql = "SELECT * FROM cliente";
	 Statement stmt = null;
	 try {
		 stmt = connection.createStatement();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 return stmt.executeQuery(sql);
 }
 
 public ResultSet getIdCliente(int id) throws SQLException{
	 //READ (UNICO)
	 String sql = "SELECT * FROM cliente WHERE codCli="+id;
	 ResultSet resultado = null;
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 resultado = stmt.executeQuery(sql);
		 
		 if(resultado.next()) {
			 System.out.println("ID -- >"+resultado.getInt(1));
			 System.out.println("CPF -- >"+resultado.getString(2));
			 System.out.println("LOCAL DE ORIGEM -- >"+resultado.getString(3));
			 System.out.println("DATA DE VOLTA -- >"+resultado.getDate(4)); 
			 System.out.println("DATA DE IDA -- >"+resultado.getDate(5)); 
		 }
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 return resultado;
 }
 
 public ResultSet getCpfCliente(Cliente cliente) throws SQLException{
	 //READ (PARA O CLIENTE USAR)
	 String sql = "SELECT * FROM cliente WHERE cpf="+cliente.getCpf();
	 ResultSet resultado = null;
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 resultado = stmt.executeQuery(sql);
		 
		 if(resultado.next()) {
			 System.out.println("ID -- >"+resultado.getInt(1));
			 System.out.println("CPF -- >"+resultado.getString(2));
			 System.out.println("LOCAL DE ORIGEM -- >"+resultado.getString(3));
			 System.out.println("DATA DE VOLTA -- >"+resultado.getDate(4));
			 System.out.println("DATA DE IDA -- >"+resultado.getDate(5));
		 }
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 return resultado;
 }
 
 public void pegarID(Cliente cliente) {
	 //PEGANDO O ID PARA PODER FAZER O METODO fazerRelacao()
	 String sql = "SELECT codCli FROM cliente WHERE cpf="+cliente.getCpf();
	 ResultSet resultado = null;
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 resultado = stmt.executeQuery(sql);
		 
		 if(resultado.next()) {
			 cliente.setId(resultado.getInt(1));
		 }
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
 }
 
 public void fazerRelacao(Cliente cliente) {
	 //PARA FAZER RELAÇÃO MUITOS PARA MUITOS
	 String sql = "INSERT INTO escolher VALUES(?,?,?)";
	 try {
		 PreparedStatement stmt = connection.prepareStatement(sql);
		 stmt.setInt(1, cliente.getId());
		 stmt.setInt(2, cliente.getDestino().getId());
		 stmt.setInt(3, cliente.getDestino().getVoo().getId());
		 
		 stmt.execute();
		 stmt.close();
	 }catch (SQLException e) {
		 e.printStackTrace();
	 }
	 
 }
 
}




