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
	
	public double calcular(List<Double> numeros, List<TipoOperador> operadores, String entrada) {
		double resultadoAcumulado= numeros.get(0);
		
		for(int i=0; i<operadores.size(); i++) {
			resultadoAcumulado= ejecutar(resultadoAcumulado, operadores.get(i), numeros.get(i));
		}
		
		resultadoFinal= resultadoAcumulado; //Actualiza el estado del ultimo resultado obtenido
		Operacion nuevaOperacion= new Operacion(entrada, resultadoAcumulado); //Creas el objeto operacion
		historial.add(nuevaOperacion); //Añades la ultima operacion al historial
		
		return resultadoAcumulado;
	}

	private double ejecutar(double a, TipoOperador operador, double b) {

		return switch(operador) {
		case SUMA -> sumar(a, b);
		case RESTA -> restar(a, b);
		case MULTIPLICACION -> multiplicar(a, b);
		case DIVISION -> dividir(a, b);
		};
		
	}
	
	
}



















