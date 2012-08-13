package pl.ksb.agape.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Length;

@Entity
@Table(name = "stan_zaawansowania", schema = "agape")
public class StanZaawansowania implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5601822674128947199L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_osoba", nullable = false, referencedColumnName = "id")
	private Osoba osoba;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_lekcja", nullable = false, referencedColumnName = "id")
	private Lekcja lekcja;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_udostepnienia")
	private Date dataUdostepnienia;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_wyslania")
	private Date dataWyslania;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_sprawdzenia")
	private Date dataSprawdzenia;

	@Column(name = "komentarz", length = 2000)
	@Length(max = 2000)
	private String komentarz;

	@Column(name = "czy_odcz")
	private boolean czyOdczytano = false;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_odcz")
	private Date dataOdczytania;

	@Column(name = "czy_odcz_kom")
	private boolean czyOdczytanoKomentarz = false;

	public String getKomentarz() {
		return komentarz;
	}

	public void setKomentarz(String komentarz) {
		this.komentarz = komentarz;
	}

	public Date getDataOdczytania() {
		return dataOdczytania;
	}

	public void setDataOdczytania(Date dataOdczytania) {
		this.dataOdczytania = dataOdczytania;
	}

	public boolean isCzyOdczytanoKomentarz() {
		return czyOdczytanoKomentarz;
	}

	public void setCzyOdczytanoKomentarz(boolean czyOdczytanoKomentarz) {
		this.czyOdczytanoKomentarz = czyOdczytanoKomentarz;
	}

	public Date getDataOdczytaniaKomentarza() {
		return dataOdczytaniaKomentarza;
	}

	public void setDataOdczytaniaKomentarza(Date dataOdczytaniaKomentarza) {
		this.dataOdczytaniaKomentarza = dataOdczytaniaKomentarza;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_odcz_kom")
	private Date dataOdczytaniaKomentarza;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Osoba getOsoba() {
		return osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}

	public Lekcja getLekcja() {
		return lekcja;
	}

	public void setLekcja(Lekcja lekcja) {
		this.lekcja = lekcja;
	}

	public Date getDataUdostepnienia() {
		return dataUdostepnienia;
	}

	public void setDataUdostepnienia(Date dataUdostepnienia) {
		this.dataUdostepnienia = dataUdostepnienia;
	}

	public Date getDataWyslania() {
		return dataWyslania;
	}

	public void setDataWyslania(Date dataWyslania) {
		this.dataWyslania = dataWyslania;
	}

	public Date getDataSprawdzenia() {
		return dataSprawdzenia;
	}

	public void setDataSprawdzenia(Date dataSprawdzenia) {
		this.dataSprawdzenia = dataSprawdzenia;
	}

	public boolean isCzyOdczytano() {
		return czyOdczytano;
	}

	public void setCzyOdczytano(boolean czyOdczytano) {
		this.czyOdczytano = czyOdczytano;
	}

}
