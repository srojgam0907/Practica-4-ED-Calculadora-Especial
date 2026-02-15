package dominio;

import java.util.ArrayList;
import java.util.List;
import app.Consola;

public class Calculadora {

	private double resultadoFinal;
	private List<Operacion> historial;
	
	public Calculadora() {
		this.resultadoFinal= 0;
		this.historial= new ArrayList<>();
	}
	
	private double sumar(double a, double b) {
		return a + b;
	}
	
	private double restar(double a, double b) {
		return a - b;
	}
	
	private double multiplicar(double a, double b) {
		return a * b;
	}
	
	private double dividir(double a, double b) {
		return a / b; 

	}

}
