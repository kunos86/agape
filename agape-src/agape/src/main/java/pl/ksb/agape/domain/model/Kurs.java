package pl.ksb.agape.domain.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


import pl.ksb.agape.domain.model.dict.Status;

@Entity
@Table(name = "kurs", schema = "agape")
@NamedQueries(
		value={@NamedQuery(name="getWszystkieKursy",query="Select k from Kurs k order by k.nrKursu"), 
				@NamedQuery(name="getWidoczneKursy", query="Select k from Kurs k where k.widocznosc = 'T' order by k.nrKursu")})
public class Kurs implements Serializable {

	private static final long serialVersionUID = 6229300064198947190L;

	@Id
	@GeneratedValue
	@Column(name = "id", length = 10, nullable = false)
	private Long id;

	@Column(name = "nr_kursu", length = 10, nullable = false)
	@NotNull
	private Long nrKursu;

	@Column(name = "tytul", length = 200, nullable = false)
	@NotNull
	@Length(max = 200)
	private String tytul;

	@Column(name = "opis", length = 5000, nullable = true)
	@Length(max = 5000)
	private String opis;

	@Column(name = "widocznosc",length = 3, nullable = false)
	@Length(max = 3)
	private String widocznosc;

	@Column(name = "data_dodania", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDodania;

	@Column(name = "data_arch", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataArch;

	@Column(name = "status", nullable = true)
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name = "image")
	@Lob
	private byte[] image;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_kursu")
	@OrderBy("nrLekcji ASC")
	private Collection<Lekcja> lekcje;

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNrKursu() {
		return nrKursu;
	}

	public void setNrKursu(Long nrKursu) {
		this.nrKursu = nrKursu;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public boolean isWidocznosc() {
		
		return "T".equals(widocznosc);
	}

	public void setWidocznosc(boolean widocznosc) {
		if (widocznosc){
			this.widocznosc = "T";
		}else{
			this.widocznosc = "N";
		}
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

	public Collection<Lekcja> getLekcje() {
		return lekcje;
	}

	public void setLekcje(Collection<Lekcja> lekcje) {
		this.lekcje = lekcje;
	}

}