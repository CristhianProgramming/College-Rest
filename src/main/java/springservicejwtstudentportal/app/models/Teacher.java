package springservicejwtstudentportal.app.models;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "teachers")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProfesor;

	@NonNull
	@Column(nullable = false, unique = true)
	private Integer documentoProfesor;

	@NotBlank
	private String nombreProfesor;
	
	@NotBlank
	private String apellidoProfesor;

	private String materiaDictada;

	public Teacher() {
	}

	public Teacher(@NotBlank Integer documentoProfesor, String nombreProfesor, String apellidoProfesor,
			String isDirector, String materiaDictada) {
		super();
		this.documentoProfesor = documentoProfesor;
		this.nombreProfesor = nombreProfesor;
		this.apellidoProfesor = apellidoProfesor;
		this.materiaDictada = materiaDictada;
	}

	public Long getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(Long idProfesor) {
		this.idProfesor = idProfesor;
	}

	public Integer getDocumentoProfesor() {
		return documentoProfesor;
	}

	public void setDocumentoProfesor(Integer documentoProfesor) {
		this.documentoProfesor = documentoProfesor;
	}

	public String getNombreProfesor() {
		return nombreProfesor;
	}

	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}

	public String getApellidoProfesor() {
		return apellidoProfesor;
	}

	public void setApellidoProfesor(String apellidoProfesor) {
		this.apellidoProfesor = apellidoProfesor;
	}


	public String getMateriaDictada() {
		return materiaDictada;
	}

	public void setMateriaDictada(String materiaDictada) {
		this.materiaDictada = materiaDictada;
	}

}
