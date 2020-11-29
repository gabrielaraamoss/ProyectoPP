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
    private static final long serialVersionUID = 4L;
    
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
    
    
    public static ArrayList<Turno> leer(String archivo) {
        ArrayList<Turno> turnos=new ArrayList<>();
        try(ObjectInputStream es = new ObjectInputStream(new FileInputStream(archivo))){
            turnos=(ArrayList<Turno>)es.readObject();
            turnos.sort((Turno t1 , Turno t2)-> t2.getPaciente().getSintoma().getPrioridad()-t1.getPaciente().getSintoma().getPrioridad());
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        } catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        } 
        return turnos;
      
    }    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Turno{" + "codigo=" + codigo + ", puesto=" + puesto + ", paciente=" + paciente + '}';
    }
    
    
    
}
