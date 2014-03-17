package pl.ksb.agape.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "application_text", schema = "agape")
public class ApplicationText implements Serializable {

	private static final long serialVersionUID = 5386992450804330343L;

	@Column(name = "domain", length = 100)
	private String domain;

	@Id
	@Column(name = "id", unique = true, length = 200)
	private String id;

	@Column(name = "text", length = 1024)
	private String text;

	public String getDomain() {
		return domain;

	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

}
