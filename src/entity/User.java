package entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@NamedQueries({ @NamedQuery(name = "user_login", query = "from User u " + "where u.username = :P_USERNAME "
		+ "and u.password = :P_PASSWORD") })

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id", name = "role_id")
	private Role role;

	@Column(name = "date_of_creation")
	private Timestamp dateOfCreation;

	@Column(name = "last_updated")
	private Timestamp lastUpdated;

	public User() {
		super();
	}

	public User(int id, String username, String password, Role role, Timestamp dateOfCreation, Timestamp lastUpdated) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.dateOfCreation = dateOfCreation;
		this.lastUpdated = lastUpdated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Timestamp getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Timestamp dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
