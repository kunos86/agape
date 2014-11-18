package pl.ksb.agape.domain.model.dto;

public class UploadedFile {

	private String name;
	private byte[] content;
	private String contentType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public UploadedFile() {
		super();
	}

	public UploadedFile(String name, byte[] content, String contentType) {
		super();
		this.name = name;
		this.content = content;
		this.contentType = contentType;
	}

}
