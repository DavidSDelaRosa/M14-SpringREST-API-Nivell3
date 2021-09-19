package es.david.core.exceptions;

public class CuadroNotFoundException extends RuntimeException{
	
	public CuadroNotFoundException(Long id) {
		super("No se pudo encontrrar el cuadro con id: " + id);
	}
	
	public CuadroNotFoundException(String msg) {
		super("No se pudo encontrar el cuadro. Causa: " + msg);
	}
	
	public CuadroNotFoundException() {
		super("No se pudo encontrar el cuadro");
	}

}
