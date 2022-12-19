package springservicejwtstudentportal.app.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "rol", uniqueConstraints = @UniqueConstraint(columnNames = { "users_rols", "descripcion" }))
public class Rols {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRol;

	private String descripcion;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "users_rols")
	private Users users;

	public Long getIdRol() {
		return idRol;
	}

	public Rols() {

	}

	public Rols(String descripcion, Users users) {

		this.descripcion = descripcion;
		this.users = users;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public String getdescripcion() {
		return descripcion;
	}

	public void setdescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
