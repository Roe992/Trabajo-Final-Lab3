package com.app;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Moto extends Vehiculo implements Serializable {

    private boolean espacioAcompaniante;
    private boolean mochilasDeViaje;
    private boolean parabrisa;

    public Moto() {
        super();
    }

    public Moto(String patente,String marca, String modelo, int kilometraje, double precio, String color, String anioFabricacion, int caballosDeFuerza, String transmision, String tipos,boolean espacioAcompaniante,boolean mochilasDeViaje,boolean parabrisa)
    {
        super(patente,marca,modelo,kilometraje,precio,color,anioFabricacion,caballosDeFuerza,transmision,tipos);
        this.espacioAcompaniante=espacioAcompaniante;
        this.mochilasDeViaje=mochilasDeViaje;
        this.parabrisa=parabrisa;
        llenarTags(); //Genera los tags con las variables del Vehiculo
    }

    @Override
    public void llenarTags()
    {
        super.getListaTags().Agregar("espacioAcompaniante " + espacioAcompaniante);
        super.getListaTags().Agregar("mochilasDeViaje "+ mochilasDeViaje);
        super.getListaTags().Agregar("parabrisa "+ parabrisa);
    }

    static public String menuTags() //Función estática que genera una lista con los tags de la Moto
    {
        String menuTags = Vehiculo.menuTags() + "9) Espacio de acompañante \n10) Mochila de viaje \n11) Parabrisa";

        return menuTags;
    }

    static public Lista<String> generarTags(){ //Agrega los tags que luego se utilizarán en la creación de tags del Usuario
        Lista<String> nuevoArray = Vehiculo.generarTags();
        nuevoArray.Agregar("espacioAcompaniante false");
        nuevoArray.Agregar("mochilaDeViaje false");
        nuevoArray.Agregar("parabrisa false");

        return nuevoArray;
    }

    @Override
    public String toString() {
        return super.toString()+
                ", espacioAcompaniante=" + espacioAcompaniante +
                ", mochilasDeViaje=" + mochilasDeViaje +
                ", parabrisa=" + parabrisa;
    }

    @Override
    public void pasarDeJSON(JSONObject nuevo) throws JSONException {
        super.pasarDeJSON(nuevo);
        this.espacioAcompaniante = nuevo.getBoolean("espacioAcompaniante");
        this.mochilasDeViaje = nuevo.getBoolean("mochilasDeViaje");
        this.parabrisa = nuevo.getBoolean("parabrisa");
    }

    @Override
    public JSONObject pasarAJSON() throws JSONException {
        JSONObject nuevo = new JSONObject();
        nuevo = super.pasarAJSON();
        nuevo.put("espacioAcompaniante", this.espacioAcompaniante);
        nuevo.put("mochilasDeViaje", this.mochilasDeViaje);
        nuevo.put("parabrisa", this.parabrisa);
        nuevo.put("tipoVehiculo", "Moto");

        return nuevo;
    }
}
