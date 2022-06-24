package com.app;

public interface I_Listas <T>{ //La comparten el ArrayList, el HashSet y el HashMap

    boolean Agregar(T nuevoElemento);

    boolean Quitar(T aQuitar);

    String Listar();

    int Tamanio();

}
