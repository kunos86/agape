package pl.ksb.agape.domain.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.NotNull;

import pl.ksb.agape.domain.model.dict.Status;

@Entity
@Table(name = "lekcja", schema = "agape")
public class Lekcja implements Serializable {

	private static final long serialVersionUID = -6935488877088993468L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "nr_lekcji", length = 10, nullable = false)
	@NotNull
	private Long nrLekcji;

	@Column(name = "id_kursu", length = 10, nullable = false)
	@NotNull
	private Long idKursu;

	@Column(name = "opis", length = 500)
	private String opis;

	@Column(name = "wprowadzenie", length = 2000)
	private String wprowadzenie;

	@Column(name = "zadania", length = 2000)
	private String zadaniaDomowe;

	@Column(name = "widocznosc", nullable = false)
	private boolean widocznosc;

	@Column(name = "data_dodania", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDodania;

	@Column(name = "data_arch", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataArch;

	@Column(name = "status", nullable = true)
	private Status status;

	@OneToMany()
	@JoinColumn(name = "id_lekcja")
	private Collection<StanZaawansowania> stanyZaawansowania;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getWprowadzenie() {
		return wprowadzenie;
	}

	public void setWprowadzenie(String wprowadzenie) {
		this.wprowadzenie = wprowadzenie;
	}

	public String getZadaniaDomowe() {
		return zadaniaDomowe;
	}

	public void setZadaniaDomowe(String zadaniaDomowe) {
		this.zadaniaDomowe = zadaniaDomowe;
	}

	public boolean isWidocznosc() {
		return widocznosc;
	}

	public void setWidocznosc(boolean widocznosc) {
		this.widocznosc = widocznosc;
	}

	public Date getDataDodania() {
		return dataDodania;
	}

	public void setDataDodania(Date dataDodania) {
		this.dataDodania = dataDodania;
	}

	public Date getDataArch() {
		return dataArch;
	}

	public void setDataArch(Date dataArch) {
		this.dataArch = dataArch;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Collection<StanZaawansowania> getStanyZaawansowania() {
		return stanyZaawansowania;
	}

	public void setStanyZaawansowania(
			Collection<StanZaawansowania> stanyZaawansowania) {
		this.stanyZaawansowania = stanyZaawansowania;
	}

}
