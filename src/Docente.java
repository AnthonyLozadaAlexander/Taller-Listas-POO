/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public class Docente extends Persona{
    private double sueldo;
    private int horasExtras;
    private double valorHorasExtras;
    private double totalSueldo;

    public Docente(double sueldo, int horasExtras, double valorHorasExtras,String nombre, int edad) {
        super(nombre, edad);
        this.sueldo = sueldo;
        this.horasExtras = horasExtras;
        this.valorHorasExtras = valorHorasExtras;
        this.totalSueldo = sueldo + (horasExtras + valorHorasExtras);
    }

    public double getSueldo() {
        return sueldo;
    }

    public int getHorasExtras() {
        return horasExtras;
    }

    public double getValorHorasExtras() {
        return valorHorasExtras;
    }

    public double getTotalSueldo() {
        return totalSueldo;
    }
    
    
    
    @Override
    public String mostrarInfo(){
        return super.mostrarInfo() + " Sueldo: " + sueldo + "\n Horas Extras: " + horasExtras + "\n valorHorasExtras: " + valorHorasExtras + "\n totalSueldo: " + totalSueldo + "\n\n";
    }
    
    
    
}
