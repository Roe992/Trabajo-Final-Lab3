package com.app;

import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ControladorJSON {
	
	public static ListaVehiculos leer(String archivo) { //Obtiene la lista de vehiculos y la retorna
		ListaVehiculos vehiculos = new ListaVehiculos();
		
		String contenido = JsonUtiles.leer(archivo);
		
		try {
			JSONArray jsonArray = new JSONArray(contenido);
			
			for(int i = 0; i<jsonArray.length(); i++) {
				
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String tipoVehiculo=jsonObject.getString("tipoVehiculo");

				if(tipoVehiculo.equals("Auto"))
				{
					Vehiculo auto = new Auto();
					auto.pasarDeJSON(jsonObject);
					vehiculos.Agregar(auto);
				}
				else if(tipoVehiculo.equals("Moto"))
				{
					Vehiculo moto = new Moto();
					moto.pasarDeJSON(jsonObject);
					vehiculos.Agregar(moto);
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vehiculos;
	}
	
	public static void cargar(String archivo, ListaVehiculos vehiculos) {
		Iterator<Map.Entry<String, Vehiculo>> it = vehiculos.iterador();
		JSONArray arrayVehiculos = new JSONArray();
		
		while(it.hasNext()) { //Transforma cada vehiculo del hashMap a un Json y lo carga
			Map.Entry<String, Vehiculo> fila = it.next();
			
			JSONObject jsonObject = new JSONObject();
			
			if(fila.getValue() instanceof Auto) {
				
				try {
					
					jsonObject = fila.getValue().pasarAJSON();
					arrayVehiculos.put(jsonObject);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(fila.getValue() instanceof Moto) {
				
				try {
					
					jsonObject = fila.getValue().pasarAJSON();
					arrayVehiculos.put(jsonObject);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		JsonUtiles.grabar(arrayVehiculos, archivo);
	}
}
