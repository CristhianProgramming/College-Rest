package springservicejwtstudentportal.app.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"profesor_calificador","estudiante_calificado","curso_calificado"})})
public class Notas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombreTrabajo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_calificado")
	private Curso cursoCalificado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesor_calificador")
	private Teacher profesorCalificador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estudiante_calificado")
	private Students estudianteCalificado;
	
	@Column(length = 2)
	private Float calificacion;

	public Notas() {}
	
	public Notas(String nombreTrabajo, Curso cursoCalificado, Teacher profesorCalificador,
			Students estudianteCalificado, Float calificacion) {
		super();
		this.nombreTrabajo = nombreTrabajo;
		this.cursoCalificado = cursoCalificado;
		this.profesorCalificador = profesorCalificador;
		this.estudianteCalificado = estudianteCalificado;
		this.calificacion = calificacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreTrabajo() {
		return nombreTrabajo;
	}

	public void setNombreTrabajo(String nombreTrabajo) {
		this.nombreTrabajo = nombreTrabajo;
	}

	public Teacher getProfesorCalificador() {
		return profesorCalificador;
	}

	public void setProfesorCalificador(Teacher profesorCalificador) {
		this.profesorCalificador = profesorCalificador;
	}

	public Students getEstudianteCalificado() {
		return estudianteCalificado;
	}

	public void setEstudianteCalificado(Students estudianteCalificado) {
		this.estudianteCalificado = estudianteCalificado;
	}

	public Float getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Float calificacion) {
		this.calificacion = calificacion;
	}
	
	
	
}
