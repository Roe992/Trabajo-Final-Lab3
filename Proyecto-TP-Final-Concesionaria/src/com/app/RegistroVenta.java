package com.app;

import java.io.Serializable;

public class RegistroVenta implements Serializable {

	private String DNI;
	private String nombre;
	private double precio;
	private String medioPago;
	private String patente;
	private String marca;
	private String modelo;
	private int numeroTransaccion;
	
	public RegistroVenta(String DNI, String nombre, double precio, String medioPago, String patente, String marca, String modelo, int numeroTransaccion) {
		this.DNI = DNI;
		this.marca = marca;
		this.medioPago = medioPago;
		this.modelo = modelo;
		this.nombre = nombre;
		this.numeroTransaccion = numeroTransaccion;
		this.patente = patente;
		this.precio = precio;
	}
	
	public String getDNI() {
		return DNI;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public String getMedioPago() {
		return medioPago;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getPatente() {
		return patente;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public int getNumeroTransaccion() {
		return numeroTransaccion;
	}
	
	@Override
	public String toString() {
		return "\n--------------------------------"+
				"\nTransaccion numero: "+this.numeroTransaccion+
				"\nMedio de pago: "+this.medioPago+
				"\nCliente: " + 
				"\n\tDNI: "+this.DNI+ "\n\tNombre: "+this.nombre+
				"\nAuto: "+ 
				"\n\tMarca: "+this.marca+"\n\tModelo: "+this.modelo+"\n\tPatente: "+this.patente+"\n\tPrecio: "+this.precio +
				"\n--------------------------------\n";
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean respuesta = false;
		RegistroVenta rb = (RegistroVenta) obj;
		if(obj instanceof RegistroVenta) {
			if(rb.getNumeroTransaccion()==this.numeroTransaccion) {
				respuesta = true;
			}
		}
		return respuesta;
	}
}
