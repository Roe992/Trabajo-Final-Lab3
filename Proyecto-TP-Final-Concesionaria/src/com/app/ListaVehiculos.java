package com.app;

import com.app.I_Listas;
import com.app.Vehiculo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ListaVehiculos implements I_Listas<Vehiculo>, Serializable //Es el HashMap de Vehiculos

{
    private HashMap<String,Vehiculo>hashMap1;

    public ListaVehiculos()
    {
        hashMap1=new HashMap<>();
    }

    public Iterator<Map.Entry<String, Vehiculo>> iterador(){
        Iterator<Map.Entry<String, Vehiculo>> it = hashMap1.entrySet().iterator();
        return it;
    }

    public Vehiculo buscar(String patente)
    {
        Vehiculo aux;
        if(hashMap1.containsKey(patente)) //Si tiene la patente...
        {
            aux = hashMap1.get(patente); //Obtiene el elemento
        }
        else{
            aux = null;
        }
        return aux;
    }

    @Override
    public boolean Agregar(Vehiculo vehiculo)
    {
        if(hashMap1.containsKey(vehiculo.getPatente())==false) //Si no contiene un Vehiculo con esa patente (key), lo agrega
        {
            hashMap1.put(vehiculo.getPatente(),vehiculo);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean Quitar(Vehiculo vehiculo) //Si existe un Vehiculo con esa patente, lo quita
    {
        boolean eliminado=false;
        if(hashMap1.containsKey(vehiculo.getPatente()))
        {
            hashMap1.remove(vehiculo.getPatente());
            eliminado= true;
        }
        else
        {
            eliminado= false;
        }
        return eliminado;

    }

    @Override
    public int Tamanio()
    {
        int aux;
        aux=hashMap1.size();
        return aux;
    }

    @Override
    public String Listar()
    {
        Iterator<Map.Entry<String,Vehiculo>> filas=hashMap1.entrySet().iterator(); //Se obtiene el iterator
        String listado="";
        while (filas.hasNext())
        {
            Map.Entry<String,Vehiculo>unaFila=filas.next();
            listado+=unaFila.getValue().toString() + "\n"; //Se muestra cada elemento separado por línea
        }
        return listado;
    }
}





