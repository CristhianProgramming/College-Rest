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
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Students implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String documentoEstudiante;

	private String nombreEstudiante;

	private String apellidoEstudiante;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_estudiante")
	private Curso asignedCurso;
	
	
	public Students() {
	}

	public Students(String documentoEstudiante, String nombreEstudiante, String apellidoEstudiante) {
		this.documentoEstudiante = documentoEstudiante;
		this.nombreEstudiante = nombreEstudiante;
		this.apellidoEstudiante = apellidoEstudiante;
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumentoEstudiante() {
		return documentoEstudiante;
	}

	public void setDocumentoEstudiante(String documentoEstudiante) {
		this.documentoEstudiante = documentoEstudiante;
	}

	public String getNombreEstudiante() {
		return nombreEstudiante;
	}

	public void setNombreEstudiante(String nombreEstudiante) {
		this.nombreEstudiante = nombreEstudiante;
	}

	public String getApellidoEstudiante() {
		return apellidoEstudiante;
	}

	public void setApellidoEstudiante(String apellidoEstudiante) {
		this.apellidoEstudiante = apellidoEstudiante;
	}

	public Curso getAsignedCurso() {
		return asignedCurso;
	}

	public void setAsignedCurso(Curso asignedCurso) {
		this.asignedCurso = asignedCurso;
	}

}
