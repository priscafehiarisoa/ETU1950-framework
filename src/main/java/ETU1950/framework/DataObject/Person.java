package ETU1950.framework.DataObject;

import ETU1950.framework.annnotation.MethodAnnotation;

public class Person {
    @MethodAnnotation(url="HelloWorld")
    public String Hello()
    {
        return "hello my name is prisca";
    }
}
