package es.david.core.models.service;

import java.util.List;
import java.util.Optional;

import es.david.core.models.entities.Cuadro;

public interface ICuadroService {
	
	public Iterable <Cuadro> findAll();
	
	public Optional<Cuadro> findById(Long id);
	
	public Cuadro save(Cuadro cuadro);
	
	public Cuadro saveOnTienda(Cuadro cuadro, Long idTienda);
	
	public List<Cuadro> findByTiendaId(Long idTienda);
	
	public void deleteById(Long id);
	
	public List<Cuadro> saveAll(List<Cuadro> cuadros);
	
	public void deleteAll();
	
	public List<Cuadro> getCuadrosByNombreCuadro(String nombreCuadro);

	public List<Cuadro> getCuadrosByAutor(String autor);
	
	public List<Cuadro> getCuadrosByPrecioGreaterThan(double precio);

}
