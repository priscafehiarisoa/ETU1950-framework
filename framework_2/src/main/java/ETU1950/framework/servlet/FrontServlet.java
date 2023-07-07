package ETU1950.framework.servlet;

import ETU1950.framework.Mapping;
import ETU1950.framework.ModelView;
import ETU1950.framework.file.File;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import static ETU1950.framework.Mapping.upper;

//@WebServlet(name = "FrontServlet", value = "/FrontServlet")
@MultipartConfig(location = "./")
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
        String content=request.getContentType();

        String contexts=request.getRequestURI().toString();
        String prefix="/";
        out.println("context "+contexts);
        String key=contexts.split(prefix)[contexts.split(prefix).length-1];
        out.println("mapping key "+Mapping.getKey(contexts));
        out.println("content : "+content);

        // attributs de la fonction
        String[] attributes =Mapping.get_parameters_from_url(contexts);
        if(attributes.length>0){
            key=Mapping.getKey(contexts);
        }

        out.println("afficher-na ny attributs "+ attributes.length);

        if (MappingUrls.containsKey(key)) {
////             Mapping
            Mapping a = MappingUrls.get(key);

            out.println("methods : " + a.getMethods());
            out.println("class: " + a.getClassName());

            try {
                out.println(1);
                // faut verifier s'il y a eu un formulaire
                Class<?> myclass=Class.forName(a.getClassName());
                Object objet=myclass.newInstance();
                Field[] fields=objet.getClass().getDeclaredFields();
                if(Mapping.checkIfForm(request,out))
                {
                    Object objets=new Object();
                    if(content.contains("multipart")){
                        objets=traitement_file_upload(request,objet,out);
                    }
                    else{
                        objets=Mapping.getForm(objet,fields,request,out);
                    }

                    out.println(objets.getClass());
//                   executer la fonction
                    ModelView modelViews=a.callMethod_from_view(request,out,objets);
//                    request.getRequestDispatcher(modelViews.getVue()).forward(request,response);


                }else{
                    out.println(3);
                    ModelView modelView=new ModelView();
                    if (attributes.length>0){
                        modelView= a.callMethodModelView(attributes,out,request);
                    }
                    else{
                    modelView=a.callMethodModelView(request);
                    }
//                set attributes :
                    Set<String> mykey=modelView.getData().keySet();
                    for (String keys: mykey) {
                        request.setAttribute(keys,modelView.getData().get(keys));
                    }
                    //afficher la page
                    request.getRequestDispatcher(modelView.getVue()).forward(request,response);
                    out.println("tsy nety ");
                }

            }
            catch (Exception e)
            {
//                out.println("nope");
//                out.println(e.getMessage());
//                out.println(Arrays.toString(e.getStackTrace()));
//                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        else{
            out.println(contexts);
            out.println("keys "+key);
            out.println("the url you entered was not found ");
            out.println("would you mind to keep trying XD ");
            out.println("It's funny to write errors like that 🥹");

        }
    }


    public static Object traitement_file_upload(HttpServletRequest request, Object object,PrintWriter out) throws ServletException, IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, ParseException, ClassNotFoundException {
       //maka an'le champ rehetra ao @ form

        Collection<Part> collect=request.getParts();
        Field [] fields=object.getClass().getDeclaredFields();
        if(!collect.isEmpty()){
            for (Part part:collect) {
                    for (int i = 0; i < fields.length; i++) {
                        String pa=part.getName().replace("[]","");
                        if(fields[i].getName().equals(part.getName())){
                            Class<?> type=fields[i].getType();
                            out.println("upload type : "+type.getName());
                            Method methods = object.getClass().getMethod("set" + upper(fields[i].getName()), type);
                            Object setted=Mapping.convertPart(part,type,request);
                            methods.invoke(object,setted);
                        }
//                        else
//                            if(part.getName().contains("[]")&& fields[i].getName().equals(pa)){
//                           Class<?> type=fields[i].getType();
//                           out.println("::::"+type.getName());
//                            Method methods = object.getClass().getMethod("set" + upper(fields[i].getName()), type);
//                            Object[] setted=Mapping.convertParts(part,type,request,out);
//                            out.println(177);
//                            for (Object ob: setted
//                                 ) {
//                                out.println((String) ob);
//                            }
//                            methods.invoke(object,setted);
//                            out.print("lalala");
//                        }
                    }
            }
        }
        return object;
    }

//    set attributes
}
