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



import pl.ksb.agape.domain.model.dict.RolaEnum;
import pl.ksb.agape.domain.model.dict.Status;

@Entity
@Table(name = "OSOBA", schema = "AGAPE", uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "uniqueEmail"))


@NamedQueries(
		value={@NamedQuery(name="getOsobaMail",query="Select o from Osoba o where o.email = :email"), 
				@NamedQuery(name="getWszystkieOsoby",query="Select o from Osoba o")})
public class Osoba implements Serializable {
	private static final long serialVersionUID = 8312693018289001116L;

	@Id
	@GeneratedValue
	@Column(name = "id", precision = 10, scale = 0)
	private Long id;

	@Column(name = "imie", length = 30)
	@NotNull
	@Length(min = 2, max = 30)
	private String imie;

	@Column(name = "nazwisko", length = 50)
	@Length(max = 50)
	private String nazwisko;

	@Column(name = "email", length = 30, unique = true)
	@Email
	@NotNull
	@Length(max = 30)
	private String email;

	@Column(name = "haslo", length = 20)
	@Length(max = 20)
	private String haslo;

	@Column(name = "wojewodztwo")
	private String wojewodztwo;

	@Column(name = "adres", length = 50)
	@Length(max = 50)
	private String adres;

	@Column(name = "DATA_UR")
	private Date dataUr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_REJ")
	private Date dataRej;

	@Column(name = "status", length=3, nullable=false)
	@NotNull
	private String status;

	@Column(name = "wspolnota", length = 50)
	@Length(max = 50)
	private String wspolnota;

	@Column(name = "wyznanie", length = 50)
	@Length(max = 50)
	private String wyznanie;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getWojewodztwo() {
		return wojewodztwo;
	}

	public void setWojewodztwo(String wojewodztwo) {
		this.wojewodztwo = wojewodztwo;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public Date getDataUr() {
		return dataUr;
	}

	public void setDataUr(Date dataUr) {
		this.dataUr = dataUr;
	}

	public Date getDataRej() {
		return dataRej;
	}

	public void setDataRej(Date dataRej) {
		this.dataRej = dataRej;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWspolnota() {
		return wspolnota;
	}

	public void setWspolnota(String wspolnota) {
		this.wspolnota = wspolnota;
	}

	public String getWyznanie() {
		return wyznanie;
	}

	public void setWyznanie(String wyznanie) {
		this.wyznanie = wyznanie;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Osoba)) {
			return false;
		}
		Osoba other = (Osoba) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pl.ksb.agape.entity.Osoba[id=" + this.id + "]";
	}

}
