package app;

import java.util.Scanner;

public class Consola {

	@SuppressWarnings("unused")
	private Scanner kb= new Scanner(System.in);
	
	/**
     * Muestra un texto por consola sin salto de línea.
     *
     * @param texto Texto que se desea mostrar.
     */
    public void escribir(String texto) { //Hay que hacer el bucle que comprube que está bien escrito
        System.out.print(texto);
    }
}
