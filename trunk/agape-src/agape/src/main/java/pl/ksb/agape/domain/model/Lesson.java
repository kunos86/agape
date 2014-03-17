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

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "course_id")
	@NotNull
	private Course course;

	@Column(name = "creation_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@Column(name = "description", length = 500)
	private String description;

	@OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
	// @JoinColumn(name = "lesson_id")
	private Collection<EducationState> educationStates;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "homework", length = 2000)
	private String homework;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;

	@Column(name = "introduction", length = 2000)
	private String introduction;

	@Column(name = "modification_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;

	@Column(name = "number", length = 10, nullable = false)
	@NotNull
	private Long number;

	@Column(name = "status", length = 3, nullable = false)
	private String status;

	public Course getCourse() {
		return course;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getDescription() {
		return description;
	}

	public Collection<EducationState> getEducationStates() {
		return educationStates;
	}

	public String getHomework() {
		return homework;
	}

	public Long getId() {
		return id;
	}

	public String getIntroduction() {
		return introduction;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public Long getNumber() {
		return number;
	}

	public String getStatus() {
		return status;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEducationStates(Collection<EducationState> educationStates) {
		this.educationStates = educationStates;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setHomework(String homework) {
		this.homework = homework;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
