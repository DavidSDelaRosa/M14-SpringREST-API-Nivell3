package es.david.core.models.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tiendas")
public class Tienda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tienda")
	private Long idTienda;
	
	@Column(name="nombre_tienda", length = 100, unique = true, nullable = false)
	private String nombreTienda;
	
	@Column(name="max_cuadros", nullable = false)
	private Integer maxCuadros;

	@OneToMany(mappedBy = "tienda", orphanRemoval = true)
	private List<Cuadro> cuadros;

	public Long getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(Long idTienda) {
		this.idTienda = idTienda;
	}

	public String getNombreTienda() {
		return nombreTienda;
	}

	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}

	public Integer getMaxCuadros() {
		return maxCuadros;
	}

	public void setMaxCuadros(Integer maxCuadros) {
		this.maxCuadros = maxCuadros;
	}

	@JsonManagedReference //evita recursividad infinita en las llamadas a la api
	public List<Cuadro> getCuadros() {
		return cuadros;
	}

	public void setCuadros(List<Cuadro> cuadros) {
		this.cuadros = cuadros;
	}

	@Override
	public String toString() {
		return "Tienda [idTienda=" + idTienda + ", nombreTienda=" + nombreTienda + ", maxCuadros=" + maxCuadros
				+ ", cuadros=" + cuadros + "]";
	}
	
}
