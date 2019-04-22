package TP9.apli;

import java.io.IOException;

import TP9.apli.model.Compra;
import TP9.apli.model.Person;
import TP9.apli.model.Producto;
import TP9.apli.util.funciones;
import TP9.apli.view.PantallaCarritoController;
import TP9.apli.view.PantallaCompraController;
import TP9.apli.view.PantallaRegistroController;
import TP9.apli.view.PantallaUsuarioController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class mainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Producto> productoDato = FXCollections.observableArrayList();
	private Compra c = new Compra();

	public mainApp() {
		productoDato = funciones.dameProductos();
		Person per = new Person("Pepe", true);
		c.setPer(per);
	}

	public ObservableList<Producto> getPersonData() {
		return productoDato;
	}

	public Compra dameCompra() {
		return c;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Tienda");
		initRoot();
		showPantallaUsuario();
	}

	/**
	 * * Initializes the root layout.
	 */
	public void initRoot() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(mainApp.class.getResource("view/rootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * * Shows the person overview inside the root layout.
	 */

	public void showPantallaUsuario() {

		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(mainApp.class.getResource("view/PantallaUsuario.fxml"));
			AnchorPane PantallaUsuario = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(PantallaUsuario);
			///// paso 2////
			PantallaUsuarioController controller = loader.getController();
			controller.setMainApp(this);

			//////////

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * ----
	 */
	public void showPantallaRegistro() {

		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(mainApp.class.getResource("view/PantallaRegistro.fxml"));
			AnchorPane PantallaRegistro = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(PantallaRegistro);
			///// paso 2////
			PantallaRegistroController controller = loader.getController();
			controller.setMainApp(this);

			//////////

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showPantallaCompra() {

		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(mainApp.class.getResource("view/PantallaCompra.fxml"));
			AnchorPane PantallaCompra = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(PantallaCompra);
			///// paso 2////
			PantallaCompraController controller = loader.getController();
			controller.setMainApp(this);

			//////////

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showPantallaCarrito() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(mainApp.class.getResource("view/PantallaCarrito.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			rootLayout.setCenter(page);
			PantallaCarritoController controller = loader.getController();
			controller.setMainApp(this);
			// Show the dialog and wait until the user closes it
		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * * Returns the main stage. *
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
