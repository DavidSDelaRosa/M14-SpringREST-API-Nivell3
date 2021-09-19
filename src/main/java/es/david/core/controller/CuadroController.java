package es.david.core.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.david.core.exceptions.APIException;
import es.david.core.exceptions.CuadroNotFoundException;
import es.david.core.models.entities.Cuadro;
import es.david.core.models.service.ICuadroService;

@RestController
@RequestMapping("/api/tiendas")
@CrossOrigin(origins = "*")
public class CuadroController {
	
	@Autowired
	private ICuadroService cuadroService;

	@PostMapping("/cuadros")
	public ResponseEntity<?> create(@RequestBody Cuadro cuadro){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cuadroService.save(cuadro));
	}
	
	@PostMapping("/{id}/cuadros")
	public ResponseEntity<?> createOnShop(@RequestBody Cuadro cuadro, @PathVariable(value = "id") Long idTienda){
		
		Cuadro cuadroPost = cuadroService.saveOnTienda(cuadro, idTienda);
		
		if(cuadroPost==null) return ResponseEntity.notFound().build();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cuadroPost);
	}
	
	@PostMapping("/cuadros/data")
	public ResponseEntity<?> createAll(@RequestBody List<Cuadro> cuadros){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cuadroService.saveAll(cuadros));
	}
	
	@GetMapping("/cuadros/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Cuadro> oCuadro = cuadroService.findById(id);
		
		if(!oCuadro.isPresent())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(oCuadro);
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/cuadros/{id}")
	public ResponseEntity<?> update(@RequestBody Cuadro cuadro, @PathVariable Long id){
		Optional<Cuadro> oCuadro = cuadroService.findById(id);
		
		if(!oCuadro.isPresent())
			return ResponseEntity.notFound().build();
		
		oCuadro.get().setNombreCuadro(cuadro.getNombreCuadro());
		oCuadro.get().setAutor(cuadro.getAutor());
		oCuadro.get().setPrecio(cuadro.getPrecio());
		oCuadro.get().setFechaCreacion(Timestamp.valueOf(LocalDateTime.now()));
		oCuadro.get().setTienda(cuadro.getTienda());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cuadroService.save(oCuadro.get()));
	}
	
	@DeleteMapping("/cuadros/fire/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		if(!cuadroService.findById(id).isPresent())
			return ResponseEntity.notFound().build();
		
		cuadroService.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/cuadros/fire")
	public ResponseEntity<?> deleteAll(){
		cuadroService.deleteAll();
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/cuadros")
	public List<Cuadro> readAll(){
		List<Cuadro> cuadros = StreamSupport
				.stream(cuadroService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return cuadros;
	}
	
	@GetMapping("/cuadros/search/nombre/{nombre}")
	public ResponseEntity<?> readByNombreCuadro(@PathVariable(value="nombre")String nombreCuadro){
		
		List<Cuadro> cuadrosNombre = cuadroService.getCuadrosByNombreCuadro(nombreCuadro);
		
		return ResponseEntity.ok(cuadrosNombre);
	}
	
	@GetMapping("/cuadros/search/autor/{autor}")
	public ResponseEntity<?> readByAutor(@PathVariable String autor){
		
		List<Cuadro> cuadrosAutor = cuadroService.getCuadrosByAutor(autor);
		
		return ResponseEntity.ok(cuadrosAutor);
	}

	@GetMapping("/cuadros/search/precio/{precio}")
	public ResponseEntity<?> readByPrecio(@PathVariable double precio){
		
		List<Cuadro> cuadrosPrecio = cuadroService.getCuadrosByPrecioGreaterThan(precio);
		
		
		return ResponseEntity.ok(cuadrosPrecio);
	}









}
