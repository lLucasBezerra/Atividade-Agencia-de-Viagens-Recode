package model;

public class Agencia {
	private Destino destino;
	private Cliente cliente;
	private Promocoes promocao;
	
	//GET E SETT
	//DESTINO
	public Destino getDestino() {
		return destino;
	}
	public void setDestino(Destino destino) {
		this.destino = destino;
	}
	//CLIENTE
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	//PROMOCAO
	public Promocoes getPromocao() {
		return promocao;
	}
	public void setPromocao(Promocoes promocao) {
		this.promocao = promocao;
	}
	
	
}
