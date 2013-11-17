package pl.ksb.agape.domain.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "lesson", schema = "agape")
@NamedQueries(value = {
		@NamedQuery(name = "getCourseLessons", query = "Select l from Lesson l where l.status = 'A' and l.course.id = :coursId order by l.number"),
		@NamedQuery(name = "getEnabledCourseLessons", query = "Select l from Lesson l where l.status = 'A' and l.course.id = :coursId and l.enabled = true order by l.number") })
public class Lesson implements Serializable {

	private static final long serialVersionUID = -6935488877088993468L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;

	@Column(name = "number", length = 10, nullable = false)
	@NotNull
	private Long number;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "course_id")
	@NotNull
	private Course course;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "introduction", length = 2000)
	private String introduction;

	@Column(name = "homework", length = 2000)
	private String homework;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "creation_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "modification_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;

	@Column(name = "status", length = 3, nullable = false)
	private String status;

	@OneToMany()
	@JoinColumn(name = "lesson_id")
	private Collection<EducationState> educationStates;

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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getHomework() {
		return homework;
	}

	public void setHomework(String homework) {
		this.homework = homework;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
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

	public Collection<EducationState> getEducationStates() {
		return educationStates;
	}

	public void setEducationStates(Collection<EducationState> educationStates) {
		this.educationStates = educationStates;
	}

}
