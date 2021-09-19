package es.david.core.models.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.core.exceptions.APIException;
import es.david.core.exceptions.CuadroNotFoundException;
import es.david.core.exceptions.TiendaNotFoundException;
import es.david.core.models.entities.Cuadro;
import es.david.core.models.repository.CuadroRepository;
import es.david.core.models.repository.TiendaRepository;

@Service
public class CuadroServiceImpl implements ICuadroService {

	@Autowired
	CuadroRepository cuadroRepository;
	@Autowired
	TiendaRepository tiendaRepository;
	
	@Override
	public Iterable<Cuadro> findAll() {
		return cuadroRepository.findAll();
	}

	@Override
	public Optional<Cuadro> findById(Long id) {
		
		if(!cuadroRepository.findById(id).isPresent()) throw new CuadroNotFoundException(id);
		
		return cuadroRepository.findById(id);
	}

	@Override
	public Cuadro save(Cuadro cuadro) {
		cuadro.setFechaCreacion(Timestamp.valueOf(LocalDateTime.now()));
		return cuadroRepository.save(cuadro);
	}

	@Override
	public void deleteById(Long id) {
		
		if(!cuadroRepository.findById(id).isPresent()) throw new CuadroNotFoundException(id);
		
		cuadroRepository.deleteById(id);
	}
	

	@Override
	public List<Cuadro> saveAll(List<Cuadro> cuadros) {
		
		if(cuadros.isEmpty() || cuadros.size()==0) throw new APIException("La lista de cuadros esta vacía");
		
		return cuadroRepository.saveAll(cuadros);
	}

	@Override
	public void deleteAll() {
		cuadroRepository.deleteAll();
	}

	@Override
	public List<Cuadro> getCuadrosByNombreCuadro(String nombreCuadro) {
		
		List<Cuadro> cuadrosByNombre= cuadroRepository.findByNombreCuadroContainingIgnoreCase(nombreCuadro);
		
		if(cuadrosByNombre.isEmpty() || cuadrosByNombre.size()==0) throw new CuadroNotFoundException("No se encontraron cuadros con el nombre de: " + nombreCuadro);
		
		return cuadrosByNombre;
	}

	@Override
	public List<Cuadro> getCuadrosByAutor(String autor) {

		List<Cuadro> cuadrosByAutor = cuadroRepository.findByAutor(autor);
		
		if(cuadrosByAutor.isEmpty()|| cuadrosByAutor.size()==0) throw new CuadroNotFoundException("No se encontraron cuadros con el autor: " + autor);

		
		return cuadrosByAutor;
	}

	@Override
	public List<Cuadro> getCuadrosByPrecioGreaterThan(double precio) {

		List<Cuadro> cuadrosByPrecio = cuadroRepository.findByPrecioGreaterThan(precio);
		
		if(cuadrosByPrecio.isEmpty() || cuadrosByPrecio.size()==0) throw new CuadroNotFoundException("No se encontraron cuadros con un precio superior a: " + precio);

		return cuadrosByPrecio;
	}

	@Override
	public Cuadro saveOnTienda(Cuadro cuadro, Long idTienda) {
		
		if(!tiendaRepository.findById(idTienda).isPresent()) throw new TiendaNotFoundException(idTienda);
		
		cuadro.setTienda(tiendaRepository.findById(idTienda).get());
		
		cuadro.setFechaCreacion(Timestamp.valueOf(LocalDateTime.now()));
		
		return cuadroRepository.save(cuadro);
	}

	@Override
	public List<Cuadro> findByTiendaId(Long idTienda) {
		
		List<Cuadro> cuadrosPorTienda = new ArrayList<>();
		
		for(Cuadro c: cuadroRepository.findAll()) {
			if(c.getTienda().getIdTienda()==idTienda) cuadrosPorTienda.add(c);
		}
		
		if(cuadrosPorTienda.isEmpty()||cuadrosPorTienda.size()==0) throw new CuadroNotFoundException("No se encontraron cuadros para la tienda con id: " + idTienda);
		
		return cuadrosPorTienda;
	}

}
