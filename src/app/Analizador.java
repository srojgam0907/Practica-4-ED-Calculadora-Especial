package app;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de analizar la entrada del usuario.
 * Detecta comandos o interpreta operaciones aritméticas (sin prioridad).
 */
public class Analizador {

    private String entrada;
    private List<Double> numeros;
    private List<TipoOperador> operadores;
    private TipoComando comando;
    private String mensajeError;

    /**
     * Analiza la entrada del usuario.
     *
     * @param entrada Línea introducida por el usuario.
     * @param resultadoActual Último resultado de la calculadora (para sustituir "result").
     * @return Resultado del análisis.
     */
    public ResultadoAnalisis analizar(String entrada, double resultadoActual) {
        resetEstado(entrada);

        String[] tokens = tokenizar();
        if (tokens.length == 0) {
            return error("Entrada vacía.");
        }

        if (tokens.length == 1) {
            return analizarComando(tokens[0]);
        }

        return analizarCalculo(tokens, resultadoActual);
    }

    /**
     * Reinicia el estado interno del analizador.
     *
     * @param entrada Entrada original del usuario.
     */
    private void resetEstado(String entrada) {
        this.entrada = (entrada == null) ? "" : entrada.trim();
        this.numeros = null;
        this.operadores = null;
        this.comando = null;
        this.mensajeError = null;
    }

    /**
     * Divide la entrada en tokens usando el espacio como separador.
     *
     * @return Array de tokens; vacío si no hay contenido.
     */
    private String[] tokenizar() {
        if (this.entrada.isBlank()) {
            return new String[0];
        }
        return this.entrada.split(" ");
    }

    /**
     * Analiza un único token como posible comando.
     *
     * @param token Token único introducido.
     * @return ResultadoAnalisis con el comando o error si no es válido.
     */
    private ResultadoAnalisis analizarComando(String token) {
        String comandoLeido = token.trim().toLowerCase();

        if (comandoLeido.equals("list")) {
            this.comando = TipoComando.LIST;
            return okComando(this.comando);
        }
        if (comandoLeido.equals("reset")) {
            this.comando = TipoComando.RESET;
            return okComando(this.comando);
        }
        if (comandoLeido.equals("result")) {
            this.comando = TipoComando.RESULT;
            return okComando(this.comando);
        }
        if (comandoLeido.equals("quit")) {
            this.comando = TipoComando.QUIT;
            return okComando(this.comando);
        }

        return error("Comando o token desconocido: '" + token + "'");
    }

    /**
     * Analiza una operación aritmética (cálculo).
     *
     * @param tokens Tokens de la entrada.
     * @param resultadoActual Último resultado para sustituir "result".
     * @return ResultadoAnalisis con listas o error.
     */
    private ResultadoAnalisis analizarCalculo(String[] tokens, double resultadoActual) {
        // No puede ser comando si hay más de 1 token
        if (!estructuraValida(tokens)) {
            return error("Estructura inválida: debe ser OPERANDO (OPERADOR OPERANDO)*");
        }

        this.comando = TipoComando.CALCULO;
        this.numeros = new ArrayList<>();
        this.operadores = new ArrayList<>();

        int i = 0;
        while (i < tokens.length) {
            if (i % 2 == 0) {
                Double opnd = parsearOperando(tokens[i], resultadoActual);
                if (opnd == null) {
                    return error(this.mensajeError);
                }
                this.numeros.add(opnd);
            } else {
                TipoOperador op = parsearOperador(tokens[i]);
                if (op == null) {
                    return error(this.mensajeError);
                }
                this.operadores.add(op);
            }
            i++;
        }

        if (this.numeros.size() != this.operadores.size() + 1) {
            return error("Estructura inválida: faltan operandos u operadores.");
        }

        return new ResultadoAnalisis(this.comando, this.numeros, this.operadores, null);
    }

    /**
     * Valida estructura básica: número impar de tokens y patrón OPERANDO/OPERADOR alternante.
     *
     * @param tokens Tokens de entrada.
     * @return true si cumple estructura mínima, false si no.
     */
    private boolean estructuraValida(String[] tokens) {
        if (tokens.length < 3) return false;
        if (tokens.length % 2 == 0) return false; // debe ser impar
        // No validamos el contenido aquí a fondo, solo el patrón posicional
        return true;
    }

    /**
     * Parsea un operando:
     * - Si es "result", devuelve resultadoActual.
     * - Si es número, intenta convertirlo (admite coma o punto).
     *
     * @param token Token a parsear.
     * @param resultadoActual Último resultado.
     * @return Double si OK; null si ERROR (y deja mensajeError).
     */
    private Double parsearOperando(String token, double resultadoActual) {
        String t = token.trim();
        String lower = t.toLowerCase();

        // Comandos prohibidos dentro de cálculo (excepto "result")
        if (lower.equals("list") || lower.equals("reset") || lower.equals("quit")) {
            this.mensajeError = "Comando '" + token + "' no puede aparecer dentro de un cálculo.";
            return null;
        }

        if (lower.equals("result")) {
            return resultadoActual;
        }

        String normalizado = lower.replace(',', '.');
        try {
            return Double.parseDouble(normalizado);
        } catch (NumberFormatException e) {
            this.mensajeError = "Número inválido: '" + token + "'";
            return null;
        }
    }

    /**
     * Parsea un operador (+, -, *, /) y lo convierte en TipoOperador.
     *
     * @param token Token operador.
     * @return TipoOperador si OK; null si ERROR (y deja mensajeError).
     */
    private TipoOperador parsearOperador(String token) {
        String t = token.trim();

        if (t.equals("+")) return TipoOperador.SUMA;
        if (t.equals("-")) return TipoOperador.RESTA;
        if (t.equals("*")) return TipoOperador.MULTIPLICACION;
        if (t.equals("/")) return TipoOperador.DIVISION;

        this.mensajeError = "Operador inválido: '" + token + "'";
        return null;
    }

    /**
     * Crea un resultado OK de tipo comando (sin listas).
     */
    private ResultadoAnalisis okComando(TipoComando comando) {
        return new ResultadoAnalisis(comando, null, null, null);
    }

    /**
     * Crea un resultado ERROR.
     */
    private ResultadoAnalisis error(String mensaje) {
        this.comando = TipoComando.ERROR;
        this.mensajeError = mensaje;
        return new ResultadoAnalisis(this.comando, null, null, this.mensajeError);
    }
