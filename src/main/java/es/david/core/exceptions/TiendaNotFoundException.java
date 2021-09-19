package es.david.core.exceptions;

public class TiendaNotFoundException extends RuntimeException{
	
	public TiendaNotFoundException(Long id) {
		super("No se pudo encontrar la tienda con el id: " + id);
	}

	public TiendaNotFoundException() {
		super("No se pudo encontrar la tienda");
	}
	
	public TiendaNotFoundException(String msg) {
		super("No se pudo encontrar la tienda. Causa: " + msg);
	}
}
