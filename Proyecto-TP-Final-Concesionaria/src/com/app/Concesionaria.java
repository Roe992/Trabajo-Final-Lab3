package com.app;

import org.w3c.dom.ls.LSException;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.io.*;


public class Concesionaria implements Serializable{

    private String nombre;
    private Double caja;
    private String direccion;

    private ListaVehiculos inventarioVehiculos;

    private Lista<Cliente> registroClientes;
    private Conjunto<RegistroVenta> historialVentas;

    private Lista<String> tagsUsuario;

    private Lista<Vehiculo> listaFiltrada;
    private int ventasEfectuadas;

    public Concesionaria(String nombre, Double caja, String direccion) {
        this.nombre = nombre;
        this.caja = caja;
        this.direccion = direccion;
        this.inventarioVehiculos = new ListaVehiculos();
        this.registroClientes = new Lista<>();
        this.historialVentas = new Conjunto<>();
        this.tagsUsuario = new Lista<String>();
        this.listaFiltrada = new Lista<Vehiculo>();
        this.ventasEfectuadas=0;
    }

    public void limpiarListas()
    {
        tagsUsuario.VaciarLista();
        listaFiltrada.VaciarLista();
    }

    public ListaVehiculos getInventarioVehiculos()
    {
        return inventarioVehiculos;
    }

    public Lista<String> getTagsUsuario() {
        return tagsUsuario;
    }

    public boolean agregarTagsUsuario(String nuevoTag, String tagElegido) throws TagRepetidoException {

        int i=0;

        boolean agregado=false;

        //Revisa en cada tag que la primer parte (el tipo) no se repita. //Se fija si cada tag NO contiene la primer parte del tipo elegido (tagElegido)
        while(i<tagsUsuario.Tamanio() && tagsUsuario.obtenerElemento(i).contains(tagElegido)==false)
        {
            i++;
        }

        if(i==tagsUsuario.Tamanio())
        {
            agregado=tagsUsuario.Agregar(nuevoTag);
        }
        else
        {
            throw new TagRepetidoException("ERROR",tagElegido); //Lanza una Excepción si encuentra un tag del mismo tipo que el que se quiere agregar
        }

        return agregado;
    }

    public double chequearRecaudacion() {
        return caja;
    }

    //------------------------------------------------------------------------------------------------------ REGISTROS

    public boolean registrarAuto(String patente, String marca, String modelo, int kilometraje, double precio, String color, String anioFabricacion,
                                 int caballosDeFuerza, String transmision, String tipos, int puertas, boolean descapotable, boolean autonomo,
                                 boolean puertasExoticas, String baul, String tipoTraccion) {
        Auto nuevoAuto = new Auto(patente, marca, modelo, kilometraje, precio, color, anioFabricacion, caballosDeFuerza, transmision, tipos, puertas,
                descapotable, autonomo, puertasExoticas, baul, tipoTraccion);

        boolean agregado = inventarioVehiculos.Agregar(nuevoAuto);

        return agregado;
    }

    public boolean registrarMoto(String patente, String marca, String modelo, int kilometraje, double precio, String color, String anioFabricacion,
                                 int caballosDeFuerza, String transmision, String tipos, boolean espacioAcompaniante, boolean mochilasDeViaje, boolean parabrisa) {
        Moto nuevaMoto = new Moto(patente, marca, modelo, kilometraje, precio, color, anioFabricacion, caballosDeFuerza, transmision, tipos, espacioAcompaniante,
                mochilasDeViaje, parabrisa);

        boolean agregado = inventarioVehiculos.Agregar(nuevaMoto);

        return agregado;
    }

    public boolean registrarCliente(String nombre, String dni, Double dinero, String fechaNacimiento) {

        boolean agregado=false;

        Cliente nuevoCliente = new Cliente(nombre, dni, dinero, fechaNacimiento);

            if(buscarCliente(dni)==null) //Revisa que no exista ningún cliente con ese DNI
            {
                agregado = registroClientes.Agregar(nuevoCliente);
            }

        return agregado;
    }

