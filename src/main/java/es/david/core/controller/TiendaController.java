package es.david.core.controller;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.david.core.exceptions.APIException;
import es.david.core.exceptions.TiendaNotFoundException;
import es.david.core.models.entities.Tienda;
import es.david.core.models.service.ITiendaService;

@RestController
@RequestMapping("/api/tiendas")
@CrossOrigin(origins = "*")
public class TiendaController {

	@Autowired
	private ITiendaService tiendaService;

	@GetMapping("/greet")
	public ResponseEntity<String> hello(){
		String msg = "Bienvenido al apartado de Tiendas";
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "App online de tiendas");
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Tienda tienda) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tiendaService.save(tienda));
	}

	@PostMapping("/data")
	public ResponseEntity<?> createAll(@RequestBody List<Tienda> tiendas) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tiendaService.saveAll(tiendas));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long idTienda) {

		Optional<Tienda> oTienda = tiendaService.findById(idTienda);

		if (!oTienda.isPresent()) {
			throw  new TiendaNotFoundException(idTienda);
		}

		return ResponseEntity.ok(oTienda);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Tienda tienda, @PathVariable Long id) {
		Optional<Tienda> oTienda = tiendaService.findById(id);
		
		if (!oTienda.isPresent()) {
			throw  new TiendaNotFoundException(id);
		}

		oTienda.get().setNombreTienda(tienda.getNombreTienda());
		oTienda.get().setMaxCuadros(tienda.getMaxCuadros());

		return ResponseEntity.status(HttpStatus.CREATED).body(tiendaService.save(oTienda.get()));
	}

	@DeleteMapping("/fire/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		if (!tiendaService.findById(id).isPresent()) {
			throw  new TiendaNotFoundException(id);
		}

		tiendaService.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/fire")
	public ResponseEntity<?> deleteAll() {

		tiendaService.deleteAll();
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public List<Tienda> readAll() {	

		List<Tienda> tiendas = StreamSupport.stream(tiendaService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return tiendas;
	}

	@GetMapping("/search/{nombre}")
	public ResponseEntity<?> readByNombreTienda(@PathVariable(value = "nombre") String nombreTienda) {

		List<Tienda> tiendas = tiendaService.getTiendasByNombreTienda(nombreTienda);

		return ResponseEntity.ok(tiendas);
	}

	@GetMapping("/search/capacity/{max_cuadros}")
	public ResponseEntity<?> readByMaxCuadros(@PathVariable(value = "max_cuadros") int maxCuadros) {

		List<Tienda> tiendas = tiendaService.getTiendasByCapacityGreaterThan(maxCuadros);

		return ResponseEntity.ok(tiendas);
	}

}
