package test;

import database.core.DBObject;

import java.util.ArrayList;
import java.util.List;

public class Categorie extends DBObject {
    String nomCategorie;

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Categorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Categorie() {
    }
    public List<Categorie> transformation(List<Object> objets){
        List<Categorie> anomalie=new ArrayList<>();
        for (int i = 0; i < objets.size(); i++) {
            anomalie.add((Categorie) objets.get(i));
        }
        return anomalie;
    }
}
