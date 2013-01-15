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

import org.hibernate.validator.constraints.Length;



@Entity
@Table(name="news", schema="agape" )
public class News implements Serializable {
	

	private static final long serialVersionUID = 6290258261191517018L;

	@Id
	@GeneratedValue
	@Column(name="id", precision=10, scale=0)
	private Long id;
	
	@Column(name="tresc", length=500)
	@Length(max=500)
	private String tresc;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_arch")
	private Date dataArch;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
