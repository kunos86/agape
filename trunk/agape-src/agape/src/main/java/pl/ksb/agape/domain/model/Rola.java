package pl.ksb.agape.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import pl.ksb.agape.domain.model.dict.RolaEnum;


@Entity
@Table(name="rola", schema="agape")
public class Rola implements Serializable{

	private static final long serialVersionUID = 314667071377078317L;


	@Id
	@GeneratedValue
	@Column(name="id", precision=10, scale=0)
	private Long id;
	
	
	@Column(name="id_osoba", precision=10, scale=0)
	private Long id_osoba;
	
	
	@Column(name="rola", length=10)
	@Enumerated(EnumType.STRING)
	private RolaEnum  rola;
	
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_arch")
	private Date dataArch;


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}


	public Long getId_osoba() {
		return id_osoba;
	}

	public void setId_osoba(Long id_osoba) {
		this.id_osoba = id_osoba;
	}



	public RolaEnum getRola() {
		return rola;
	}



	public void setRola(RolaEnum rola) {
		this.rola = rola;
	}



	public Date getDataArch() {
		return dataArch;
	}

	public void setDataArch(Date dataArch) {
		this.dataArch = dataArch;
	}

	

	
}
