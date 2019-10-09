package br.com.fcamara.asnpark.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "TB_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int id;

	@Column(name = "username")
	@NotEmpty(message = "*Por favor, digite seu login")
	private String login;

	@Column(name = "PASSWORD")
	@Length(min = 5, message = "*Sua senha deve ter pelo menos 5 caracteres")
	@NotEmpty(message = "*Por favor, digite sua senha")
	private String password;

	@Column(name="status", columnDefinition = "INT DEFAULT 1", length = 2)
	@Enumerated(EnumType.ORDINAL)
	private EstadoUsuario status;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "TB_USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<Role> roles;

	
	public User comLogin(String login) {
		this.login = login;
		return this;
	}
	
	public User	ePassword(String pass){
		this.password = pass;
		return this;
	}
	
	public User comPerfil(Role novoPerfil) {
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.add(novoPerfil);
		
		this.roles = roleSet;
		return this;
	}
	
	public User comStatus(EstadoUsuario status) {
		this.status = status;
		return this;
	}
	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EstadoUsuario getStatus() {
		return status;
	}

	public void setStatus(EstadoUsuario status) {
		this.status = status;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}