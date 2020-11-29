/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author gabrielaramos
 */
public class Video {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public static CircularDoublyLinkedList<String> leer (String path){
        CircularDoublyLinkedList<String> videos = new CircularDoublyLinkedList<>();
        try(FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr)){
                    String ruta = null;
                    while ((ruta = br.readLine())!= null){
                        videos.addFirst(ruta);
                    }
        }catch(Exception e){
            System.out.println(e.getMessage());
        
        }
        return videos;
    }
    
    @Override
    public String toString() {
        return "Video{" + "nombre=" + nombre + '}';
    }
    
    
    
}
