package ec.edu.espol.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabrielaramos
 */
public class Puesto implements Serializable {
    private int codigo;
    private PriorityQueue<Turno> turnos;
    private Medico medico;

    public Puesto(int codig, Medico medico) {
        this.codigo = codigo;
        this.turnos = new PriorityQueue<>((Turno t1 , Turno t2)-> t1.getPaciente().getSintoma().getPrioridad()-t2.getPaciente().getSintoma().getPrioridad());
        this.medico = medico;
    }

 public static void guardar(Queue<Puesto> cola,String archivo){
        try(ObjectOutputStream es = new ObjectOutputStream(new FileOutputStream(archivo))){
            try{
                if (cola.size()<=0){
                    throw new ErrorEmptyList(cola.size());
                }else{             
                    es.writeObject(cola);
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
    
    
    public static Queue<Puesto> leer(String archivo){
        Queue<Puesto> puestos=new LinkedList<>();
        try(ObjectInputStream es = new ObjectInputStream(new FileInputStream(archivo))){
            puestos=(Queue<Puesto>)es.readObject();

        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println(e.getMessage());
        } 
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        } 
        return puestos;
      
    }           
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public PriorityQueue<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(PriorityQueue<Turno> turnos) {
        this.turnos = turnos;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    
}

    