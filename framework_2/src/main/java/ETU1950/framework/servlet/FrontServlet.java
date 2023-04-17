package ETU1950.framework.servlet;

import ETU1950.framework.Mapping;
import ETU1950.framework.ModelView;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

//@WebServlet(name = "FrontServlet", value = "/FrontServlet")
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;

    @Override
    public void init() throws ServletException {
        //tsy maintsy instancer-na fona fa manjary tsy mandeha
        String objectPackage="test.";
        String packageDirectory="/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/java/test";
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

        if (MappingUrls.containsKey(key)) {
//             Mapping
            Mapping a = MappingUrls.get(key);

            out.println("methods : " + a.getMethods());
            out.println("class: " + a.getClassName());
            out.println("euh");
            try {
                String obj=a.callMethod();
                out.println("mety");
                out.println(obj);
//                response.sendRedirect(obj);
                request.getRequestDispatcher(obj).forward(request,response);
            }
            catch (Exception e)
            {
                out.println("nope");
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
}
