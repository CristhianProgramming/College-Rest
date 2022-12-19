package springservicejwtstudentportal.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsers;
	
	private String userName;
	
	private String passWorld;
	
	public Users() {
	}

	public Users(String userName, String passWorld) {
		this.userName = userName;
		this.passWorld = passWorld;
	}

	public Long getIdUsers() {
		return idUsers;
	}

	public void setIdUsers(Long idUsers) {
		this.idUsers = idUsers;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String PassWorld() {
		return passWorld;
	}

	public void setPassWorld(String passWorld) {
		this.passWorld = passWorld;
	}
	
	
}
