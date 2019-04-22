package TP9.apli.view;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import TP9.apli.mainApp;
import TP9.apli.model.Compra;
import TP9.apli.model.ProdComprado;
import TP9.apli.model.Producto;
import TP9.apli.util.funciones;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PantallaCompraController {

	@FXML
	private TableView<Producto> productoTable;
	@FXML
	private TableColumn<Producto, Integer> idColumn;
	@FXML
	private TableColumn<Producto, String> productoColumn;
	@FXML
	private TableColumn<Producto, Float> precioColumn;
	@FXML
	private TextField idProd;
	@FXML
	private TextField cantProd;
	private Map<Integer, Producto> dic;

	// Reference to the main application.
	private mainApp mainApp;

	public PantallaCompraController() {

	}

	private void diccionario(ObservableList<Producto> observableList) {
		int n = 1;
		dic = new HashMap<Integer, Producto>();
		for (Producto p : observableList) {
			dic.put(n, p);
			n++;
		}
	}

	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		idColumn.setCellValueFactory(
				cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getIdproducto()).asObject());

		productoColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));

		precioColumn
				.setCellValueFactory(cellData -> new ReadOnlyFloatWrapper(cellData.getValue().getPrecio()).asObject());
	}

	public void setMainApp(mainApp mainApp) {
		this.mainApp = mainApp;
		// Add observable list data to the table
		productoTable.setItems(mainApp.getPersonData());
		diccionario(mainApp.getPersonData());
	}

	@FXML
	private void handleCarrito() {
		mainApp.showPantallaCarrito();
	}

	@FXML
	private void handleSeleccionar() {
		Producto p1 = dic.get(Integer.parseInt(idProd.getText()));
		ProdComprado p2 = new ProdComprado(p1.getIdproducto(), p1.getNombre(), p1.getPrecio());
		p2.setCantidad(Integer.parseInt(cantProd.getText()));
		mainApp.dameCompra().addLista(p2);
		idProd.clear();
		cantProd.clear();
	}

	@FXML
	private void handleSalir() {
		Platform.exit();
	}
}
