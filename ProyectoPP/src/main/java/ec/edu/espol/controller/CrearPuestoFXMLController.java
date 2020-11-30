/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import ec.edu.espol.model.Medico;
import ec.edu.espol.model.Puesto;
import ec.edu.espol.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author gabrielaramos
 */
public class CrearPuestoFXMLController implements Initializable {


    @FXML
    private ComboBox cbxM;
    
    Map<String, Usuario> medicos = Medico.leer("medicos.ser");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxM.setItems(FXCollections.observableArrayList(Medico.nombresMedicos()));
    }    
    
    @FXML
    private void regresar(MouseEvent event) {
        try {
            FXMLLoader fxmlloader1 = App.loadFXMLoad("PrincipalFXML");
            App.setRoot(fxmlloader1);

        }catch (IOException ex) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION, "ERROR");
            alerta.show();
        }              
    }

    @FXML
    private void registroP(MouseEvent event) {
        try {
            FXMLLoader fxmlloader2 = App.loadFXMLoad("VentanaFXML");
            App.setRoot(fxmlloader2);
            VentanaFXMLController controlador=fxmlloader2.getController();

            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "No se puede mostrar");
                a.show();
            }              
    }

    @FXML
    private void regresarP(MouseEvent event) {
        try {
            FXMLLoader fxmlloader1 = App.loadFXMLoad("PuestoFXML");
            App.setRoot(fxmlloader1);
            PuestoFXMLController controlador=fxmlloader1.getController();

            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "No se puede mostrar");
                a.show();
            }               
    }

    @FXML
    private void registroM(MouseEvent event) {
        try {
            FXMLLoader fxmlloader2 = App.loadFXMLoad("RegistroMedicoFXML");
            App.setRoot(fxmlloader2);
            RegistroMedicoFXMLController controlador=fxmlloader2.getController();

            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "No se puede mostrar");
                a.show();
            }         
         
    }

    @FXML
    private void crearPuesto(MouseEvent event) {
        String nombre = cbxM.getValue().toString();
        if (nombre == null){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION, "MEDICO NO SELECCIONADO");
            alerta.show();
        }
        else{
            Medico medico = (Medico) medicos.get(nombre);
            System.out.println(Puesto.obtenerCod("puestos.ser"));
            Puesto p = new Puesto(Puesto.obtenerCod("puestos.ser"), medico);
            medico.setPuesto(p.getCodigo());
            Queue<Puesto> puestos = Puesto.leer("puestos.ser");
            puestos.offer(p);
            System.out.println(puestos.size());
            System.out.println(medico);
            Puesto.guardar(puestos, "puestos.ser");   
            Medico.guardar(medicos, "medicos.ser");
        }
    }

}
