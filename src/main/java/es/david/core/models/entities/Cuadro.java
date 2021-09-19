package es.david.core.models.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="cuadros")
public class Cuadro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cuadro")
	private Long idCuadro;
	
	@Column(name="nombre_cuadro", length = 100, nullable = false, unique = true)
	private String nombreCuadro;
	
	@Column(name="autor", length = 100, nullable = true)
	private String autor;
	
	@Column(name="precio", nullable=false)
	private double precio;
	
	@Column(name="fecha_creacion", nullable= false)
	private Date fechaCreacion;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tienda")
	private Tienda tienda;

	public Long getIdCuadro() {
		return idCuadro;
	}

	public void setIdCuadro(Long idCuadro) {
		this.idCuadro = idCuadro;
	}

	public String getNombreCuadro() {
		return nombreCuadro;
	}

	public void setNombreCuadro(String nombreCuadro) {
		this.nombreCuadro = nombreCuadro;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@JsonBackReference //evita recursividad infinita en las llamadas a la api
	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	@Override
	public String toString() {
		return "Cuadro [idCuadro=" + idCuadro + ", nombreCuadro=" + nombreCuadro + ", autor=" + autor + ", precio="
				+ precio + ", fechaCreacion=" + fechaCreacion + ", tienda=" + tienda + "]";
	}
	
}
