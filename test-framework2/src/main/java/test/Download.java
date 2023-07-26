package test;

import ETU1950.framework.ModelView;
import ETU1950.framework.annnotation.MethodAnnotation;
import ETU1950.framework.file.FileByteStorage;

import java.io.IOException;
import java.sql.SQLException;

public class Download {
    String filepath;

    public Download(String filepath) {
        this.filepath = filepath;
    }

    public Download() {
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @MethodAnnotation(url = "download")
    public ModelView download() throws SQLException, IOException {

        String downloadpath="/Users/priscafehiarisoadama/Desktop/downloaded/";
        String [] files=this.getFilepath().split("/");
        String filename=files[files.length-1];
        byte[] fileBytes = FileByteStorage.readFileToBytes(this.getFilepath());
        FileByteStorage.writeBytesToFile(fileBytes, downloadpath+filename);
//        FileByteStorage.download(imagePath, downloadpath);
        return new ModelView("menu.jsp");
    }
}
