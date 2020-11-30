/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.gui.App;
import ec.edu.espol.model.CircularDoublyLinkedList;
import ec.edu.espol.model.Reloj;
import ec.edu.espol.model.Turno;
import ec.edu.espol.model.Video;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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

    LinkedList<Turno> turnos = Turno.leer("turnos.ser");
    private Reloj reloj;
    private final CircularDoublyLinkedList<String> videos = Video.leer("videos.txt");
    private Iterator it = videos.iterator();

    @FXML
    private MediaView ventanaVideo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turnos.sort((Turno t1, Turno t2)
                -> t1.getPaciente().getSintoma().getPrioridad() - t2.getPaciente().getSintoma().getPrioridad());
        reloj = new Reloj(tiempo);
        reloj.start();
        reproducirVideos(it);
        int tamanio = turnos.size();
        if (tamanio >= 3) {
            Turno t = turnos.pollFirst();
            turno1.setText(t.getCodigo());
            puesto1.setText(String.valueOf(t.getPuesto().getCodigo()));
            t = turnos.pollFirst();
            turno2.setText(t.getCodigo());
            puesto2.setText(String.valueOf(t.getPuesto().getCodigo()));
            t = turnos.pollFirst();
            turno3.setText(t.getCodigo());
            puesto3.setText(String.valueOf(t.getPuesto().getCodigo()));

        } else if (tamanio == 2) {
            Turno t = turnos.pollFirst();
            turno1.setText(t.getCodigo());
            puesto1.setText(String.valueOf(t.getPuesto().getCodigo()));
            t = turnos.pollFirst();
            turno2.setText(t.getCodigo());
            puesto2.setText(String.valueOf(t.getPuesto().getCodigo()));
        } else {
            Turno t = turnos.pollFirst();
            turno1.setText(t.getCodigo());
            puesto1.setText(String.valueOf(t.getPuesto().getCodigo()));
        }
    }

    public void reproducirVideos(Iterator it) {
        String url = it.next().toString();
        Media media = new Media(new File(url).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setAutoPlay(true);
        player.setOnEndOfMedia(() -> {
            reproducirVideos(it);
        });
        ventanaVideo.setMediaPlayer(player);
    }

    @FXML
    private void regresar(MouseEvent event) {
        reloj.stopHilo();
        try {
            FXMLLoader fxmlloader1 = App.loadFXMLoad("VentanaFXML");
            App.setRoot(fxmlloader1);

        } catch (IOException ex) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION, "ERROR");
            alerta.show();
        }

    }
}
