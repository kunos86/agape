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


@Entity
@Table(name = "student_teacher", schema = "agape")
public class StudentTeacher implements Serializable {

	private static final long serialVersionUID = -3947896097082524605L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id", nullable=false)
	@NotNull
	private User teacher;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable=false)
	@NotNull
	private User student;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable=false)
	@NotNull
	private Date creationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_date")
	private Date deletedDate;

	@Column(name = "current", length=3, nullable=false)
	@NotNull
	private String current;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Boolean isCurrent() {
		return "T".equals(current);
	}

	public void setCurrent(Boolean current) {
		if (current){
			
			this.current = "T";
		}else{
			this.current = "N";
		}
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	
	

}
