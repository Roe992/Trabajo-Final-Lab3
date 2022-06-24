package com.app;

import java.io.Serializable;

public class Cliente implements Serializable {

    public String nombre;

    public String dni;

    public double dinero; //(Presupuesto del Cliente)

    public String  fechaNacimiento;

    public Cliente(String nombre, String dni, Double dinero, String fechaNacimiento)
    {
        this.nombre=nombre;
        this.dni=dni;
        this.dinero=dinero;
        this.fechaNacimiento=fechaNacimiento;
    }

    public String getDni()
    {
        return dni;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public double getDinero() {
        return dinero;
    }

    @Override
    public String toString()
    {
        StringBuilder cliente=new StringBuilder("");

        cliente.append("Nombre: " + this.nombre + "\n");
        cliente.append("DNI: " + this.dni + "\n");
        cliente.append("Dinero: " + this.dinero + "\n");
        cliente.append("Fecha de Nacimiento: " + this.fechaNacimiento + "\n");

        return cliente.toString();
    }


}
