package pl.ksb.agape.view.bean.tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.richfaces.event.FileUploadEvent;

import pl.ksb.agape.domain.model.dto.UploadedFile;

@ManagedBean
@SessionScoped
public class FileUploaderBean implements Serializable{

	private static final long serialVersionUID = -2457512267612727496L;
	private List<UploadedFile> uploadedFiles = new ArrayList<UploadedFile>();

	public void listener(FileUploadEvent event) throws Exception {
		org.richfaces.model.UploadedFile item = event.getUploadedFile();
		UploadedFile file = new UploadedFile(item.getName(), item.getData(), item.getContentType());
		uploadedFiles.add(file);

	}
	
	public List<UploadedFile> getUploadedFiles() {
		return uploadedFiles;
	}
	public void clearList(){
		uploadedFiles.clear();
	}

}
