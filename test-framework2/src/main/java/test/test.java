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

    @MethodAnnotation(url="getData")
    public static ModelView getDataAndRedirect()
    {
        ModelView model=new ModelView("datas.jsp");
        model.addItem("nombre",12);
        return model;
    }

    public void teste() {

    }
}