package pl.ksb.agape.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import pl.ksb.agape.domain.model.dict.RoleEnum;

@Entity
@Table(name = "role", schema = "agape")
public class Role implements Serializable, Comparable<Role>{

	private static final long serialVersionUID = 314667071377078317L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;

	@Column(name = "modification_date", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationDate;

	@Column(name = "role_name", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private RoleEnum roleName;

	@Column(name="enabled")
	private Boolean enabled = Boolean.FALSE;
	
	@ManyToOne(fetch = FetchType.EAGER,optional=false)
	@JoinColumn(name = "user_id")
	@NotNull
	private User user;
	

	public Long getId() {
		return id;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public RoleEnum getRoleName() {
		return roleName;
	}

	public User getUser() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public void setRoleName(RoleEnum roleName) {
		this.roleName = roleName;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int compareTo(Role o) {
		if (o==null || o.getRoleName()==null){
			return 1;
		}
		return roleName.name().compareTo(o.getRoleName().name());
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}



	
	
}
