/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Puesto;
import ec.edu.espol.model.Turno;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author gabrielaramos
 */
public class AtencionFXMLController implements Initializable {

    @FXML
    private Text tiempo;
    @FXML
    private AnchorPane video;
    @FXML
    private Label turno1;
    @FXML
    private Label turno3;
    @FXML
    private Label turno2;
    @FXML
    private Label puesto1;
    @FXML
    private Label puesto2;
    @FXML
    private Label puesto3;
    
    ArrayList<Turno> turnos = Turno.leer("turnos.ser");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDateTime locaDate = LocalDateTime.now();
        int hours  = locaDate.getHour();
        int minutes = locaDate.getMinute();
        tiempo.setText(hours+":"+minutes);
        Turno t = turnos.get(0);
        turno1.setText(t.getCodigo());
        puesto1.setText(String.valueOf(t.getPuesto().getCodigo()));
        t = turnos.get(1);
        turno2.setText(t.getCodigo());
        puesto2.setText(String.valueOf(t.getPuesto().getCodigo()));
        t = turnos.get(2);
        turno3.setText(t.getCodigo());
        puesto3.setText(String.valueOf(t.getPuesto().getCodigo()));
        
        
        
    }    
    
}
