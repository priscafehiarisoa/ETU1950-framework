package ETU1950.framework.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileByteStorage {
    public static void main(String[] args) {
        String inputFile = "/Users/priscafehiarisoadama/Desktop/Screenshot 2023-07-17 at 23.04.34.png"; // Chemin vers votre fichier source
        String outputFile = "/Users/priscafehiarisoadama/Desktop/upload/Screenshot 2023-07-17 at 23.04.34.png"; // Chemin vers le fichier de sortie (où le fichier sera stocké sous forme de bytes)

        try {
            // Étape 1: Lire le fichier et le convertir en tableau de bytes
            byte[] fileBytes = readFileToBytes(inputFile);

            // Étape 2: Écrire le tableau de bytes dans un fichier
            writeBytesToFile(fileBytes, outputFile);

            System.out.println("Le fichier a été stocké sous forme de bytes avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour lire un fichier et le convertir en tableau de bytes
    public static byte[] readFileToBytes(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        byte[] fileBytes = new byte[fis.available()];
        fis.read(fileBytes);
        fis.close();
        return fileBytes;
    }

    // Méthode pour écrire un tableau de bytes dans un fichier
    public static void writeBytesToFile(byte[] bytes, String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(bytes);
        fos.close();
    }

    public static void saveFile(File file) throws IOException {
        String output= file.getFile_path()+file.getFile_name();
        writeBytesToFile(file.getFile_byte(), output);
    }

    public static void download(String filePath, String downloadPath) throws IOException {
        byte[] fileBytes = readFileToBytes(filePath);
        writeBytesToFile(fileBytes, downloadPath);
    }
}
