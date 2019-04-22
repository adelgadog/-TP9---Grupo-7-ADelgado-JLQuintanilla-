package TP9.apli.view;

import TP9.apli.mainApp;
import TP9.apli.model.Person;
import TP9.apli.model.Producto;
import TP9.apli.util.funciones;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PantallaRegistroController {
	
	@FXML
    private Label labelTienda;
   
    @FXML
    private TextField Usuario;
    
   
    @FXML
    private PasswordField psw;

    @FXML
    private RadioButton radioButton1 = new RadioButton("Si");
    
    @FXML
    private RadioButton radioButton2 = new RadioButton("No");
    
	private mainApp mainApp;

	private Stage dialogStage;
	
	private Person p = new Person();
	
	public PantallaRegistroController() {
		
	}
	
	

	@FXML
    public void Registrado(ActionEvent event) {
		boolean tieneDinero = radioButton1.isSelected();
		String user = Usuario.getText();
		String pass = psw.getText();
		funciones.datosRegistro(user, pass, tieneDinero);
		p.setName(user);
		p.setDineros(tieneDinero);
		mainApp.dameCompra().setPer(p);
		mainApp.showPantallaCompra();
		
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
