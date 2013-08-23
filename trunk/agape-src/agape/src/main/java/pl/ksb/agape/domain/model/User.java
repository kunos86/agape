package pl.ksb.agape.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;



import pl.ksb.agape.domain.model.dict.RoleEnum;
import pl.ksb.agape.domain.model.dict.Status;

@Entity
@Table(name = "USER", schema = "AGAPE", uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "uniqueEmail"))
@NamedQueries(
		value={@NamedQuery(name="getUserByMail",query="Select u from User u where u.email = :email"), 
				@NamedQuery(name="getUsers",query="Select u from User u")})
public class User implements Serializable {
	private static final long serialVersionUID = 8312693018289001116L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;

	@Column(name = "name", length = 30)
	@NotNull
	@Length(min = 2, max = 30)
	private String name;

	@Column(name = "surname", length = 50)
	@Length(max = 50)
	private String surname;

	@Column(name = "email", length = 30, unique = true)
	@Email
	@NotNull
	@Length(max = 30)
	private String email;

	@Column(name = "password", length = 20)
	@Length(max = 20)
	private String password;

	@Column(name = "country")
	private String country;

	@Column(name = "address", length = 50)
	@Length(max = 50)
	private String address;

	@Column(name = "birth_date")
	private Date birthDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_date")
	private Date registrationDate;

	@Column(name = "status", length=3, nullable=false)
	@NotNull
	private String status;

	@Column(name = "community", length = 50)
	@Length(max = 50)
	private String community;

	@Column(name = "religion", length = 50)
	@Length(max = 50)
	private String religion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}





	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pl.ksb.agape.entity.User[id=" + this.id + "]";
	}

}
