/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.ArrayList;


/**
 *
 * @author gabrielaramos
 */
public class Paciente extends Usuario {
    private int edad;
    private char genero;
    private String sintoma;

    public Paciente(String nombre, String apellido, int edad, char genero, String sintoma) {
        super(nombre,apellido);
        this.edad = edad;
        this.genero = genero;
        this.sintoma = sintoma;
    }


    
}