    public boolean agregarRegistroVenta(String DNI, String nombre, double precio, String medioPago, String patente,
                                        String marca, String modelo, int numeroTransaccion) {
        RegistroVenta nuevaVenta = new RegistroVenta(DNI, nombre, precio, medioPago, patente, marca,
                modelo, numeroTransaccion);
        boolean agregado = historialVentas.Agregar(nuevaVenta);

        return agregado;
    }

    //------------------------------------------------------------------------------------------------------ QUITAR

    public boolean quitarVehiculo(String patente) {
        Vehiculo buscado = inventarioVehiculos.buscar(patente);
        boolean quitado = false;

        if (buscado != null) { //Revisa que exista un vehículo con esa patente
            quitado = inventarioVehiculos.Quitar(buscado);
        }

        return quitado;
    }

    public boolean quitarCliente(String dni) {
        Cliente buscado = buscarCliente(dni); //Busca y quita al Cliente con el dni recibidos

        boolean quitado = false;

        if (buscado != null) {
            quitado = registroClientes.Quitar(buscado);
        }

        return quitado;
    }

    public void quitarTagUsuario(int posicion) { //Quita un tag por posición
        String aQuitar = tagsUsuario.obtenerElemento(posicion);

        tagsUsuario.Quitar(aQuitar);
    }

    public String devolverTagsUsuario() { //Retorna una lista con todos los tags del usuario separados por |

        StringBuilder listaTags=new StringBuilder("");

        listaTags.append("|");

        for(int i=0; i<tagsUsuario.Tamanio(); i++)
        {
            listaTags.append(tagsUsuario.obtenerElemento(i) + "|");
        }

        return listaTags.toString();

    }

    public String listarTagsUsuario()
    {
        return tagsUsuario.Listar();
    }

    //---------------------------------------------------------------------------------------------------- BUSQUEDAS

    public Cliente buscarCliente(String dni) {
        int i = 0;

        Cliente buscado = null;

        //Busca un cliente por su dni
        while (i < registroClientes.Tamanio() && registroClientes.obtenerElemento(i).getDni().equals(dni)==false) {
            i++;
        }

        //Si cortó ántes es porque lo encontró...
        if (i < registroClientes.Tamanio()) {
            buscado = registroClientes.obtenerElemento(i);
        }

        //retorna el Cliente o null si no lo encuentra
        return buscado;
    }

    public RegistroVenta buscarRegistroVenta(int numeroTransaccion) {
        RegistroVenta buscado = null;
        Iterator<RegistroVenta> it = historialVentas.iterador();

        while (it.hasNext()) {
            if (it.next().getNumeroTransaccion() == numeroTransaccion) { //Si lo encuentra, guarda el Registro y lo Retorna
                buscado = it.next();
            }
        }
        return buscado;
    }

    public Vehiculo buscarVehiculo(String patente) {
        Vehiculo buscado = inventarioVehiculos.buscar(patente);

        return buscado;
    }

    //----------------------------------------------------------------------------------------------------- VARIOS

    public boolean editarPrecio(String patente, double nuevoPrecio) {
        Vehiculo buscado = inventarioVehiculos.buscar(patente); //Busca al vehiculo

        boolean editado = false;

        if (buscado != null) { //Si lo encuentra, le edita el precio
            buscado.setPrecio(nuevoPrecio);
            editado = true;
        }

        return editado;
    }

    //-------------------------------------------------------------------------------------------------------

    public void separarAutoMoto(int tipo) //Llena la lista Filtrada de sólo Autos (1) o sólo Motos (2), dependiendo del parámetro que le llegue.
    {
        listaFiltrada.VaciarLista(); //Vacia la lista de Vehiculos FIltrados. (Por si ya se filtró ántes)

        Iterator<Map.Entry<String, Vehiculo>> filas = inventarioVehiculos.iterador(); //Obtiene el Iterador del HashMap de Vehiculos

        while (filas.hasNext()) //Por cada elemento del HashMap.
        {
            Map.Entry<String, Vehiculo> unaFila = filas.next(); //Obtiene una fila (clave/valor) (patente/Vehiculo)

            if (unaFila.getValue() instanceof Auto && tipo == 1) //Si el Vehiculo es un Auto y el usuario quiere Autos(1)...
            {
                listaFiltrada.Agregar(unaFila.getValue()); //Agrega el Auto a la listaFiltrada.
            } else //Motos
            {
                if (unaFila.getValue() instanceof Moto && tipo == 2) //Si el Vehiculo es una Moto y el usuario quiere Motos(2)...
                {
                    listaFiltrada.Agregar(unaFila.getValue()); //Agrega la Moto a la listaFiltrada.
                }
            }
        }

    }

