package pl.ksb.agape.domain.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "USER", schema = "AGAPE", uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "uniqueEmail"))
@NamedQueries(value = {
		@NamedQuery(name = "getUserByMail", query = "Select u from User u where u.email = :email"),
		@NamedQuery(name = "getUsers", query = "Select u from User u") })
public class User implements Serializable {
	private static final long serialVersionUID = 8312693018289001116L;

	@Column(name = "address", length = 50)
	@Length(max = 50)
	private String address;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "community", length = 50)
	@Length(max = 50)
	private String community;

	@Column(name = "country")
	private String country;

	@Column(name = "email", length = 30, unique = true)
	@Email
	@NotNull
	@Length(max = 30)
	private String email;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;

	@Column(name = "name", length = 30)
	@NotNull
	@Length(min = 2, max = 30)
	private String name;

	@Column(name = "password", length = 20)
	@Length(max = 20)
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_date")
	private Date registrationDate;

	@Column(name = "religion", length = 50)
	@Length(max = 50)
	private String religion;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	// @Transient
	private List<Role> roles;

	@Column(name = "status", length = 3, nullable = false)
	@NotNull
	private String status;

	@Column(name = "surname", length = 50)
	@Length(max = 50)
	private String surname;

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

	public String getAddress() {
		return address;
	}

	public Integer getAge() {
		Integer ret = null;
		if (birthDate != null) {
			Calendar cBirth = Calendar.getInstance();
			cBirth.setTime(birthDate);
			ret = Calendar.getInstance().get(Calendar.YEAR)
					- cBirth.get(Calendar.YEAR);
		}
		return ret;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getCommunity() {
		return community;
	}

	public String getCountry() {
		return country;
	}

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		String name = this.name;
		if (this.surname != null && !this.surname.isEmpty()) {
			name += " " + surname;
		}
		return name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public String getReligion() {
		return religion;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public String getStatus() {
		return status;
	}

	public String getSurname() {
		return surname;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "pl.ksb.agape.entity.User[id=" + this.id + "]";
	}

}
