package dominio;

public class Operacion {

	private final String entrada;
	private final double resultado;
	
	/**
	 * Crea un nuevo objeto Operacion
	 * @param entrada La operacion que introduce el usuario
	 * @param resultado El resultado calculado
	 */
	public Operacion(String entrada, double resultado) {
		this.entrada= entrada;
		this.resultado= resultado;
	}
	
	/**
	 * Devuelve la entrada por teclado original
	 * @return La entrada original del usuario
	 */
	public String getEntrada() {
		return entrada;
	}
	
	/**
	 * Devuelve el resultado de la operacion introducida por el usuario
	 * @return Resultado obtenido de la operación
	 */
	public double getResultado() {
		return resultado;
	}

	/**
	 * Formatea la forma en la se muestra el historial por pantalla
	 * @return La cadena con la operacion y el resultado
	 */
	@Override
	public String toString() {
		return String.format("%s = %.2f", entrada, resultado);
	}

}
