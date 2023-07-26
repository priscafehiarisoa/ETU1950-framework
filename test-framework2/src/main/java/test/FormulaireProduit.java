package test;

import ETU1950.framework.ModelView;
import ETU1950.framework.annnotation.MethodAnnotation;
import ETU1950.framework.file.File;
import ETU1950.framework.file.FileByteStorage;
import database.Connection;
import database.core.DBConnection;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class FormulaireProduit {
    String numero_serie;
    String idCategorie;
    double frequenceProcesseur;
    double ram;
    File image;

    public FormulaireProduit(String numero_serie, String idCategorie, double frequenceProcesseur, double ram, File image) {
        this.numero_serie = numero_serie;
        this.idCategorie = idCategorie;
        this.frequenceProcesseur = frequenceProcesseur;
        this.ram = ram;
        this.image = image;
    }

    public FormulaireProduit() {
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public double getFrequenceProcesseur() {
        return frequenceProcesseur;
    }

    public void setFrequenceProcesseur(double frequenceProcesseur) {
        this.frequenceProcesseur = frequenceProcesseur;
    }

    public double getRam() {
        return ram;
    }

    public void setRam(double ram) {
        this.ram = ram;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    @MethodAnnotation(url = "addProduct")
    public ModelView addProduct() throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        ModelView modelView=new ModelView("menu.jsp");
        // atao ao anaty classe produit le fichier ntao upload

        //initialiser la connection
        DBConnection dbConnection= Connection.getConnection();

        Produit p=new Produit(this.getNumero_serie(),this.getIdCategorie(),this.getFrequenceProcesseur(),this.getRam(),this.getImage().getFile_path()+this.getImage().getFile_name());
        p.save(dbConnection);
        File image=this.getImage();
        FileByteStorage.saveFile(image);
        //fermer la connection
        Connection.safe_close_connection(dbConnection);

        return modelView;
    }

    @MethodAnnotation(url = "loadAdd")
    public ModelView loadInsertProduit() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ModelView modelView=new ModelView("insertProduit.jsp");
        //initialiser la connection
        DBConnection dbConnection= Connection.getConnection();
        //lister les categories
        List<Categorie> categories=new Categorie().transformation(new Categorie().getAll(dbConnection));
        modelView.addItem("categorie",categories);
        //fermer la connection
        Connection.safe_close_connection(dbConnection);
        return modelView;
    }

    @MethodAnnotation(url = "listProduct")
    public ModelView listerProduit() throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //initialise la connection
        DBConnection dbConnection=Connection.getConnection();
        ModelView modelView=new ModelView("listeProduit.jsp");
        List<Produit> produits=new Produit().transformation(new Produit().getAll(dbConnection));
        modelView.addItem("produits",produits);
        //fermer la connection
        Connection.safe_close_connection(dbConnection);
        return modelView;
    }


}
