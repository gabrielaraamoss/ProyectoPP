/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.CircularDoublyLinkedList;
import ec.edu.espol.model.List;
import ec.edu.espol.model.Puesto;
import ec.edu.espol.model.Turno;
import ec.edu.espol.model.Video;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    
    private ArrayList<Turno> turnos = Turno.leer("turnos.ser");
    private Thread hiloReloj;
    private final boolean banderaHilo = true;
    
    @FXML
    private MediaView ventanaVideo;

    /**
     * Initializes the controller class.
     */
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reproducirVideos(Video.leer("videos.txt"));
        hiloReloj=new Thread(new Runnable() {
            @Override
            public void run() {
                    while(banderaHilo){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                           LocalDateTime locaDate = LocalDateTime.now();
                           int hours = locaDate.getHour();
                           int minutes = locaDate.getMinute();
                           int seconds = locaDate.getSecond();
                           tiempo.setText(hours+":"+minutes+":"+seconds);
                           System.out.println(seconds); 
                        }
                    });
                    try {
                        Thread.sleep(1000);    
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            
        });
        hiloReloj.start();
        
  
        Turno t = turnos.get(0);
        System.out.println(t);
        turno1.setText(t.getCodigo());
        /*puesto1.setText(String.valueOf(t.getPuesto().getCodigo()));
        /*t = turnos.get(1);
        turno2.setText(t.getCodigo());
        puesto2.setText(String.valueOf(t.getPuesto().getCodigo()));
        t = turnos.get(2);
        turno3.setText(t.getCodigo());
        puesto3.setText(String.valueOf(t.getPuesto().getCodigo()));*/
        
        
        
    }    
    
    public void reproducirVideos(CircularDoublyLinkedList<String> videos){
        Iterator it =videos.iterator();
        String url = it.next().toString();
        Media media = new Media(new File(url).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setAutoPlay(true);
        player.setOnEndOfMedia(() -> {
            reproducirVideos(videos);
        });
        ventanaVideo.setMediaPlayer(player);
    }
    
}
