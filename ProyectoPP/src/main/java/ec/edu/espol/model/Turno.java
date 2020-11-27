/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author gabrielaramos
 */
public class Turno implements Serializable {
    private String codigo;
    private Puesto puesto;
    private Paciente paciente;

    public Turno(String codigo, Puesto puesto, Paciente paciente) {
        this.codigo = codigo;
        this.puesto = puesto;
        this.paciente = paciente;
    }

    public static void guardar(ArrayList<Turno> arraylist,String archivo){
        try(ObjectOutputStream es = new ObjectOutputStream(new FileOutputStream(archivo))){
            try{
                if (arraylist.size()<=0){
                    throw new ErrorEmptyList(arraylist.size());
                }else{             
                    es.writeObject(arraylist);
                }
                
            }catch(ErrorEmptyList e){
                System.out.println(e);
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    
    }
    
    
    public static ArrayList<Turno> leer(String archivo) throws ClassNotFoundException{
        ArrayList<Turno> turnos=new ArrayList<>();
        try(ObjectInputStream es = new ObjectInputStream(new FileInputStream(archivo))){
            turnos=(ArrayList<Turno>)es.readObject();

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        } 
        return turnos;
      
    }       
    
}