    public void agregarTagsEnListaFiltrada() {
        int i=0;

        while (i < listaFiltrada.Tamanio()) { //Por cada Elemento de Lista Filtrada (Por cada Vehiculo de los Filtrados)...

            int a = 0;

            //Recorremos los tags del usuario y chequeamos si el vehiculo los tiene a todos.
            while (a < tagsUsuario.Tamanio() && listaFiltrada.obtenerElemento(i).getListaTags().Contiene(tagsUsuario.obtenerElemento(a))) {
                a++;
            }

            //Si se corta ántes de terminar (porque no tiene alguno)...
            if (a < tagsUsuario.Tamanio()) {
                listaFiltrada.Quitar(listaFiltrada.obtenerElemento(i)); //Saca el Vehiculo de la Lista
            }
            else
            {
                i++;
            }
        }
    }

    public void filtrarPrecioMaximo(double precioMaximo) //Quita todos los Vehiculos que valgan más que el parámetro recibido
    {
        int i=0;

        while(i<listaFiltrada.Tamanio()) //Por cada Vehiculo de la Lista Filtrada
        {
            if(listaFiltrada.obtenerElemento(i).getPrecio()>precioMaximo) //Si vale más que el parámetro...
            {
                listaFiltrada.Quitar(listaFiltrada.obtenerElemento(i)); //Quita el Vehículo
            }
            else
            {
                i++;
            }
        }
    }

    public void filtrarPorKilometraje(int kilometrajeMaximo) //Quita todos los Vehiculos que tengan más Kilometros que el parámetro recibido
    {
        int i=0;

        while (i<listaFiltrada.Tamanio()) //Por cada Vehiculo de la Lista Filtrada
        {
            if(listaFiltrada.obtenerElemento(i).getKilometraje()>kilometrajeMaximo) //Si tiene más kilometros que el parámetro...
            {
                listaFiltrada.Quitar(listaFiltrada.obtenerElemento(i)); //Quita el Vehículo
            }
            else
            {
                i++;
            }
        }

    }









    public void quitarTagsEnListaFiltrada(int tipo) { //Se rearma la ListaFiltrada
        listaFiltrada.VaciarLista();
        separarAutoMoto(tipo);
        agregarTagsEnListaFiltrada();
    }


    public String devolverStringListaFiltrada() {
        return listaFiltrada.Listar();
    }

    public String devolverHistorialVentas() {
        return historialVentas.Listar();
    }

    public String devolverRegistroClientes() {
        return registroClientes.Listar();
    }

    public void limpiarListaTags() {
        tagsUsuario.VaciarLista();
    }


    public void vender(String dni, String patente, String medioPago) {
        Cliente comprador = buscarCliente(dni); //Busca al comprador (Cliente)

        Vehiculo vendido = inventarioVehiculos.buscar(patente); //Busca el vehiculo a vender


        comprador.setDinero(comprador.getDinero() - vendido.getPrecio()); //Se le resta el dinero al Cliente
        caja += vendido.getPrecio(); //Se aumenta el dinero en la caja


        quitarVehiculo(patente); //Se elimina el vehiculo
        tagsUsuario.VaciarLista(); //Se reinicia tanto la lista de filtros como la de vehiculos
        listaFiltrada.VaciarLista();
        agregarRegistroVenta(dni, comprador.nombre,vendido.getPrecio(),medioPago,patente,vendido.getMarca(),vendido.getModelo(),ventasEfectuadas);
        //Se recarga toda la lista (ahora actualizada)


    }
    public boolean comprobarPresupuestoCliente(String dni, String patente)
    {
        Cliente cliente=buscarCliente(dni); //Busca el cliente

        boolean pagable=false;

        if(cliente!=null)
        {
            if(buscarVehiculo(patente).getPrecio()<=cliente.dinero) //Revisa si al cliente le alcanza el dinero para pagar el Vehiculo
            {
                pagable=true;
            }
        }

        return pagable;

    }

}











