package com.app;

import java.io.Serializable;
import java.util.ArrayList;

public class Lista<T> implements I_Listas<T>, Serializable {
    /*Implementa la interfaz de Listas (agregar, buscar, quitar, listar) y utiliza una variable genérica que extienda de Comparable para que se pueda realizar
    la búsqueda de un elemento (comparándolos)*/

    /*Es una Clase que permite crear un ArrayList de cualquier tipo Comparable en la cual los métodos del ArrayList están encapsulados en otros métodos*/

    ArrayList<T> listaElementos;

    public Lista()
    {
        listaElementos=new ArrayList<>();
    }

    public T obtenerElemento(int posicion) //Obtiene un elemento según la posición del índice del array (Devuelve null si la posición pedida es inválida).
    {
        T elemento=null;

        if(posicion>=0 && posicion<listaElementos.size()) //Revisa que sea un índice válido...
        {
            elemento= listaElementos.get(posicion); //obtiene el elemento
        }

        return elemento;
    }

    public boolean Contiene(T buscado) //Un contains
    {
        boolean encontrado=listaElementos.contains(buscado);

        return encontrado;
    }

    public void VaciarLista() //Vacia la lista
    {
        listaElementos.clear();
    }

    @Override
    public boolean Agregar(T nuevoElemento) { //Agrega un nuevo elemento al ArrayList y retorna si puedo agregarlo o no.

        boolean agregado=listaElementos.add(nuevoElemento);

        return agregado;
    }

    @Override
    public boolean Quitar(T aQuitar) { //Busca el elemento en el ArrayList y si lo encuentra, lo elimina.

        boolean quitado=listaElementos.remove(aQuitar);

        return quitado;
    }

    @Override
    public String Listar() { //Devuelve un String donde se encuentran listados todos los Elementos del ArrayList.

        StringBuilder listado=new StringBuilder("");

        for(int i=0; i<listaElementos.size(); i++)
        {
            listado.append( (i + 1) +") " + listaElementos.get(i).toString() + "\n"); //Por cada vehiculo retorna: 1) Vehiculo - Datos
        }

        return listado.toString();
    }

    @Override
    public int Tamanio()
    {
        return listaElementos.size();
    }

}
