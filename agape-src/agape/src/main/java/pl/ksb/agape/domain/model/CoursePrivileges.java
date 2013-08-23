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
@Table(name = "course_privileges", schema = "agape")
public class CoursePrivileges implements Serializable {

	private static final long serialVersionUID = 2239141571409557644L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id", nullable=false)
	@NotNull
	private Course course;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable=false)
	@NotNull
	private User user;

	@Column(name = "modificationOperator", length = 30)
	private String modificationOperator;

	@Column(name = "modification_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getModificationOperator() {
		return modificationOperator;
	}

	public void setModificationOperator(String modificationOperator) {
		this.modificationOperator = modificationOperator;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	

}
