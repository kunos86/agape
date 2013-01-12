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

import org.hibernate.annotations.Type;

@Entity
@Table(name = "uczniowie_naucz", schema = "agape")
public class UczenNauczyciel implements Serializable {

	private static final long serialVersionUID = -3947896097082524605L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_nauczyciela", referencedColumnName = "id")
	private Osoba nauczyciel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ucznia", referencedColumnName = "id")
	private Osoba uczen;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_dodania")
	private Date dataDodania;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_usuniecia")
	private Date dataUsuniecia;

	@Column(name = "aktualny")
	@Type(type = "org.hibernate.type.YesNoType")
	private Boolean aktualny;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Osoba getNauczyciel() {
		return nauczyciel;
	}

	public void setNauczyciel(Osoba nauczyciel) {
		this.nauczyciel = nauczyciel;
	}

	public Osoba getUczen() {
		return uczen;
	}

	public void setUczen(Osoba uczen) {
		this.uczen = uczen;
	}

	public Date getDataDodania() {
		return dataDodania;
	}

	public void setDataDodania(Date dataDodania) {
		this.dataDodania = dataDodania;
	}

	public Date getDataUsuniecia() {
		return dataUsuniecia;
	}

	public void setDataUsuniecia(Date dataUsuniecia) {
		this.dataUsuniecia = dataUsuniecia;
	}

	public Boolean getAktualny() {
		return aktualny;
	}

	public void setAktualny(Boolean aktualny) {
		this.aktualny = aktualny;
	}

}
