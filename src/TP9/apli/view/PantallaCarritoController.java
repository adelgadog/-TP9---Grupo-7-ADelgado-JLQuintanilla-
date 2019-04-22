package TP9.apli.view;

import TP9.apli.mainApp;
import TP9.apli.model.ProdComprado;
import TP9.apli.util.funciones;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class PantallaCarritoController {
	@FXML
	private TableView<ProdComprado> productoTable;
	@FXML
	private TableColumn<ProdComprado, String> productoColumn;
	@FXML
	private TableColumn<ProdComprado, Float> precioColumn;
	@FXML
	private TableColumn<ProdComprado, Float> cantColumn;
	@FXML
	private TableColumn<ProdComprado, Float> totalColumn;
	
	private Stage dialogStage;
	
	private mainApp mainApp;
	
	public PantallaCarritoController(){
		
	}
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		productoColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
		
		precioColumn.setCellValueFactory(cellData -> new ReadOnlyFloatWrapper(cellData.getValue().getPrecio()).asObject());

		cantColumn.setCellValueFactory(cellData -> new ReadOnlyFloatWrapper(cellData.getValue().getCantidad()).asObject());
		
		totalColumn.setCellValueFactory(cellData -> new ReadOnlyFloatWrapper(cellData.getValue().getTotal()).asObject());
	
	}
	public void setMainApp(mainApp mainApp) {
		this.mainApp = mainApp;
		// Add observable list data to the table
		ObservableList<ProdComprado> lista = FXCollections.observableArrayList(mainApp.dameCompra().getLista());
		productoTable.setItems(lista);
	}
	@FXML
	private void handleSalir() {
		Platform.exit();
	}
	@FXML
	private void handleComprar() {		
		funciones.darTicket(mainApp.dameCompra());
		funciones.guardarDB(mainApp.dameCompra());
		Platform.exit();
	}
}
