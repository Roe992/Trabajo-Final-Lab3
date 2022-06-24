package com.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.naming.ldap.Control;
import javax.swing.plaf.IconUIResource;
import java.awt.*;
import java.util.Scanner;
import java.io.*;

public class Main {
    static Scanner teclado;

    public static void main(String[] args) {
        teclado = new Scanner(System.in);

        Concesionaria concesionaria=new Concesionaria("Autodex Ejemplo", 0d, "Calle de Ejemplo 10");

        try {

            concesionaria=ControladorArchivo.leerArchivo(); //Carga la Concesionaria del archivo

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //System.out.println(ControladorJSON.leer("vehiculos").Listar()); //Carga y muestra los vehiculos desde un archivo json

        MenuConcesionaria(concesionaria); //Se inicia el programa

        ControladorJSON.cargar("vehiculos", concesionaria.getInventarioVehiculos()); //Crea un archivo json con los Vehiculos

        try
        {
            ControladorArchivo.guardarArchivos(concesionaria); //Se guarda la Concesionaria en un Archivo
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        teclado.close();
    }

    public static void MenuConcesionaria(Concesionaria concesionaria)
    {
        boolean operando = true;
        int opcion = 0;

        while (operando==true) {

            try{

            System.out.println("Seleccione operacion");
            System.out.println("1: Registrar Cliente");
            System.out.println("2: Registrar Vehiculo"); //Registrar Auto/Registrar Moto
            System.out.println("3: Chequear recaudacion");
            System.out.println("4: Quitar Vehiculo");
            System.out.println("5: Quitar cliente");
            System.out.println("6: Buscar cliente");
            System.out.println("7: Buscar Vehiculo");
            System.out.println("8: Buscar registro de venta");
            System.out.println("9: Editar precio vehiculo");
            System.out.println("10: Ver Lista de Clientes");
            System.out.println("11: Ver Lista de Registros de Venta");
            System.out.println("12: Buscar por filtro");
            System.out.println("15: Terminar la operacion");

            String opcionString=teclado.nextLine();

            if(tieneLetra(opcionString) || opcionString.isEmpty())
            {
                throw new MalCargadoException("\nERROR: Tipo inválido\n");
            }
            else
            {
                opcion=Integer.parseInt(opcionString);

            }

            switch (opcion) {
                case 1: //Registrar Cliente

                    String nombre;
                    String dni;
                    double dinero;
                    String  fechaNacimiento;

                    //Ingreso de datos del Cliente

                    try
                    {

                    //---------------------------- Nombre
                    System.out.println("Ingrese nombre");
                    nombre=teclado.nextLine();
                    //---------------------------- DNI
                    System.out.println("Ingrese DNI");
                    dni=teclado.nextLine(); //Ingresamos el DNI con tipo String
                    if(tieneLetra(dni) || dni.isEmpty()) //Los DNI sólo tienen números así que revisa que no tenga letras y que no esté vacio.
                    {
                        throw new MalCargadoException("\nERROR: Se ingresó una letra/símbolo dentro del DNI\n");
                    }
                    //---------------------------- Dinero
                    System.out.println("Ingrese el Dinero");
                    String dineroString=teclado.nextLine(); //Leemos el precio en formato String

                    dinero=Double.parseDouble(dineroString); //Lo intenta transformar a Double y lanza una NumberFormatException si no tiene el formato de Double

                    //---------------------------- Fecha de Nacimiento
                    System.out.println("Ingrese la Fecha de Nacimiento");
                    fechaNacimiento=teclado.nextLine();

                    boolean clienteRegistrado=concesionaria.registrarCliente(nombre,dni,dinero,fechaNacimiento); //Se crea el Cliente

                        if(clienteRegistrado==true)
                        {
                            System.out.println("Cliente registrado");
                        }else
                        {
                            System.out.println("ya se encuentra un cliente registrado con ese DNI");
                        }
                    }
                    catch(MalCargadoException e){
                        System.out.println(e.getMessage());
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("\nERROR: Se ingresó un carácter no válido");
                    }

                    break;

                case 2: //Registrar Vehiculo
                    int tipoElegido=0;
                    boolean registrandoVehiculo=true;
                    while (registrandoVehiculo) //Mientras se mantenga dentro de este menú...
                    {

                        try {
                            System.out.println("1: Registrar Auto");
                            System.out.println("2: Registrar Moto");
                            System.out.println("3: Volver al menu");

                            String tipoElegidoString = teclado.nextLine();

                            if(tieneLetra(tipoElegidoString) || tipoElegidoString.isEmpty())
                            {
                                throw new MalCargadoException("\nERROR: Tipo inválido\n");
                            }
                            else
                            {
                                tipoElegido=Integer.parseInt(tipoElegidoString);
                            }

                            if (tipoElegido >= 1 && tipoElegido <= 2) //Si eligió registrar algún vehículo (Dentro del rango de opciones)
                            {
                                //Primero se crea la parte de Vehiculo (que es general) y luego el tipo "Especial".

                                //----------------- Vehiculo
                                String patente;
                                String marca;
                                String modelo;
                                int kilometraje;
                                double precio;
                                String color;
                                String anioFabricacion;
                                int caballosDeFuerza;
                                String transmision;
                                String tipos;

                                //--------------------- Ingreso Datos Vehiculo

                                //-------------------- Patente
                                System.out.println("Ingrese patente");
                                patente = teclado.nextLine();
                                //-------------------- Marca
                                System.out.println("Ingrese marca");
                                marca = teclado.nextLine();
                                //-------------------- Modelo
                                System.out.println("Ingrese modelo");
                                modelo = teclado.nextLine();

                                //-------------------- Kilometraje (Cantidad de Kilometros)
                                System.out.println("Ingrese kilometraje");
                                String kilometrajeString=teclado.nextLine();

                                if(tieneLetra(kilometrajeString) || kilometrajeString.isEmpty())
                                {
                                    throw new MalCargadoException("\nERROR: Se ingresó una letra/símbolo dentro del valor\n");
                                }
                                else
                                {
                                    kilometraje=Integer.parseInt(kilometrajeString);
                                }

                                //-------------------- Precio
                                System.out.println("Ingrese precio");
                                String precioString = teclado.nextLine();

                                precio=Double.parseDouble(precioString);

                                //-------------------- Color
                                System.out.println("Ingrese color");
                                color = teclado.nextLine();

                                //-------------------- Año de fabricación
                                System.out.println("Ingrese año de fabricación");
                                anioFabricacion = teclado.nextLine();

                                if(tieneLetra(anioFabricacion) || anioFabricacion.isEmpty())
                                {
                                    throw new MalCargadoException("\nERROR: Se ingresó una letra/símbolo dentro del Año\n");
                                }

                                //-------------------- Caballos de fuerza
                                System.out.println("Ingrese caballos de fuerza");
                                String caballosDeFuerzaString=teclado.nextLine();

                                if(tieneLetra(caballosDeFuerzaString) || caballosDeFuerzaString.isEmpty())
                                {
                                    throw new MalCargadoException("\nERROR: Se ingresó una letra/símbolo dentro del valor\n");
                                }
                                else
                                {
                                    caballosDeFuerza=Integer.parseInt(caballosDeFuerzaString);
                                }

                                //-------------------- Tipo de Transmision
                                System.out.println("Ingrese tipo de transmision");
                                transmision = teclado.nextLine();

                                //-------------------- Tipo de Vehiculo
                                System.out.println("Ingrese tipo de vehiculo (deportivo, sedan etc)");
                                tipos = teclado.nextLine();

                                //--------------------

                                switch (tipoElegido) //Se separa según el tipo de Vehiculo elegido
                                {
                                    case 1: //Auto

                                        //------------------------------ Auto

                                        int puertas;
                                        boolean descapotable;
                                        boolean autonomo;
                                        String baul;
                                        boolean puertasExoticas;
                                        String tipoTraccion;

                                        //------------------------------ Ingreso datos Auto

                                        //-------------------- Cantidad de Puertas
                                        System.out.println("Ingrese la cantidad de puertas");
                                        String puertasString= teclado.nextLine();

                                        if(tieneLetra(puertasString) || puertasString.isEmpty())
                                        {
                                            throw new MalCargadoException("\nERROR: Se ingresó una letra/símbolo dentro del valor\n");
                                        }
                                        else
                                        {
                                            puertas=Integer.parseInt(puertasString);
                                        }

                                        //-------------------- Capacidad Autónoma
                                        System.out.println("Ingrese 'si', si el auto tiene capacidad autonoma");
                                        if (teclado.nextLine().equals("si")) {
                                            autonomo = true;
                                        } else autonomo = false;

                                        //-------------------- Tamaño del baul
                                        System.out.println("Ingrese tamaño del baul (grande pequeño mediano etc)");
                                        baul = teclado.nextLine();

                                        //-------------------- Puertas Exoticas
                                        System.out.println("Ingrese 'si' si el auto tiene puertas exoticas(puertas mariposa/deslizantes etc)");
                                        if (teclado.nextLine().equals("si")) {
                                            puertasExoticas = true;
                                        } else puertasExoticas = false;

                                        //-------------------- Tipo de Traccion
                                        System.out.println("Ingrese tipo traccion (ej 4x4)");
                                        tipoTraccion = teclado.nextLine();

                                        //-------------------- Descapotable
                                        System.out.println("Ingrese 'si', si el auto es descapotable");
                                        if (teclado.nextLine().equals("si")) {
                                            descapotable = true;
                                        } else descapotable = false;
                                        //--------------------

                                        boolean autoRegistrado=concesionaria.registrarAuto(patente, marca, modelo, kilometraje, precio, color, anioFabricacion, caballosDeFuerza, transmision, tipos, puertas,
                                                           descapotable, autonomo, puertasExoticas, baul, tipoTraccion); //Se crea el Auto

                                        if(autoRegistrado)
                                        {
                                            System.out.println("Auto registrado con exito");
                                        }
                                        else
                                        {
                                            System.out.println("Ya se encuentra un Auto registrado con esa patente");
                                        }

                                        break;

                                    case 2: //Moto

                                        //------------------------------ Moto

                                        boolean espacioAcompaniante;
                                        boolean mochilasDeViaje;
                                        boolean parabrisa;

                                        //------------------------------ Ingreso datos Moto

                                        //-------------------- Espacio para Acompañante
                                        System.out.println("Ingrese 'si', si la moto tiene espacio para acompañante");
                                        if (teclado.nextLine().equals("si")) {
                                            espacioAcompaniante = true;
                                        } else espacioAcompaniante = false;

                                        //-------------------- Mochilas de viaje
                                        System.out.println("Ingrese 'si' si la moto tiene mochilas de viaje");
                                        if (teclado.nextLine().equals("si")) {
                                            mochilasDeViaje = true;
                                        } else mochilasDeViaje = false;

                                        //-------------------- Parabrisa
                                        System.out.println("Ingrese 'si', si la moto tiene parabrisa");
                                        if (teclado.nextLine().equals("si")) {
                                            parabrisa = true;
                                        } else parabrisa = false;

                                        //--------------------

                                        boolean motoRegistrada=concesionaria.registrarMoto(patente, marca, modelo, kilometraje, precio, color, anioFabricacion, caballosDeFuerza, transmision, tipos, espacioAcompaniante,
                                                                                       mochilasDeViaje, parabrisa); //Se crea la Moto

                                        if(motoRegistrada)
                                        {
                                            System.out.println("Moto registrada con exito");
                                        }
                                        else
                                        {
                                            System.out.println("Ya se encuentra una Moto registrada con esa patente");
                                        }

                                        break;
                                }
                            } else {

                                if(tipoElegido>3)
                                {
                                    //Opción fuera de rango
                                    System.out.println("\nERROR: Opción inválida\n");
                                    teclado.nextLine();
                                }
                                registrandoVehiculo = false;
                            }
                        }catch(MalCargadoException e){
                                System.out.println(e.getMessage());
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("\nERROR: Se ingresó un carácter no válido");
                        }
                    }

                    break;

                case 3: //Chequear Recaudacion
                    System.out.println("Su recaudación es de: $" + concesionaria.chequearRecaudacion() + "\n");
                    break;

                case 4: //Quitar vehiculo

                    System.out.println("Ingrese patente del Vehículo a eliminar: ");
                    String patenteAEliminar=teclado.nextLine();

                    if(concesionaria.quitarVehiculo(patenteAEliminar))
                    {
                        System.out.println("- Vehiculo eliminado correctamente -\n");
                    }
                    else
                    {
                        System.out.println("- No existe un Vehiculo con la patente ingresada -\n");
                    }

                    break;

                case 5: //Quitar Cliente

                    System.out.println("Ingrese el DNI del cliente a eliminar: ");
                    String dniAEliminar=teclado.nextLine();

                    if(concesionaria.quitarCliente(dniAEliminar))
                    {
                        System.out.println("- Cliente eliminado correctamente -\n");
                    }
                    else
                    {
                        System.out.println("- Cliente no encontrado -\n");
                    }

                    break;

                case 6: //Buscar Cliente

                    System.out.println("Ingrese el DNI del cliente a buscar: ");
                    String dniABuscar= teclado.nextLine();

                    Cliente clienteBuscado=concesionaria.buscarCliente(dniABuscar);

                    if(clienteBuscado!=null)
                    {
                        System.out.println(clienteBuscado.toString());
                    }
                    else
                    {
                        System.out.println("- Cliente no encontrado -\n");
                    }

                    break;

                case 7: //Buscar Vehiculo

                    System.out.println("Ingrese la patente del Vehiculo buscado: ");
                    String patenteABuscar=teclado.nextLine();

                    Vehiculo vehiculoBuscado=concesionaria.buscarVehiculo(patenteABuscar);

                    if(vehiculoBuscado!=null)
                    {
                        System.out.println(vehiculoBuscado.toString());
                    }
                    else
                    {
                        System.out.println("- Vehiculo no encontrado -\n");
                    }

                    break;

                case 8: //Buscar Registro de Venta

                    System.out.println("Ingrese el Numero de Transaccion de la venta: ");
                    int numeroTransaccion=teclado.nextInt();
                    teclado.nextLine(); //Limpia el buffer

                    RegistroVenta registroVentaBuscada=concesionaria.buscarRegistroVenta(numeroTransaccion);

                    if(registroVentaBuscada!=null)
                    {
                        System.out.println(registroVentaBuscada.toString());
                    }
                    else
                    {
                        System.out.println("- Registro de Venta no encontrado -\n");
                    }

                    break;

                case 9: //Editar Precio Vehiculo

                    try {

                        System.out.println("Ingrese patente del Vehiculo a editar: ");
                        String patenteAEditar = teclado.nextLine();

                        Vehiculo vehiculoAEditar = concesionaria.buscarVehiculo(patenteAEditar);

                        if (vehiculoAEditar != null) {
                            System.out.println(vehiculoAEditar.toString());
                            System.out.println("Ingrese el nuevo Precio del Vehiculo (Precio Actual: $" + vehiculoAEditar.getPrecio() + "): ");
                            Double nuevoPrecio = teclado.nextDouble();
                            teclado.nextLine(); //Limpia el buffer

                            concesionaria.editarPrecio(patenteAEditar, nuevoPrecio); //Se edita el precio

                            System.out.println("- Precio Editado correctamente -");
                        } else {
                            System.out.println("- Vehiculo no encontrado -\n");
                        }

                    }catch (NumberFormatException e)
                    {
                        System.out.println("\nERROR: Se ingresó un carácter no válido");
                    }

                    break;

                case 10: //Mostrar Registro Clientes

                    System.out.println(concesionaria.devolverRegistroClientes());

                    break;

                case 11: //Mostrar Historial Ventas

                    System.out.println(concesionaria.devolverHistorialVentas());

                    break;

                case 12: //Busqueda por Filtros
                    boolean estaFiltrando=true;
                    int autoOmoto=0;
                    int opcionTags=0;

                    try
                    {
                    System.out.println("Seleccione si buscar Auto o Moto");
                    System.out.println("1: Buscar Auto");
                    System.out.println("2: Buscar Moto");

                    String autoOmotoString=teclado.nextLine(); //Se elige si filtrar por Autos o Motos

                        if(tieneLetra(String.valueOf(autoOmoto)) || autoOmotoString.isEmpty())
                        {
                            throw new MalCargadoException("\nERROR: Se ingresó una letra/símbolo dentro del valor\n");
                        }
                        else
                        {
                            autoOmoto=Integer.parseInt(autoOmotoString);
                        }

                    concesionaria.separarAutoMoto(autoOmoto);
                        int limiteKilometraje=999999999;
                        double precioMaximo=999999999; //Precio inalcanzable por defecto...

                    while (estaFiltrando==true) //Mientras este filtrando...
                    {
                        System.out.println(concesionaria.devolverStringListaFiltrada()); //Muestra los Vehiculos filtrados

                        System.out.println("Filtros: " + concesionaria.devolverTagsUsuario() + "\n"); //Muestra los tags del usuario

                        if(precioMaximo!=999999999){
                            System.out.println("Limite Precio Maximo: $" +precioMaximo+"\n"); //No muestra el precio si el usuario no lo editó
                        }

                        if(limiteKilometraje!=999999999){
                            System.out.println("Limite Kilometraje: " +limiteKilometraje+" km\n");
                        }

                        try
                        {
                        System.out.println("1: Agregar Tags");
                        System.out.println("2: Quitar Tags");
                        System.out.println("3: Limpiar Tags");
                        System.out.println("4: Filtrar Precio");
                        System.out.println("5: Filtrar Kilometros");
                        System.out.println("6: Vender");
                        System.out.println("7: Volver al menu anterior");

                        Lista<String> generadorTags;


                        System.out.println("Ingrese la opcion a ejecutar");

                        String opcionTagsString=teclado.nextLine();

                            if(tieneLetra(opcionTagsString) || opcionTagsString.isEmpty())
                            {
                                throw new MalCargadoException("\nERROR: Se ingresó una letra/símbolo dentro del valor\n");
                            }
                            else
                            {
                                opcionTags=Integer.parseInt(opcionTagsString);
                            }

                        switch (opcionTags) { //Switch con cada opcion del manejo de Filtros

                            case 1: //Agregar Tags

                                //Obtiene el menu con los tags de Auto o Moto dependiendo de qué se eligió
                                if (autoOmoto == 1) {
                                    System.out.println(Auto.menuTags());
                                    generadorTags = Auto.generarTags();
                                } else {
                                    System.out.println(Moto.menuTags());
                                    generadorTags = Moto.generarTags();
                                }

                                try{
                                System.out.println("Ingrese el Numero del Tag a Agregar"); //Se elige el tipo de tag a Agregar
                                String indexTagString=teclado.nextLine();

                                int indexTag;

                                    if(tieneLetra(indexTagString) || indexTagString.isEmpty())
                                    {
                                        throw new MalCargadoException("\nERROR: Se ingresó una letra/símbolo dentro del valor\n");
                                    }
                                    else
                                    {
                                        indexTag=Integer.parseInt(indexTagString);
                                    }

                                    if (indexTag >= 1 && indexTag <= generadorTags.Tamanio()) { //Siempre y cuando esté dentro del rango de tipos...

                                    String tagElegido = generadorTags.obtenerElemento(indexTag - 1); //Se obtiene la primer parte del tag (color, marca, etc...)

                                    //"color "   "puertas "

                                    String valor = ""; //Este es el valor que se le va a agregar al tag a crear

                                    if (tagElegido.contains("false")) { //Si el tag es un boolean...
                                        tagElegido = tagElegido.replace("false", "");
                                        valor = "true"; //valor es true
                                    } else { //Si NO es un boolean
                                        System.out.println("Ingrese el valor del Tag"); //Se ingresa un valor por teclado
                                        valor = teclado.nextLine();
                                    }

                                    String formateado = tagElegido + valor; //Se arma el tag con tipo + valor

                                    //"color rojo"

                                        try
                                        {
                                            //Se agrega el String formateado
                                            concesionaria.agregarTagsUsuario(formateado, tagElegido);
                                            //Si ya existe un tag de ese tipo filtrado lanza un "TagRepetidoException".

                                            concesionaria.agregarTagsEnListaFiltrada();
                                        }catch (TagRepetidoException l)
                                        {
                                            System.out.println(l.getMessage()+ tagElegido);
                                        }


                                            System.out.println("");

                                }
                                else
                                {
                                    //Opción fuera de rango

                                    //clear
                                    System.out.println("\nERROR: Opción inválida\n");
                                    teclado.nextLine();
                                    //clear
                                }

                                }catch (MalCargadoException e)
                                {
                                    e.getMessage();
                                }

                                break;

                                    case 2://quitar tags

                                        try
                                        {

                                        System.out.println("Seleccione tag a eliminar:");
                                        int posAeliminar;
                                        System.out.println(concesionaria.listarTagsUsuario());

                                        String posAeliminarString=teclado.nextLine();

                                            if(tieneLetra(posAeliminarString) || posAeliminarString.isEmpty())
                                            {
                                                throw new MalCargadoException("\nERROR: Se ingresó una letra/símbolo dentro del valor\n");
                                            }
                                            else
                                            {
                                                posAeliminar=Integer.parseInt(posAeliminarString);
                                            }

                                        if(posAeliminar>=1 && posAeliminar<=concesionaria.getTagsUsuario().Tamanio())
                                        {

                                        concesionaria.quitarTagUsuario(posAeliminar - 1);

                                        concesionaria.quitarTagsEnListaFiltrada(autoOmoto); //Actualizar la Lista Filtrada (1 si Autos o 2 si Motos).

                                        }
                                        else
                                        {
                                            //Opción fuera de rango

                                            //clear
                                            System.out.println("\nERROR: Opción inválida\n");
                                            teclado.nextLine();
                                            //clear
                                        }

                                        }catch (MalCargadoException e)
                                        {
                                            e.getMessage();
                                        }
                                        break;

                                    case 3:///limpiar tags
                                        concesionaria.limpiarListaTags();
                                        concesionaria.quitarTagsEnListaFiltrada(autoOmoto); //Actualizar la Lista Filtrada (1 si Autos o 2 si Motos).
                                        concesionaria.filtrarPorKilometraje(limiteKilometraje);
                                        concesionaria.filtrarPrecioMaximo(precioMaximo);
                                        break;

                                    case 4://Filtrar Precio

                                        boolean filtrandoPrecio=true;

                                        while (filtrandoPrecio==true)
                                        {

                                        System.out.println("1) Establecer Precio Máximo");
                                        System.out.println("2) Reiniciar Precio Máximo");
                                        System.out.println("3) Volver al Menu");

                                        int opcionPrecio;

                                        String opcionPrecioString=teclado.nextLine();

                                        try {

                                            if(tieneLetra(opcionPrecioString) || opcionPrecioString.isEmpty())
                                            {
                                                throw new MalCargadoException("\nERROR: Se ingresó una letra/símbolo dentro del valor\n");
                                            }
                                            else
                                            {
                                                opcionPrecio=Integer.parseInt(opcionPrecioString);
                                            }

                                            if(opcionPrecio>=1 && opcionPrecio<=3)
                                            {

                                            switch (opcionPrecio)
                                            {
                                                case 1: //Establecer Precio Maximo
                                                    System.out.println("Ingrese el Precio Máximo a filtrar:");
                                                    String precioMaximoString=teclado.nextLine();

                                                    try
                                                    {
                                                        precioMaximo=Double.parseDouble(precioMaximoString);

                                                        concesionaria.filtrarPrecioMaximo(precioMaximo);

                                                    }catch (NumberFormatException e)
                                                    {
                                                        System.out.println("\nERROR: Se ingresó un carácter no válido\n");
                                                    }
                                                    break;

                                                case 2: // Reiniciar Precio Maximo

                                                    concesionaria.separarAutoMoto(autoOmoto);
                                                    concesionaria.agregarTagsEnListaFiltrada();
                                                    concesionaria.filtrarPorKilometraje(limiteKilometraje);
                                                    precioMaximo=999999999;
                                                    System.out.println("\n - Se reinició la limitación de Precio -\n");

                                                    break;

                                                case 3: //Volver al Menu
                                                    filtrandoPrecio=false;
                                                    break;
                                            }

                                            }
                                            else
                                            {
                                                //Opción fuera de rango

                                                System.out.println("\nERROR: Opción inválida\n");
                                                teclado.nextLine();
                                            }

                                        }catch (MalCargadoException e)
                                            {
                                                e.getMessage();
                                            }
                                        }

                                        break;

                                    case 5: //Filtrar Kilometros
                                        boolean filtrandoKilometros=true;
                                        int opcionKilometraje=0;

                                        while(filtrandoKilometros)
                                        {

                                        System.out.println("1: Establecer kilometraje Máximo");
                                        System.out.println("2: Reiniciar kilometraje Máximo");
                                        System.out.println("3: Volver al Menu anterior");

                                        String opcionKilometrajeString=teclado.nextLine();

                                        if(tieneLetra(opcionKilometrajeString) || opcionKilometrajeString.isEmpty())
                                        {
                                            throw new MalCargadoException("\nERROR: Se ingresó un carácter no válido\n");
                                        }
                                        else
                                        {
                                            opcionKilometraje=Integer.parseInt(opcionKilometrajeString);
                                        }

                                        if(opcionKilometraje>=1 && opcionKilometraje<=3)
                                        {

                                        switch (opcionKilometraje){
                                            case 1:
                                                System.out.println("Ingrese kilometraje Máximo (ingrese 0 para un vehiculo nuevo)");
                                                String limiteKilometrajeString=teclado.nextLine();

                                                if(tieneLetra(limiteKilometrajeString) || limiteKilometrajeString.isEmpty())
                                                {
                                                    throw new MalCargadoException("\nERROR: Se ingresó un carácter no válido\n");
                                                }
                                                else
                                                {
                                                    limiteKilometraje=Integer.parseInt(limiteKilometrajeString);
                                                }

                                                concesionaria.filtrarPorKilometraje(limiteKilometraje);

                                                break;

                                            case 2:
                                                concesionaria.separarAutoMoto(autoOmoto);
                                                concesionaria.agregarTagsEnListaFiltrada();
                                                concesionaria.filtrarPrecioMaximo(precioMaximo);
                                                limiteKilometraje=999999999;

                                                System.out.println("\n- Se reinició el límite del kilometraje -\n");

                                                break;
                                            case 3:
                                                filtrandoKilometros=false;
                                                break;
                                        }

                                        }else
                                        {

                                            System.out.println("\nERROR: Opción inválida\n");
                                            teclado.nextLine();

                                        }

                                        }
                                        break;

                                    case 6: //Vender

                                        try
                                        {

                                        System.out.println("Ingrese DNI del comprador");
                                        String dniComprador = teclado.nextLine();

                                        if(concesionaria.buscarCliente(dniComprador)==null)
                                        {
                                            throw new Exception("- Comprador no Encontrado -");
                                        }

                                        System.out.println("Ingrese patente del vehiculo a vender");
                                        String patenteComprado = teclado.nextLine();

                                            if(concesionaria.buscarVehiculo(patenteComprado)==null)
                                            {
                                                throw new Exception("- Vehiculo no Encontrado -");
                                            }

                                        System.out.println("Ingrese medio de pago");
                                        String metodoPago = teclado.nextLine();

                                            if (concesionaria.comprobarPresupuestoCliente(dniComprador, patenteComprado)) {

                                                concesionaria.vender(dniComprador, patenteComprado, metodoPago);

                                                System.out.println("- Se completó la venta exitosamente -");

                                            } else {
                                                    throw new Exception("- Presupuesto del Cliente Insuficiente -");
                                            }

                                        }catch (Exception e)
                                        {
                                            System.out.println(e.getMessage());
                                        }
                                    break;

                                    case 7: //Volver al Menú
                                        estaFiltrando = false;
                                        concesionaria.limpiarListas();

                                        break;
                        }
                        }catch (MalCargadoException e)
                        {
                            e.getMessage();
                        }
                    }
                    }catch (MalCargadoException e)
                    {
                        e.getMessage();
                    }

                    break;

                case 15: //Volver
                    operando=false;
                break;
            }

            }catch (MalCargadoException e)
            {
                System.out.println(e.getMessage());
            }
        }

    }

    public static boolean tieneLetra(String s) {
        boolean contieneLetra = false;

        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) { //Si NO es un dígito (un número)
                    contieneLetra=true; //contieneLetra = true
                    break; //corta el bucle
                }
            }
        }

        return contieneLetra;
    }

}



