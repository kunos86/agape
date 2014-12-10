package pl.ksb.agape.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import pl.ksb.agape.domain.dao.IModificationLoggable;
import pl.ksb.agape.util.ModificationListener;



@Entity
@Table(name="news", schema="agape" )
@EntityListeners(ModificationListener.class)
public class News extends ModificationUserDate  implements IModificationLoggable, Serializable {
	

	private static final long serialVersionUID = 6290258261191517018L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;
	
	
	@Column(name="title", length=100, nullable=false)
	@Length(max=100)
	@NotNull
	private String title;
	
	@Column(name="content", length=500, nullable=false)
	@Length(max=500)
	@NotNull
	private String content;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="modification_user_id", insertable=false, updatable=false, nullable=true)
	private User modificationUser;


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

	public User getModificationUser() {
		return modificationUser;
	}

	public void setModificationUser(User modificationUser) {
		this.modificationUser = modificationUser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	
	

}
