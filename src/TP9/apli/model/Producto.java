package TP9.apli.model;

import java.io.Serializable;

public class Producto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idproducto;
	private String nombre;
	private float precio;

	public Producto(int idproducto, String nombre, float precio) {
		this.idproducto = idproducto;
		this.nombre = nombre;
		this.precio = precio;
	}

	public Producto() {

	}

	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
