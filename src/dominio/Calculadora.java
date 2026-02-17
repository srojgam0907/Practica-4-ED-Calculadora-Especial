package dominio;

import java.util.ArrayList;
import java.util.List;

public class Calculadora {

	private double resultadoFinal;
	private List<Operacion> historial;
	
	/**
	 * Crea un nuevo objeto Calculadora
	 */
	public Calculadora() {
		this.resultadoFinal= 0;
		this.historial= new ArrayList<>();
	}
	
	/**
	 * Realiza la operación sumar
	 * @param a
	 * @param b
	 * @return resultado de la suma de los parametros
	 */
	private double sumar(double a, double b) {
		return a + b;
	}
	
	/**
	 * Realiza la operación restar
	 * @param a
	 * @param b
	 * @return resultado de la resta de los parametros
	 */
	private double restar(double a, double b) {
		return a - b;
	}
	
	/**
	 * Realiza la operación multiplicar
	 * @param a
	 * @param b
	 * @return resultado de la multiplicación de los parametros
	 */
	private double multiplicar(double a, double b) {
		return a * b;
	}
	
	/**
	 * Realiza la operación dividir
	 * @param a
	 * @param b
	 * @return resultado de la división de los parametros
	 */
	private double dividir(double a, double b) {
		return a / b; 

	}
	
	/**
	 * Realiza una serie de operaciones de izquierda a derecha. 
	 * No hay prioridad de operadores
	 * @param numeros Lista de los numeros de la operación
	 * @param operadores Lista de los operadores que se usarán
	 * @param entrada La operacion que escribio el usuario
	 * @return El resultado final de la operación
	 */
	public double calcular(List<Double> numeros, List<TipoOperador> operadores, String entrada) {
		double resultadoAcumulado= numeros.get(0);
		
		for(int i=0; i<operadores.size(); i++) {
			resultadoAcumulado= ejecutar(resultadoAcumulado, operadores.get(i), numeros.get(i+1));
		}
		
		resultadoFinal= resultadoAcumulado; //Actualiza el estado del ultimo resultado obtenido
		Operacion nuevaOperacion= new Operacion(entrada, resultadoAcumulado); //Creas el objeto operacion
		historial.add(nuevaOperacion); //Añades la ultima operacion al historial
		
		return resultadoAcumulado;
	}

	/**
	 * Selecciona, segun el operador pasado por parametro, 
	 * a que metodo llamar para realizar la operacion correspondiente 
	 * @param a
	 * @param operador
	 * @param b
	 * @return el resultado de la operacion correspondiente
	 */
	private double ejecutar(double a, TipoOperador operador, double b) {

		return switch(operador) {
		case SUMA -> sumar(a, b);
		case RESTA -> restar(a, b);
		case MULTIPLICACION -> multiplicar(a, b);
		case DIVISION -> dividir(a, b);
		};
		
	}
	
	/**
	 * Reinicia la calculadora. Pone el ultimo resultado 
	 * guardado a 0 y vacía el historial
	 */
	public void resetear() {
		this.resultadoFinal= 0;
		this.historial.clear();
	}
	
	/**
	 * Devuelve el ultimo resultado guardado
	 * @return Ultimo resultado guardado
	 */
	public double getResultadoFinal() {
		return resultadoFinal;
	}
	
	/**
	 * Devuelve el historial de operaciones realizadas 
	 * @return lista de operaciones
	 */
	public List<Operacion> getHistorial() {
		return new ArrayList<>(historial); 
	}
}



















