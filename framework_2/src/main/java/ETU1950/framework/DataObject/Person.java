package ETU1950.framework.DataObject;

import ETU1950.framework.annnotation.MethodAnnotation;

import java.util.Date;

public class Person {
    @MethodAnnotation(url="HelloWorld")
    public String Hello()
    {
        return "hello my name is prisca";
    }
    public void testVariables(int a, float b, Date d){
        System.out.println("hello");
    }


}