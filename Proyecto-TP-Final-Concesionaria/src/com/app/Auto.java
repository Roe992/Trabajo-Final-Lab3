package com.app;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Auto extends Vehiculo implements Serializable {
    private int puertas;
    private boolean descapotable;
    private boolean autonomo;
    private String baul;
    private boolean puertasExoticas;
    private String tipoTraccion;

    public Auto() {
        super();
    }


    public Auto(String patente,String marca, String modelo, int kilometraje, double precio, String color, String anioFabricacion, int caballosDeFuerza, String transmision, String tipos,int puertas,boolean descapotable, boolean autonomo, boolean puertasExoticas, String baul,String tipoTraccion)
    {
        super(patente,marca,modelo,kilometraje,precio,color,anioFabricacion,caballosDeFuerza,transmision,tipos);
        this.puertas=puertas;
        this.descapotable=descapotable;
        this.autonomo=autonomo;
        this.baul=baul;
        this.puertasExoticas=puertasExoticas;
        this.tipoTraccion=tipoTraccion;
        llenarTags(); //Genera los tags con las variables del Vehiculo
    }

    static public Lista<String> generarTags()
    {
        Lista<String> nuevoArray=Vehiculo.generarTags();
        nuevoArray.Agregar("puertas ");
        nuevoArray.Agregar("descapotable false");
        nuevoArray.Agregar("autonomo false"); //autonomo true;   (Le podemos poner el true directamente y saltear el input si se trata de un boolean)
        nuevoArray.Agregar("baul ");
        nuevoArray.Agregar("puertasExoticas false");
        nuevoArray.Agregar("tipoTraccion ");

        return nuevoArray;
    }

    static public String menuTags()
    {
            String menuTags = Vehiculo.menuTags() + "9) Cantidad de puertas \n10) Descapotable \n11) Autonomo \n12) Tamaño baul \n13) Puertas exoticas \n14) Tipo de traccion \n";

        return menuTags;
    }

    @Override
    public void llenarTags()
    {
        super.llenarTags(); //llena los tags de Vehiculo
        super.getListaTags().Agregar("puertas " + puertas);
        super.getListaTags().Agregar("descapotable "+ descapotable);
        super.getListaTags().Agregar("autonomo "+ autonomo);
        super.getListaTags().Agregar("baul "+ baul);
        super.getListaTags().Agregar("puertasExoticas "+ puertasExoticas);
        super.getListaTags().Agregar("tipoTraccion "+ tipoTraccion);
    }

    @Override
    public String toString() {
        return super.toString()+
                ", puertas='" + puertas +'\'' +
                ", descapotable=" + descapotable +
                ", autonomo=" + autonomo +
                ", baul='" + baul +'\'' +
                ", puertasExoticas=" + puertasExoticas +
                ", tipoTraccion='" + tipoTraccion + '\'';
    }

    @Override
    public void pasarDeJSON(JSONObject nuevo) throws JSONException {
        super.pasarDeJSON(nuevo);
        this.autonomo = nuevo.getBoolean("autonomo");
        this.baul = nuevo.getString("baul");
        this.descapotable = nuevo.getBoolean("descapotable");
        this.puertas = nuevo.getInt("puertas");
        this.puertasExoticas = nuevo.getBoolean("puertasExoticas");
        this.tipoTraccion = nuevo.getString("tipoTraccion");
    }

    @Override
    public JSONObject pasarAJSON() throws JSONException {
        JSONObject nuevo = new JSONObject();
        nuevo = super.pasarAJSON();
        nuevo.put("autonomo", this.autonomo);
        nuevo.put("baul", this.baul);
        nuevo.put("descapotable", this.descapotable);
        nuevo.put("puertas", this.puertas);
        nuevo.put("puertasExoticas", this.puertasExoticas);
        nuevo.put("tipoTraccion", this.tipoTraccion);
        nuevo.put("tipoVehiculo", "Auto"); //Se agrega para poder diferenciarlos a la hora de leer el Json

        return nuevo;
    }

}
