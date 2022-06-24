package com.app;

public class MalCargadoException extends Exception{ //Se crea cuando se ingresan letras o caracteres inválidos donde sólo se permiten números

    public MalCargadoException(String mensaje)
    {
        super(mensaje);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
