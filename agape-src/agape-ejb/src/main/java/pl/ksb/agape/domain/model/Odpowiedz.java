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

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "ODPOWIEDZ", schema = "AGAPE")
public class Odpowiedz implements Serializable {

	private static final long serialVersionUID = -3123059276000447646L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "id_pytanie", precision = 10, scale = 0, nullable = false)
	@NotNull
	private Long idPytanie;

	@Column(name = "id_osoba", precision = 10, scale = 0)
	private Long idOsoba;

	@Column(name = "tresc", length = 500)
	@Length(max = 500)
	private String tresc;

	@Column(name = "data_arch", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataArch;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPytanie() {
		return idPytanie;
	}

	public void setIdPytanie(Long idPytanie) {
		this.idPytanie = idPytanie;
	}

	public Long getIdOsoba() {
		return idOsoba;
	}

	public void setIdOsoba(Long idOsoba) {
		this.idOsoba = idOsoba;
	}

	public String getTresc() {
		return tresc;
	}

	public void setTresc(String tresc) {
		this.tresc = tresc;
	}

	public Date getDataArch() {
		return dataArch;
	}

	public void setDataArch(Date dataArch) {
		this.dataArch = dataArch;
	}

}
