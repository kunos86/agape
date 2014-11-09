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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;






import pl.ksb.agape.domain.dao.IModificationLoggable;
import pl.ksb.agape.domain.model.dict.Status;
import pl.ksb.agape.util.ModificationListener;

@Entity
@Table(name = "question", schema = "agape")
@NamedQueries(value = {
		@NamedQuery(name = "getLessonQuestions", query = "Select q from Question q where q.status = 'A' and q.lesson.id = :lessonId order by q.number"),
		@NamedQuery(name = "getEnabledLessonQuestions", query = "Select q from Question q where q.status = 'A' and q.lesson.id = :lessonId and q.enabled = true order by q.number") })

@EntityListeners(ModificationListener.class)
public class Question extends ModificationUserDate implements IModificationLoggable, Serializable {

	private static final long serialVersionUID = 9090565015091367287L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;

	@Column(name = "number", length = 10, nullable = false)
	@NotNull
	private Long number;

	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="lesson_id")
	@NotNull
	private Lesson lesson;
	
	
	@Column(name = "content", length = 500, nullable = true)
	@Length(max = 500)
	private String content;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "creation_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date creationDate;

	@Column(name = "status", length=3, nullable = false)
	@NotNull
	private String status;

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

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}




}
