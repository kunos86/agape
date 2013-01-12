package pl.ksb.agape.domain.model.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Length;

@Entity
@Table(name = "lekcje_do_spr", schema = "agape")
public class LekcjaDoSpr implements java.io.Serializable {

	private static final long serialVersionUID = -7553075282279536931L;

	@Id
	@Column(name = "id")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_wyslania", length = 29)
	private Date dataWyslania;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_udostepnienia", length = 29)
	private Date dataUdostepnienia;

	@Column(name = "id_lekcja")
	private Long idLekcja;

	@Column(name = "nr_lekcji")
	private Long nrLekcji;

	@Column(name = "id_kursu")
	private Long idKursu;

	@Column(name = "nr_kursu")
	private Long nrKursu;

	@Column(name = "id_osoba")
	private Long idOsoba;

	@Column(name = "imie", length = 30)
	@Length(max = 30)
	private String imie;

	@Column(name = "nazwisko", length = 50)
	@Length(max = 50)
	private String nazwisko;

	@Column(name = "email", length = 30)
	@Length(max = 30)
	private String email;

	@Column(name = "id_nauczyciela")
	private Long idNauczyciela;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataWyslania() {
		return dataWyslania;
	}

	public void setDataWyslania(Date dataWyslania) {
		this.dataWyslania = dataWyslania;
	}

	public Date getDataUdostepnienia() {
		return dataUdostepnienia;
	}

	public void setDataUdostepnienia(Date dataUdostepnienia) {
		this.dataUdostepnienia = dataUdostepnienia;
	}

	public Long getIdLekcja() {
		return idLekcja;
	}

	public void setIdLekcja(Long idLekcja) {
		this.idLekcja = idLekcja;
	}

	public Long getNrLekcji() {
		return nrLekcji;
	}

	public void setNrLekcji(Long nrLekcji) {
		this.nrLekcji = nrLekcji;
	}

	public Long getIdKursu() {
		return idKursu;
	}

	public void setIdKursu(Long idKursu) {
		this.idKursu = idKursu;
	}

	public Long getNrKursu() {
		return nrKursu;
	}

	public void setNrKursu(Long nrKursu) {
		this.nrKursu = nrKursu;
	}

	public Long getIdOsoba() {
		return idOsoba;
	}

	public void setIdOsoba(Long idOsoba) {
		this.idOsoba = idOsoba;
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

	public Long getIdNauczyciela() {
		return idNauczyciela;
	}

	public void setIdNauczyciela(Long idNauczyciela) {
		this.idNauczyciela = idNauczyciela;
	}

}
