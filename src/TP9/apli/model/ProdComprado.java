package TP9.apli.model;

public class ProdComprado extends Producto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int cantidad;
	float total;
	
	public float getTotal() {
		return total;
	}


	public ProdComprado(int idproducto, String nombre, float precio) {
		super(idproducto, nombre, precio);
		// TODO Auto-generated constructor stub
	}

	public ProdComprado() {
		// TODO Auto-generated constructor stub
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		this.total = cantidad * super.getPrecio();
	}

}
