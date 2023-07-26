package test;
import ETU1950.framework.annnotation.MethodAnnotation;
import ETU1950.framework.ModelView;
import ETU1950.framework.file.File;
import database.Connection;
import database.core.DBConnection;
import database.core.DBObject;
import database.exception.SQL.AttributeMissingException;
import database.exception.SQL.AttributeTypeNotExistingException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Personne extends DBObject {
    String nom;
    String  prenom;
    Date birth;
    int number;
    Integer numbers;

    public Personne(String nom, String prenom, Date birth, int number, Integer numbers) {
        this.nom = nom;
        this.prenom = prenom;
        this.birth = birth;
        this.number = number;
        this.numbers = numbers;
    }

    public Personne() {
        setNom("");
        setPrenom("");
        setBirth(Date.valueOf(LocalDate.now()));
        setNumber(0);
        setNumbers(0);

    }
    //    File photos;
//
//    int [] tec;
//    String [] it;
//
//    public String[] getIt() {
//        return it;
//    }
//
//    public void setIt(String[] it) {
//        this.it = it;
//    }
//
//    public int[] getTec() {
//        return tec;
//    }
//
//    public void setTec(int[] tec) {
//        this.tec = tec;
//    }
//
//    public File getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(File photos) {
//        this.photos = photos;
//    }

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

    @MethodAnnotation(url="getform", auth="admin")
    public ModelView save(){
        ModelView model=new ModelView("datas.jsp");
        model.addItem("personne",this);
        return model;
    }
    @MethodAnnotation(url="getform2")
    public ModelView saved(Date birth2){
        ModelView model=new ModelView("datas3.jsp");
        model.addItem("personne",this);
        model.addItem("birth2",birth2);
        return model;
    }

    @MethodAnnotation(url="method_variables")
    public ModelView treatSprint8(int a,int b){
        ModelView model=new ModelView("datas2.jsp");
        model.addItem("a",a);
        model.addItem("b",b);
        return model;
    }
    public void testVariables(int a, float b, java.util.Date d){
        System.out.println("hello");
    }
    @MethodAnnotation(url="log_in")
    public ModelView login() throws SQLException, AttributeTypeNotExistingException, AttributeMissingException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

        ModelView m = new ModelView("menu.jsp");
        m.addSession("isConnected", this);
        m.addSession("profil", "admin");
        m.addItem("profile", this);

        DBConnection dbConnection= Connection.getConnection();

//        //partie categorie
//        Categorie categorie=new Categorie();
//        categorie.createTable(dbConnection);
//        List<Categorie> categories=new ArrayList<>();
//        categories.add(new Categorie("Ultrabooks"));
//        categories.add(new Categorie("Gaming"));
//        categories.add(new Categorie("Chromebooks"));
//        categories.add(new Categorie("Business Laptops"));
//        categories.add(new Categorie("Consumer Laptops"));
//
//        for (int i = 0; i < categories.size() ; i++) {
//            categories.get(i).save(dbConnection);
//        }
//
//        //partie produit
//        Produit produit=new Produit();
//        produit.createTable(dbConnection);

        dbConnection.commit();
        dbConnection.close();
        return m;
    }
    @MethodAnnotation(url="list_emp" )
    public ModelView list_emp(){
        ModelView m = new ModelView("index.jsp");
        m.addSession("isConnected", this);
        m.addSession("profil", "tsotra");
        m.addItem("profile", this);
        m.addItem("test", this);
        int []p=new int[]{1,2,1,2,1,2,12,1};
        m.addItem("tab",p );
        m.setJson(true);
        return m;
    }


    public static void main(String[] args) throws SQLException, AttributeTypeNotExistingException, AttributeMissingException {
        DBConnection dbConnection= Connection.getConnection();
        Personne p = new Personne();
        p.createTable(dbConnection);

        dbConnection.commit();
        dbConnection.close();
    }
}