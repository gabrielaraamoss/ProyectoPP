/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import ec.edu.espol.model.Turno;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author dilan
 */
public class RecetarFXMLController implements Initializable {


    @FXML
    private Label turno;
    @FXML
    private Label medico;
    @FXML
    private Label paciente;
    
    LinkedList<Turno> turnos = Turno.leer("turnos.ser");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         turnos.sort((Turno t1 , Turno t2)-> 
                t1.getPaciente().getSintoma().getPrioridad()- t2.getPaciente().getSintoma().getPrioridad());
         Turno turn = turnos.pollFirst();
         paciente.setText(turn.getPaciente().getNombres()+" "+turn.getPaciente().getApellidos());
         medico.setText(turn.getPuesto().getMedico().getNombres());
         turno.setText(turn.getCodigo());
         
    }    
    
    @FXML
    private void registrar(MouseEvent event) {
        Turno.guardar(turnos, "turnos.ser");
        try {
            FXMLLoader fxmlloader1 = App.loadFXMLoad("PrincipalFXML");
            App.setRoot(fxmlloader1);
            PuestoFXMLController controlador=fxmlloader1.getController();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
