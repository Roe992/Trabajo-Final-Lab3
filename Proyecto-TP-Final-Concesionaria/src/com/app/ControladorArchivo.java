package com.app;

import java.io.*;

public class ControladorArchivo {

    public static Concesionaria leerArchivo() {
        Concesionaria aux=new Concesionaria("ejemplo",200.2,"calle3065");
        try {
            FileInputStream fileInputStream = new FileInputStream("datos.bin");

            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);

            aux = (Concesionaria) inputStream.readObject();

            inputStream.close();

        } catch (EOFException e) {

            System.out.println("FIN del Archivo");
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NotSerializableException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return aux;
    }

    public static void guardarArchivos(Concesionaria concesionaria) throws Exception
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream("datos.bin");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(concesionaria);

            objectOutputStream.close();

        }
        catch (EOFException e) {

            System.out.println("FIN del Archivo");
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NotSerializableException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



}
