package ETU1950.framework.servlet;

import ETU1950.framework.Mapping;
import ETU1950.framework.ModelView;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

//@WebServlet(name = "FrontServlet", value = "/FrontServlet")
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
//    public void init() throws ServletException {
        //tsy maintsy instancer-na fona fa manjary tsy mandeha
//        String objectPackage="test.";
//        String packageDirectory="/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/java/test";
        String objectPackage=servletConfig.getInitParameter("objectPackage");
        String packageDirectory=servletConfig.getInitParameter("packageDirectory");
        try {

            this.MappingUrls = Mapping.getMethodsHashMapFromPackage(packageDirectory, objectPackage);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException classnotfound)
        {
            System.out.println("oh man an error occured XD ::: "+classnotfound.getMessage()+" \n tsy mety kosa zao eeee");
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        PrintWriter out=response.getWriter();
        String contexts=request.getRequestURI().toString();
        String prefix="/";
        String key=contexts.split(prefix)[contexts.split(prefix).length-1];
//
        if (MappingUrls.containsKey(key)) {
////             Mapping
            Mapping a = MappingUrls.get(key);

            out.println("methods : " + a.getMethods());
            out.println("class: " + a.getClassName());
            out.println("euh");
            try {
                out.println(1);
                // faut verifier s'il y a eu un formulaire
                Class<?> myclass=Class.forName(a.getClassName());
                Object objet=myclass.newInstance();
                Field[] fields=objet.getClass().getDeclaredFields();
                out.println("field : "+fields[0]);
                if(Mapping.checkIfForm(fields,request,out))
                {
                    out.println(2);
                    out.println("box1");
                    Object objets=Mapping.getfromForm(objet,fields,request,out);
                    out.println("box");
                    // print objet
                    out.println(objets.getClass());
                    out.println("lol");
//                    out.println(objets.getClass().getMethod("getNom").invoke(objet));
                    Mapping.showObject(objets,fields,out);
                    out.println("lol2");




                }
                else{
                    out.println(3);

                    String obj=a.callMethod();
                    ModelView mymodel=a.callMethodModelView();
//                set attributes :
                    Set<String> mykey=mymodel.getData().keySet();
                    for (String keys: mykey) {
                        request.setAttribute(keys,mymodel.getData().get(keys));
                    }
//                    request.getRequestDispatcher(obj).forward(request,response);
                    out.println("tsy nety ");
                }







            }
            catch (Exception e)
            {
                out.println("nope");
                out.println(e.getMessage());
                out.println(Arrays.toString(e.getStackTrace()));
                e.printStackTrace();
            }
        }
        else{
            out.println(contexts);
            out.println(key);
            out.println("the url you entered was not found ");
            out.println("would you mind to keep trying XD ");
            out.println("It's funny to write errors like that 🥹");

        }
    }

//    set attributes
}
