package TP9.apli.view;

import java.sql.ResultSet;
import java.sql.SQLException;

import TP9.apli.mainApp;
import TP9.apli.model.Person;
import TP9.apli.util.funciones;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaUsuarioController {

	@FXML
	private Label labelTienda;

	@FXML
	private TextField user;

	@FXML
	private TextField pass;

	private mainApp mainApp;

	private Stage dialogStage;

	private Person p = new Person();

	public PantallaUsuarioController() {

	}

	@FXML
	public void Login(ActionEvent event) throws SQLException {

		ResultSet rs = funciones.datosUsuario(user.getText(), pass.getText());
		if (rs != null) {

			p.setName(user.getText());

			if (rs.getInt(3) == 1) {
				p.setDineros(true);
			} else {
				p.setDineros(false);
			}
			mainApp.dameCompra().setPer(p);
			mainApp.showPantallaCompra();

		}

	}

	@FXML
	public void Register(ActionEvent event) {
		mainApp.showPantallaRegistro();
	}

	@FXML
	public void Exit(ActionEvent event) {
		Platform.exit();
	}

	public void setMainApp(mainApp mainApp) {
		this.mainApp = mainApp;

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
}