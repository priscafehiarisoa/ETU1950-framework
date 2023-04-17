package test;
import ETU1950.framework.annnotation.MethodAnnotation;
import ETU1950.framework.ModelView;

public class test {
    public static void main(String[] args) {

    }
//    @Override
    @MethodAnnotation(url="hurray")
    public static ModelView tester ()
    {
        return new ModelView("test.jsp");
    }

    public void teste() {

    }
}