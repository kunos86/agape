package pl.ksb.agape.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@MappedSuperclass
public abstract class ModificationUserDate {
	
	@Column(name = "modification_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modificationDate;
	
	

	@Column(name = "modification_user_id",length=255)
	protected Long modificationUserId;



	public Date getModificationDate() {
		return modificationDate;
	}



	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}



	public Long getModificationUserId() {
		return modificationUserId;
	}



	public void setModificationUserId(Long modificationUserId) {
		this.modificationUserId = modificationUserId;
	}

	



	
	
	
}
