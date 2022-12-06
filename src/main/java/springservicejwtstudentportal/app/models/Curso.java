package springservicejwtstudentportal.app.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Curso implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String numeroCurso;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "directorTeacher",unique = true,nullable = true)
	private Teacher hasDirector;

	public Curso() {

	}
	
	public Curso(String numeroCurso) {
		this.numeroCurso = numeroCurso;
	
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroCurso() {
		return numeroCurso;
	}

	public void setNumeroCurso(String numeroCurso) {
		this.numeroCurso = numeroCurso;
	}

	public Teacher getHasDirector() {
		return hasDirector;
	}

	public void setHasDirector(Teacher hasDirector) {
		this.hasDirector = hasDirector;
	}

	
	
}
