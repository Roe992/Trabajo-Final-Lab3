package com.app;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

public class Conjunto<T extends RegistroVenta> implements I_Listas<T>, Serializable {
	
	private HashSet<T> conjunto; //HashSet (Nosotros lo usamos en la lista de Registros de Venta)
	
	public Conjunto(){
		conjunto = new HashSet<>();
	}

	public Iterator<T> iterador(){ //Obtener el iterador
		Iterator<T> it = conjunto.iterator();
		return it;
	}

	@Override
	public boolean Agregar(T nuevoElemento) { //Agregar elemento
		boolean agregado = conjunto.add(nuevoElemento);
		
		return agregado;
	}

	@Override
	public boolean Quitar(T aQuitar) { //Quitar elemento

		boolean quitado=conjunto.remove(aQuitar);

		return quitado;
	}

	@Override
	public String Listar() { //Mostrar los elementos
		String lista = "";
		Iterator<T> it = conjunto.iterator();

		while(it.hasNext()) {
			lista += it.next().toString();
		}
		return lista;
	}

	@Override
	public int Tamanio()
	{
		return conjunto.size();
	} //Retorna el tamaño del HashSet
}
