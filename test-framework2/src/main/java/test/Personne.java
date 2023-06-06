package test;
import ETU1950.framework.annnotation.MethodAnnotation;
import ETU1950.framework.ModelView;

import java.sql.Date;

public class Personne {
    String nom;
    String prenom;
    Date birth;
    int number;
    Integer numbers;

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public void setBirth(String birth) {
        this.birth = Date.valueOf(birth);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }public void setNumber(String number) {
        this.number =  Integer.parseInt(number);
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }public void setNumbers(String number) {
        this.numbers = Integer.parseInt(number);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @MethodAnnotation(url="getTest")
    public void test ()
    {
        System.out.println("test");
    }

    @MethodAnnotation(url="getform")
    public ModelView save(){
        ModelView model=new ModelView("datas.jsp");
        model.addItem("personne",this);
        return model;
    }
}