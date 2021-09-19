package es.david.core.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.david.core.models.entities.Cuadro;

public interface CuadroRepository extends JpaRepository<Cuadro, Long> {
	
	public List<Cuadro> findByNombreCuadroContainingIgnoreCase(String nombreCuadro);
	
	public List<Cuadro> findByAutor(String autor);
	
	public List<Cuadro> findByPrecioGreaterThan(double precio);
	

}
