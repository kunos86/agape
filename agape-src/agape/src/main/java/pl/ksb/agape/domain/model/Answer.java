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

import org.hibernate.validator.constraints.Length;



@Entity
@Table(name = "ANSWER", schema = "AGAPE")
public class Answer implements Serializable {

	private static final long serialVersionUID = -3123059276000447646L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="question_id", nullable=false)
	@NotNull
	private Question question;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="student_id", nullable=false)
	@NotNull
	private User student;	

	@Column(name = "content", length = 500)
	@Length(max = 500)
	private String content;

	@Column(name = "modyfication_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modyficationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getModyficationDate() {
		return modyficationDate;
	}

	public void setModyficationDate(Date modyficationDate) {
		this.modyficationDate = modyficationDate;
	}

	

}
