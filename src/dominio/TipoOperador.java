package dominio;

public enum TipoOperador {

	suma('+'),
	resta('-'),
	multiplicacion('*'),
	division('/');

	private char symbol;
	
	TipoOperador(char symbol) {
		this.symbol= symbol;
	}


    public char getSymbol() {
        return symbol;
    }

}
