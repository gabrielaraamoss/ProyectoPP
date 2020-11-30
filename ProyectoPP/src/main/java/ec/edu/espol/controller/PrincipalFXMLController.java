/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author gabrielaramos
 */
public class PrincipalFXMLController implements Initializable {

    Alert a;
    FXMLLoader fxmlloader;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void registro(MouseEvent event) {
        try {
            fxmlloader = App.loadFXMLoad("VentanaFXML");
            App.setRoot(fxmlloader);
            VentanaFXMLController controlador = fxmlloader.getController();

        } catch (IOException ex) {
            a = new Alert(Alert.AlertType.INFORMATION, "No se puede mostrar");
            a.show();
        }

    }

    @FXML
    private void recetar(MouseEvent event) {
        try {
            fxmlloader = App.loadFXMLoad("RecetarFXML");
            App.setRoot(fxmlloader);
            RecetarFXMLController controlador = fxmlloader.getController();

        } catch (IOException ex) {
            a = new Alert(Alert.AlertType.INFORMATION, "No se puede mostrar");
            a.show();
        }
    }

}
