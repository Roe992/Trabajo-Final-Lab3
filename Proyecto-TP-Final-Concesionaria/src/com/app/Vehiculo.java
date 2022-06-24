package com.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Vehiculo implements Serializable {
    private String patente;
    private String marca;
    private String modelo;
    private int kilometraje;
    private double precio;
    private String color;
    private String anioFabricacion;
    private int caballosDeFuerza;
    private String transmision;
    private String tipos;
    private Lista ListaTags;

    public Vehiculo() {
        ListaTags = new Lista<>();
    }


    public Vehiculo(String patente,String marca, String modelo, int kilometraje,double precio, String color, String anioFabricacion, int caballosDeFuerza, String transmision, String tipos)
    {
        this.anioFabricacion=anioFabricacion;
        this.caballosDeFuerza=caballosDeFuerza;
        this.color=color;
        this.kilometraje=kilometraje;
        this.marca=marca;
        this.precio=precio;
        this.transmision=transmision;
        this.modelo=modelo;
        this.tipos=tipos;
        this.patente=patente;
        this.ListaTags=new Lista<>();
        llenarTags();
    }

    public Lista getListaTags() {
        return ListaTags;
    }

    public int getCaballosDeFuerza() {
        return caballosDeFuerza;
    }

    public String getTipos() {
        return tipos;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public double getPrecio() {
        return precio;
    }

    public String getAnioFabricacion() {
        return anioFabricacion;
    }

    public String getColor() {
        return color;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPatente() {
        return patente;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void llenarTags()
    {
        ///tags de clase vehiculo
        ListaTags.Agregar("marca "+ getMarca());
        ListaTags.Agregar("modelo "+ getModelo());
        ListaTags.Agregar("kilometraje "+ getKilometraje());
        ListaTags.Agregar("color "+ getColor());
        ListaTags.Agregar("anioFabricacion "+ getAnioFabricacion());
        ListaTags.Agregar("caballosDeFuerza "+ getCaballosDeFuerza());
        ListaTags.Agregar("transmision "+ getTransmision());
        ListaTags.Agregar("tipos "+ getTipos());
        //
    }

    public static Lista<String> generarTags() //Genera una lista con los tags de Vehiculo (que se van a usar para hacer los tags del Usuario)
    {
        Lista<String> tagsVehiculo=new Lista<>();

        tagsVehiculo.Agregar("marca ");
        tagsVehiculo.Agregar("modelo ");
        tagsVehiculo.Agregar("kilometraje ");
        tagsVehiculo.Agregar("color ");
        tagsVehiculo.Agregar("anioFabricacion ");
        tagsVehiculo.Agregar("caballosDeFuerza ");
        tagsVehiculo.Agregar("transmision ");
        tagsVehiculo.Agregar("tipos ");

        return tagsVehiculo;
    }

    static public String menuTags() //Retorna una lista con los tags de Vehiculo (Los nombres para el menú de agregado de tags)
    {
        return  "1) Marca \n2) Modelo \n3) Kilometraje \n4) Color \n5) Año fabricación \n6) Caballos de fuerza \n7) Transmisión \n8) Tipos \n";
    }

    public void pasarDeJSON(JSONObject nuevo) throws JSONException {
        this.anioFabricacion = nuevo.getString("anioFabricacion");
        this.caballosDeFuerza = nuevo.getInt("caballosDeFuerza");
        this.color = nuevo.getString("color");
        this.kilometraje = nuevo.getInt("kilometraje");
        this.marca = nuevo.getString("marca");
        this.modelo = nuevo.getString("modelo");
        this.patente = nuevo.getString("patente");
        this.precio = nuevo.getDouble("precio");
        this.tipos = nuevo.getString("tipos");
        this.transmision = nuevo.getString("transmision");
        llenarTags();

    }

    public JSONObject pasarAJSON() throws JSONException {
        JSONObject nuevo = new JSONObject();
        nuevo.put("anioFabricacion", this.anioFabricacion);
        nuevo.put("caballosDeFuerza", this.caballosDeFuerza);
        nuevo.put("color", this.color);
        nuevo.put("kilometraje", this.kilometraje);
        nuevo.put("marca", this.marca);
        nuevo.put("modelo", this.modelo);
        nuevo.put("patente", this.patente);
        nuevo.put("precio", this.precio);
        nuevo.put("tipos", this.tipos);
        nuevo.put("transmision", this.transmision);

        return nuevo;
    }


    @Override
    public String toString() {
        return
                "patente='" + patente + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", kilometraje=" + kilometraje +
                ", precio=" + precio +
                ", color='" + color + '\'' +
                ", anioFabricacion='" + anioFabricacion + '\'' +
                ", caballosDeFuerza=" + caballosDeFuerza +
                ", transmision='" + transmision + '\'' +
                ", tipos='" + tipos + '\'';
    }
}
