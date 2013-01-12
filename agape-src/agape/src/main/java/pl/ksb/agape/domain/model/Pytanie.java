package pl.ksb.agape.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


import pl.ksb.agape.domain.model.dict.Status;

@Entity
@Table(name = "pytanie", schema = "agape")
public class Pytanie implements Serializable {

	private static final long serialVersionUID = 9090565015091367287L;

	@Id
	@GeneratedValue
	@Column(name = "id", length = 10, nullable = false)
	private Long id;

	@Column(name = "nr_pytanie", length = 10, nullable = false)
	@NotNull
	private Long nrPytanie;

	@Column(name = "nr_lekcja", length = 10, nullable = false)
	@NotNull
	private Long nrLekcja;

	@Column(name = "tresc", length = 500, nullable = true)
	@Length(max = 500)
	private String tresc;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNrPytanie() {
		return nrPytanie;
	}

	public void setNrPytanie(Long nrPytanie) {
		this.nrPytanie = nrPytanie;
	}

	public Long getNrLekcja() {
		return nrLekcja;
	}

	public void setNrLekcja(Long nrLekcja) {
		this.nrLekcja = nrLekcja;
	}

	public String getTresc() {
		return tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
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

}
