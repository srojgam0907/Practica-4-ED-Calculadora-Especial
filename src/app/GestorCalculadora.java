package app;

import dominio.*;
import java.util.List;

public class GestorCalculadora {
	private Calculadora calculadora; 
	private Analizador analizador;
	private Consola consola;
	
	/**
	 * Crea un nuevo objeto del gestor de la calculadora
	 */
	public GestorCalculadora() {
		this.calculadora= new Calculadora();
		this.analizador= new Analizador();
		this.consola= new Consola();
	}
	
	/**
	 * Se encarga de iniciar el programa. 
	 * Imprime una bienvenida y empieza un bucle que se queda 
	 * activo hasta que el usuario introduce el comando QUIT
	 */
	public void init() {
		boolean seguir= true;
		consola.escribirMensaje("CALCULADORA\n-----------------");
		
		while(seguir) {
			String entrada= consola.leer();
			ResultadoAnalisis resultado= analizador.analizar(entrada, calculadora.getResultadoFinal());
			
			switch(resultado.comando()) {
			case CALCULO -> hacerOperacion(resultado, entrada);
			case RESULT -> calculadora.getResultadoFinal();
			case LIST -> mostrarHistorial();
			case RESET -> calculadora.resetear();
			case QUIT -> seguir= false;
			case ERROR -> consola.escribirError(resultado.mensajeError());
			}
		}
	}
	
	/**
	 * Llama al metodo calcular de la clase Calculadora 
	 * y muestra el resultado
	 * @param resultado
	 * @param entrada 
	 */
	private void hacerOperacion(ResultadoAnalisis resultado, String entrada) {
		double result= calculadora.calcular(resultado.numeros(), resultado.operadores(), entrada);
		consola.escribirMensaje(result); 
	}
	
	/**
	 * Muestra el historial de operaciones en la pantalla 
	 * con un formato concreto
	 */
	private void mostrarHistorial() {
		List<Operacion> historial= calculadora.getHistorial();
		
		for(int i=0; i<historial.size(); i++) {
			consola.escribirMensaje(i+1 + "- " + historial.get(i));
			
		}
		
		consola.escribirMensaje("Último resultado guardado: " + calculadora.getResultadoFinal()); 
	}
}
