package app;

import java.util.Scanner;

public class Consola {

	private Scanner kb;
	
	public Consola() {
		this.kb= new Scanner(System.in);
	}
	
	/**
	 * Imprime el prompt correspondiente y lee lo que introduce el usuario
	 * @return el texto que introduce el usuario
	 */
	public String leer() {
		System.out.print("> ");
		return kb.nextLine();
	}
	
	/**
	 * Imprime por pantalla un mensaje para el usuario
	 * @param mensaje El mensaje dirigido al usuario
	 */
	public void escribirMensaje(String mensaje) {
		System.out.println(mensaje);
	}
	
	/**
	 * Imprime por pantalla una mensaje de error
	 * @param mensaje El mensaje para informar al usuario
	 */
	public void escribirError(String mensaje) {
		System.out.println("ERROR: " + mensaje); 
	}
	
}
