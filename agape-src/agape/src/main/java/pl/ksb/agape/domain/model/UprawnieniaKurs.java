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

@Entity
@Table(name = "kurs_uprawnienia", schema = "agape")
public class UprawnieniaKurs implements Serializable {

	private static final long serialVersionUID = 2239141571409557644L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;

	@Column(name = "id_kurs", precision = 10, scale = 0)
	private Long idKurs;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_osoba", nullable = false, referencedColumnName = "id")
	private Osoba osoba;

	@Column(name = "operator", length = 30)
	private String operator;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_zm")
	private Date dataZm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdKurs() {
		return idKurs;
	}

	public void setIdKurs(Long idKurs) {
		this.idKurs = idKurs;
	}

	public Osoba getOsoba() {
		return osoba;
	}

	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getDataZm() {
		return dataZm;
	}

	public void setDataZm(Date dataZm) {
		this.dataZm = dataZm;
	}

}
