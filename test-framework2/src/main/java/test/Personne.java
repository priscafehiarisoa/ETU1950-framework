package test;
import ETU1950.framework.annnotation.MethodAnnotation;
import ETU1950.framework.ModelView;
import ETU1950.framework.annnotation.Singleton;
import ETU1950.framework.file.File;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Date;
@Singleton(isSingleton = true)

public class Personne {
    String nom;
    String  prenom;
    Date birth;
    int number;
    int inst=0;
    Integer numbers;
    
    File photos;

    int [] tec;
    String [] it;

    public int getInst() {
        return inst;
    }

    public void setInst(int inst) {
        this.inst = inst;
    }

    public String[] getIt() {
        return it;
    }

    public void setIt(String[] it) {
        this.it = it;
    }

    public int[] getTec() {
        return tec;
    }

    public void setTec(int[] tec) {
        this.tec = tec;
    }

    public File getPhotos() {
        return photos;
    }

    public void setPhotos(File photos) {
        this.photos = photos;
    }

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
        this.setNumber(this.getNumber()+1);
        ModelView model=new ModelView("datas.jsp");
        model.addItem("personne",this);
        setInst(getInst()+1);

        return model;
    }
    @MethodAnnotation(url="getform2")
    public ModelView saved(Date birth2){
        ModelView model=new ModelView("datas3.jsp");
        setInst(getInst()+1);
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

    public static void main(String[] args) {

    }
}