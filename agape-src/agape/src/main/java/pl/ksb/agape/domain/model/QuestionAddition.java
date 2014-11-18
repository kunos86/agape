package pl.ksb.agape.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import pl.ksb.agape.domain.dao.IModificationLoggable;
import pl.ksb.agape.util.ModificationListener;

@Entity
@Table(schema="agape", name="question_addition")
@EntityListeners(ModificationListener.class)
public class QuestionAddition extends ModificationUserDate implements IModificationLoggable, Serializable{

	
	public enum FileType{
		
		IMAGE("Plik graficzny"),
		VIDEO("Plik wideo"),
		AUDIO("Plik audio");
		private String name;
		private FileType(String name){
			this.name = name;
		}
		public String getName(){
			return name;
		}
		
	};
	
	private static final long serialVersionUID = -9169563019165093947L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, precision = 10, scale = 0)
	private Long id;

	@Column(name = "number", length = 10, nullable = false)
	@NotNull
	private Long number;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "file_type")
	private FileType fileType = FileType.IMAGE;
	
	
	@Column(name = "filen_name", length = 200, nullable = false)
	@NotNull
	private String fileName;

	@Column(name = "mime_type", length=20)
	private String mimeType;
	
	@Lob 
	@Column(name = "content", length=20971520)//max 20 MB
	private byte[] content;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="question_id")
	@NotNull
	private Question question;
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled = true;
	
	
	@Column(name="height")
	private Long height = 200l;
	
	
	@Column(name = "description", length=200)
	private String description;

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

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	
	
}
