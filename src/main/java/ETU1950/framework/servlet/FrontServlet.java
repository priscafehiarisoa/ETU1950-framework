package ETU1950.framework.servlet;

import ETU1950.framework.Mapping;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

//@WebServlet(name = "FrontServlet", value = "/FrontServlet")
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;

    @Override
    public void init() throws ServletException {
        //tsy maintsy instancer-na fona fa manjary tsy mandeha
        String objectPackage="ETU1950.framework.DataObject.";
        String packageDirectory="/Users/priscafehiarisoadama/IdeaProjects/WEBDynamique_S4/ETU1950-framework/src/main/java/ETU1950/framework/DataObject/";
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
        String prefix="/ETU1950_framework_war_exploded/";

        System.out.println(contexts.split(prefix)[0]);
        String key=contexts.split(prefix)[1];

        if (MappingUrls.containsKey(key)) {

            // Mapping
            Mapping a = MappingUrls.get(key);

            out.println("Lucky you ... I have the result : \n here you are ");
            out.println("methods : " + a.getMethods());
            out.println("class: " + a.getClassName());

        }
        else{
            out.println("oh dear : .... poor you .... ");
            out.println("the url you entered was not found ");
            out.println("would you mind to keep trying XD ");
            out.println("It's funny to write errors like that :p");

        }
    }
}
