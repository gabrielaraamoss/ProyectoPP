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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gabrielaramos
 */
public class Sintoma  implements Serializable{
    private String nombre;
    private int prioridad;

    public Sintoma(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }
    

    
    public static void guardar(ArrayList<Sintoma> arraylist,String archivo){
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
    
    public static Map<String, Sintoma> leer(String archivo){
        Map<String , Sintoma> sintomasMap = new HashMap<>();
        try(ObjectInputStream es = new ObjectInputStream(new FileInputStream(archivo))){
           ArrayList<Sintoma>   sintomas=(ArrayList<Sintoma>)es.readObject();
            for (Sintoma s: sintomas){
                sintomasMap.put(s.getNombre(), s);
            }

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        } 
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        } 
        return sintomasMap;
      
    }        

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    
      
    @Override
    public String toString() {
        return nombre + "|" + prioridad;
    }
    

    
}
