package dominio;

import java.util.List;

/**
 * Representa el resultado del análisis de una entrada.
 *
 * @param comando Tipo detectado (LIST/RESET/RESULT/QUIT/CALCULO/ERROR).
 * @param numeros Lista de números si es un cálculo; null si no aplica.
 * @param operadores Lista de operadores si es un cálculo; null si no aplica.
 * @param mensajeError Mensaje descriptivo si comando es ERROR; en caso contrario puede ser null.
 */
public record ResultadoAnalisis(
        TipoComando comando,
        List<Double> numeros,
        List<TipoOperador> operadores,
        String mensajeError
) {}