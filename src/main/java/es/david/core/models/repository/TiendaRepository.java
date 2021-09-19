package es.david.core.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.david.core.models.entities.Tienda;

public interface TiendaRepository extends JpaRepository<Tienda, Long>{
	
	public List<Tienda> findByNombreTiendaContainingIgnoreCase(String nombreTienda);
	
	public List<Tienda> findByMaxCuadrosGreaterThan(int maxCuadros);

}
