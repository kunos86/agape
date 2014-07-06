package pl.ksb.agape.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "activation_token", schema = "agape")
public class ActivationToken implements Serializable {

	private static final long serialVersionUID = -7850188439234675551L;

	@Id
	@Column(name = "token", length = 200, unique = true)
	private String token;

	@Column(name = "user_id", precision = 10, scale = 0)
	private Long userId;

	public String getToken() {
		return token;
	}

	public Long getUserId() {
		return userId;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
