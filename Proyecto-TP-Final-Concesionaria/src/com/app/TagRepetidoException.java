package com.app;

public class TagRepetidoException extends Exception{ //Se crea cuando se intenta agregar un tag que ya fué filtrado

    private String tagRepetido;

    public TagRepetidoException(String mensaje,String tagRepetido)
    {
        super(mensaje);
        this.tagRepetido=tagRepetido;
    }


    @Override
    public String getMessage() {
        return " \n- "+super.getMessage()+": El tag "+tagRepetido+" ya se encuentra en la lista de filtros.\n";
    }
}
