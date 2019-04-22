package TP9.apli.model;


import java.io.Serializable;
import java.util.ArrayList;

public class Compra implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Person per;
	@SuppressWarnings("unused")
	private ProdComprado producto;
	private ArrayList<ProdComprado> lista;

	public Compra() {
		// constructor por defecto
		per = new Person();
		lista = new ArrayList<ProdComprado>();
	}

	public void setPer(Person per) {
		this.per = per;
	}

	public ArrayList<ProdComprado> getLista() {
		return lista;
	}
	
	public void addLista(ProdComprado producto) {
		this.producto = producto;
		this.lista.add(producto);
	}
	
	public void delLista() {
		this.lista = null;
		this.lista = new ArrayList<ProdComprado>();
	}
	public Person getPer() {
		return per;
	}

}
