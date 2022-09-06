package model;


public class Voo {
	//ATRIBUTOS
	private int id;
	private Double precoVoo;
	private	String companhia;
	
	//GET AND SET
	//ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//PREÇO DA PASSAGEM
	public Double getPrecoVoo() {
		return precoVoo;
	}
	public void setPrecoVoo(Double precoVoo) {
		this.precoVoo = precoVoo;
	}
	
	//COMPANHIA AEREA
	public String getCompanhia() {
		return companhia;
	}
	public void setCompanhia(String companhia) {
		this.companhia = companhia;
	}
	
	
}
