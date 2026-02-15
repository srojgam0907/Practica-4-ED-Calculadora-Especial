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
	
	public String getEntrada() {
		return entrada;
	}
	
	public double getResultado() {
		return resultado;
	}

	@Override
	public String toString() {
		return String.format("%s = %.2f", entrada, resultado);
	}

}
