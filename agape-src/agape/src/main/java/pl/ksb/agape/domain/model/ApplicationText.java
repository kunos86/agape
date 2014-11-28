package pl.ksb.agape.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import pl.ksb.agape.domain.model.dict.ApplicationTextType;

@Entity
@Table(name = "application_text", schema = "agape")
public class ApplicationText implements Serializable {

	private static final long serialVersionUID = 5386992450804330343L;

	@Column(name = "domain", length = 100)
	private String domain;

	@Id
	@Column(name = "id", unique = true, length = 200)
	private String id;

	@Column(name = "text", length = 6000)
	@Length(max=6000)
	private String text;
	
	@Column(name = "type",length = 5)
	@Enumerated(EnumType.STRING)
	private ApplicationTextType type = ApplicationTextType.TEXT;

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

	public ApplicationTextType getType() {
		return type;
	}

	public void setType(ApplicationTextType type) {
		this.type = type;
	}
	
	

}
