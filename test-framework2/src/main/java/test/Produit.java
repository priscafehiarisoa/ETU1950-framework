package test;

import database.core.DBObject;

import java.util.ArrayList;
import java.util.List;

public class Produit extends DBObject {
    String numero_serie;
    String idCategorie;
    double frequenceProcesseur;
    double ram;

    String imagePath;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Produit(String numero_serie, String idCategorie, double frequenceProcesseur, double ram, String imagePath) {
        this.numero_serie = numero_serie;
        this.idCategorie = idCategorie;
        this.frequenceProcesseur = frequenceProcesseur;
        this.ram = ram;
        this.imagePath = imagePath;
    }

    public Produit() {
    }

    public List<Produit> transformation(List<Object> objets){
        List<Produit> anomalie=new ArrayList<>();
        for (int i = 0; i < objets.size(); i++) {
            anomalie.add((Produit) objets.get(i));
        }
        return anomalie;
    }




}
