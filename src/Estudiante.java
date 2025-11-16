/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public class Estudiante extends Persona{
    private double nota1;
    private double nota2;
    private double promedio;

    public Estudiante(double nota1, double nota2, String nombre, int edad) {
        super(nombre, edad);
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.promedio = (nota1+nota2)/2;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public double getPromedio() {
        return promedio;
    }
    
    @Override
    public String mostrarInfo(){
        return super.mostrarInfo() + " Nota 1: " + nota1 + "\n Nota 2: " + nota2 + "\n Promedio: " + promedio + "\n\n";
    }
    
    
    
}
