package es.david.core.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.core.exceptions.TiendaNotFoundException;
import es.david.core.models.entities.Tienda;
import es.david.core.models.repository.TiendaRepository;

@Service
public class TiendaServiceImpl implements ITiendaService {

	@Autowired
	TiendaRepository tiendaRepository;
	
	@Override
	public Iterable<Tienda> findAll() {
		return tiendaRepository.findAll();
	}

	@Override
	public Optional<Tienda> findById(Long id) {
		
		if(!tiendaRepository.findById(id).isPresent()) throw new TiendaNotFoundException(id);
		
		return tiendaRepository.findById(id);
	}

	@Override
	public Tienda save(Tienda tienda) {
		return tiendaRepository.save(tienda);
	}

	@Override
	public void deleteById(Long id) {
		
		if(!tiendaRepository.findById(id).isPresent()) throw new TiendaNotFoundException(id);
		
		tiendaRepository.deleteById(id);
	}
	
	@Override
	public List<Tienda> saveAll(List<Tienda> tiendas) {
		return tiendaRepository.saveAll(tiendas);
	}
	
	@Override
	public void deleteAll() {
		tiendaRepository.deleteAll();
	}

	@Override
	public List<Tienda> getTiendasByNombreTienda(String nombreTienda) {
		
		List<Tienda> tiendasByName = tiendaRepository.findByNombreTiendaContainingIgnoreCase(nombreTienda);
		
		if(tiendasByName.isEmpty() || tiendasByName.size()==0) throw new TiendaNotFoundException("No se encontraron tiendas con el nombre: " + nombreTienda);
		
		return tiendasByName;
	}

	@Override
	public List<Tienda> getTiendasByCapacityGreaterThan(int maxCuadros) {
		
		List<Tienda> tiendasByCapacity = tiendaRepository.findByMaxCuadrosGreaterThan(maxCuadros);
		
		if(tiendasByCapacity.isEmpty() || tiendasByCapacity.size()==0) throw new TiendaNotFoundException("No se encontraron tiendas con una capacidad superior a: " + maxCuadros);
		
		return tiendasByCapacity;
	}

}
