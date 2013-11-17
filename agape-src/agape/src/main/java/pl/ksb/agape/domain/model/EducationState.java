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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "education_state", schema = "agape")
public class EducationState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5601822674128947199L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "student_id", nullable = false)
	@NotNull
	private User student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lesson_id", nullable = false)
	@NotNull
	private Lesson lesson;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "shared_date")
	private Date sharedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sent_Date")
	private Date sentDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "checked_date")
	private Date checkedDate;

	@Column(name = "comment", length = 2000)
	@Length(max = 2000)
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "read_date")
	private Date readDate;

	@Column(name = "read_comment_date")
	private Date readCommentDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public Date getSharedDate() {
		return sharedDate;
	}

	public void setSharedDate(Date sharedDate) {
		this.sharedDate = sharedDate;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public boolean isSent() {
		return sentDate != null;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Date getCheckedDate() {
		return checkedDate;
	}

	public void setCheckedDate(Date checkedDate) {
		this.checkedDate = checkedDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isRead() {
		return readDate != null;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public Date getReadCommentDate() {
		return readCommentDate;
	}

	public void setReadCommentDate(Date readCommentDate) {
		this.readCommentDate = readCommentDate;
	}

}