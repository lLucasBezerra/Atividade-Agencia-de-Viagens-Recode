package model;



public class Promocoes {
	//ATRIBUTOS
	private int id;
	private int desconto;
	private String nomePromo;
	private Destino itensPromo; //fk
	

	//GET AND SET
	//ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//DESCONTO
	public int getDesconto() {
		return desconto;
	}
	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}
	
	//NOME DA PROMOÇÃO
	public String getNomePromo() {
		return nomePromo;
	}
	public void setNomePromo(String nomePromo) {
		this.nomePromo = nomePromo;
	}
	
	//fk
	public Destino getItensPromo() {
		return itensPromo;
	}
	public void setItensPromo(Destino itensPromo) {
		this.itensPromo = itensPromo;
	}
}
