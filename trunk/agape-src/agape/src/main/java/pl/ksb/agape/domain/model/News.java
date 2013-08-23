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



@Entity
@Table(name="news", schema="agape" )
public class News implements Serializable {
	

	private static final long serialVersionUID = 6290258261191517018L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;
	
	@Column(name="content", length=500, nullable=false)
	@Length(max=500)
	@NotNull
	private String content;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="mofification_date", nullable=false)
	@NotNull
	private Date modificationDate;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}




	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getModificationDate() {
		return modificationDate;
	}


	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}



	
	
	

}
