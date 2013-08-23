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
@Table(name = "course", schema = "agape")
@NamedQueries(
		value={@NamedQuery(name="getCourse",query="Select c from Course c where c.status = 'A' order by c.number"), 
				@NamedQuery(name="getEnabledCourse", query="Select c from Course c where c.status = 'A' and c.enabled = true order by c.number")})
public class Course implements Serializable {

	private static final long serialVersionUID = 6229300064198947190L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;

	@Column(name = "number", nullable = false)
	@NotNull
	private Long number;

	@Column(name = "title", length = 200, nullable = false)
	@NotNull
	@Length(max = 200)
	private String title;

	@Column(name = "description", length = 5000, nullable = true)
	@Length(max = 5000)
	private String description;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "creation_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "modification_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;

	@Column(name = "status", length=3, nullable = false)
	@NotNull
	private String status;

	@Column(name = "image", nullable=true)
	@Lob
	private byte[] image;

	@OneToMany(mappedBy="course", fetch=FetchType.LAZY)
	@OrderBy("number ASC")
	private Collection<Lesson> lessons;

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

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(Collection<Lesson> lessons) {
		this.lessons = lessons;
	}



}