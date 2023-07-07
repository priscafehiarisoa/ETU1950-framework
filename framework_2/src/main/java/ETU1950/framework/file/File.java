package ETU1950.framework.file;

public class File {
    String file_name;
    String file_path;
    byte [] file_byte;

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public byte[] getFile_byte() {
        return file_byte;
    }

    public void setFile_byte(byte[] file_byte) {
        this.file_byte = file_byte;
    }

    public File(String file_name, String file_path, byte[] file_byte) {
        this.file_name = file_name;
        this.file_path = file_path;
        this.file_byte = file_byte;
    }

}
