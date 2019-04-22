package TP9.apli.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import TP9.apli.model.Compra;
import TP9.apli.model.ProdComprado;
import TP9.apli.model.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class funciones {

	public static void darTicket(Compra carrito) {
		ArrayList<ProdComprado> listaproductos = carrito.getLista();
		LocalDateTime fecha = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy.HH;mm;ss");
		String fechaticket = fecha.format(myFormatObj);
		float total = 0;
		try {
			Writer wr = new FileWriter(new File(".\\tickets\\", "ticket" + fechaticket + ".txt"));
			for (ProdComprado p : listaproductos) {
				wr.write(p.getNombre() + ":	" + (p.getPrecio() * p.getCantidad()) + "€\n");
				wr.write(System.lineSeparator());
				wr.flush();
				total = total + (p.getPrecio() * p.getCantidad());
			}
			wr.write("El total de su compra es: " + total + "€\n");
			wr.write(System.lineSeparator());
			wr.write("Gracias por comprar en Quintanilla-Delgado SL.\n");
			wr.write(System.lineSeparator());
			wr.flush();
			wr.close();
		} catch (IOException e) {
			System.err.println("ERROR");
			System.err.println("An IOException was caught :" + e.getMessage());
		}
	}

	public static void guardarDB(Compra carrito) {
		String nombreDB = "HistorialCompra.db";
		String cliente = carrito.getPer().getName();
		ArrayList<ProdComprado> listaproductos = carrito.getLista();
		String sql2 = "INSERT INTO historial (usuario,producto,precio,cantidad,total,fecha)VALUES(?,?,?,?,?,datetime('now'));";
		try (PreparedStatement pstmt = conectaDB(nombreDB).prepareStatement(sql2)) {
			for (ProdComprado p : listaproductos) {
				pstmt.setString(1, cliente);
				pstmt.setString(2, p.getNombre());
				pstmt.setFloat(3, p.getPrecio());
				pstmt.setInt(4, p.getCantidad());
				pstmt.setFloat(5, p.getTotal());
				pstmt.executeUpdate();
			}
		} catch (SQLException o) {
			System.out.println(o.getMessage());
		}
	}

	public static ObservableList<Producto> dameProductos() {
		String nombreDB = "testdb.db";
		ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
		String pregunta = "SELECT id, nombre, precio FROM producto";
		try (PreparedStatement pstmt = conectaDB(nombreDB).prepareStatement(pregunta)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Producto p = new Producto();
				p.setIdproducto(Integer.parseInt(rs.getString(1)));
				p.setNombre(rs.getString(2));
				p.setPrecio(rs.getInt(3));
				listaProductos.add(p);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return listaProductos;
	}

	private static Connection conectaDB(String nombreDB) {
		String url = "jdbc:sqlite:" + nombreDB;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			//if (conn != null) {
				//DatabaseMetaData meta = conn.getMetaData();
				//System.out.println("The driver name is " + meta.getDriverName());
				//System.out.println("A new database has been created.");
			//}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public static ResultSet datosUsuario(String usuario, String pass) {
		
		String sqlSelect = "select * from Usuario where usuario =? and contraseña =?";
		ResultSet rs = null;
		try {
			PreparedStatement stmtUs = conectaDB("BDUsuario.db").prepareStatement(sqlSelect);
		stmtUs.setString(1, usuario);
		stmtUs.setString(2, pass);
		rs = stmtUs.executeQuery();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
		
	}
	
	public static void datosRegistro(String usuario, String pass, Boolean dineros) {
		
		String insertar = "INSERT INTO Usuario (usuario,contraseña,dinero) VALUES(?,?,?);";
		try (PreparedStatement pstmt = conectaDB("BDUsuario.db").prepareStatement(insertar)) {
			pstmt.setString(1, usuario);
			pstmt.setString(2, pass);
			if (dineros) {
				pstmt.setInt(3, 1);
			}
			if (!dineros) {
				pstmt.setInt(3, 0);
			}
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
}
