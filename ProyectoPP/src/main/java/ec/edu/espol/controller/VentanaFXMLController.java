/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import ec.edu.espol.model.Paciente;
import ec.edu.espol.model.Puesto;
import ec.edu.espol.model.Sintoma;
import ec.edu.espol.model.Turno;
import ec.edu.espol.model.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author gabrielaramos
 */
public class VentanaFXMLController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellido;
    @FXML
    private TextField edad;
    @FXML
    private ComboBox cbxG;
    @FXML
    private ComboBox cbxS;
    FXMLLoader fxmlloader;
    Queue<Puesto> puestos = Puesto.leer("puestos.ser");
    Map<String, Sintoma> sintomas = Sintoma.leer("sintomas.ser");
    LinkedList<Turno> turnos = Turno.leer("turnos.ser");
    Alert a;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turnos.sort((Turno t1, Turno t2)
                -> t2.getPaciente().getSintoma().getPrioridad() - t1.getPaciente().getSintoma().getPrioridad());
        ArrayList<String> items = new ArrayList<>();
        ArrayList<String> items1 = new ArrayList<>();
        edad.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("(-?([0-9]*+[.])?[0-9]*)")) ? change : null));
        items.add("M");
        items.add("F");
        cbxG.setItems(FXCollections.observableArrayList(items));
        items1.add("Fiebre");
        items1.add("Desmayo");
        items1.add("Resfriado");
        items1.add("Congestión nasal");
        items1.add("Dolor de estómago");
        items1.add("Dolor de cabeza");
        items1.add("Dolor de pecho");
        items1.add("Escalofrío");
        items1.add("tos");
        items1.add("Infarto");
        cbxS.setItems(FXCollections.observableArrayList(items1));
    }

    @FXML
    private void regresar(MouseEvent event) {
        try {
            fxmlloader = App.loadFXMLoad("PrincipalFXML");
            App.setRoot(fxmlloader);

        } catch (IOException ex) {
            a = new Alert(Alert.AlertType.INFORMATION, "ERROR");
            a.show();
        }
    }

    @FXML
    private void puesto(MouseEvent event) {
        try {
            fxmlloader = App.loadFXMLoad("PuestoFXML");
            App.setRoot(fxmlloader);
            PuestoFXMLController controlador = fxmlloader.getController();

        } catch (IOException ex) {
            a = new Alert(Alert.AlertType.INFORMATION, "No se puede mostrar");
            a.show();
        }
    }

    @FXML
    private void registroM(MouseEvent event) {
        try {
            fxmlloader = App.loadFXMLoad("RegistroMedicoFXML");
            App.setRoot(fxmlloader);
            RegistroMedicoFXMLController controlador = fxmlloader.getController();

        } catch (IOException ex) {
            a = new Alert(Alert.AlertType.INFORMATION, "No se puede mostrar");
            a.show();
        }

    }

    @FXML
    private void registrar(MouseEvent event) {
        if (nombre.getText().isEmpty() || apellido.getText().isEmpty() || cbxS.getValue() == null) {
            if (nombre.getText().isEmpty()) {
                a = new Alert(Alert.AlertType.INFORMATION, "INGRESE NOMBRES");
                a.show();
            } else if (apellido.getText().isEmpty()) {
                a = new Alert(Alert.AlertType.INFORMATION, "INGRESE APELLIDOS");
                a.show();
            } else if (edad.getText().isEmpty()) {
                a = new Alert(Alert.AlertType.INFORMATION, "INGRESE EDAD");
                a.show();
            } else if (cbxG.getValue() == null) {
                a = new Alert(Alert.AlertType.INFORMATION, "SELECCIONE");
                a.show();

            } else if (cbxS.getValue() == null) {
                a = new Alert(Alert.AlertType.INFORMATION, "SELECCIONE");
                a.show();

            }

            a = new Alert(Alert.AlertType.INFORMATION, "INGRESE DATOS");
            a.show();

        } else {
            Paciente paciente = new Paciente(nombre.getText(), apellido.getText(), Integer.parseInt(edad.getText()), cbxG.getValue().toString().charAt(0), sintomas.get(cbxS.getValue().toString()));
            Puesto p = puestos.poll();
            int tamaño = p.getTurnos().size();
            char inicial1 = p.getMedico().getNombres().charAt(0);
            char inicial2 = p.getMedico().getApellidos().charAt(0);
            String codTurno = inicial1 + "" + inicial2 + tamaño;
            Turno turno = new Turno(codTurno, p, paciente);
            p.getTurnos().add(turno);
            turnos.add(turno);
            Turno.guardar(turnos, "turnos.ser");
            Map<String, Usuario> pacientes = Paciente.leer("pacientes.ser");
            pacientes.put(paciente.getNombres(), paciente);
            Paciente.guardar(pacientes, "pacientes.ser");
            puestos.offer(p);
            Puesto.guardar(puestos, "puestos.ser");
            try {
                fxmlloader = App.loadFXMLoad("AtencionFXML");
                App.setRoot(fxmlloader);
                AtencionFXMLController controlador = fxmlloader.getController();

            } catch (IOException ex) {
                a = new Alert(Alert.AlertType.INFORMATION, "No se puede mostrar");
                a.show();
            }

        }

    }

}
