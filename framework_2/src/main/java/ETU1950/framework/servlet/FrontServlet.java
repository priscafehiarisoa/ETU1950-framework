package ETU1950.framework.servlet;

import ETU1950.framework.Mapping;
import ETU1950.framework.ModelView;
import ETU1950.framework.annnotation.MethodAnnotation;
import ETU1950.framework.exeptions.NotAllowedMethod;
import ETU1950.framework.file.File;
import com.google.gson.Gson;
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
import java.util.*;

import static ETU1950.framework.Mapping.upper;

//@WebServlet(name = "FrontServlet", value = "/FrontServlet")
@MultipartConfig(location = "./")
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;
    String sessionName;
    String profilName;
    Gson gson = new Gson();


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
//    public void init() throws ServletException {
        //tsy maintsy instancer-na fona fa manjary tsy mandeha
//        String objectPackage="test.";
//        String packageDirectory="/Users/priscafehiarisoadama/PhpstormProjects/ETU1950-framework/test-framework2/src/main/java/test";
        String objectPackage=servletConfig.getInitParameter("objectPackage");
        String packageDirectory=servletConfig.getInitParameter("packageDirectory");
        sessionName = servletConfig.getInitParameter("session_name");
        profilName = servletConfig.getInitParameter("profil_name");
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
        HttpSession session = request.getSession();


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
                Class<?> myclass=Class.forName(a.getClassName());
                Method theMethod=myclass.getDeclaredMethod(a.getMethods());
                MethodAnnotation annotation=theMethod.getAnnotation(MethodAnnotation.class);
                Object objet=myclass.newInstance();
                Field[] fields=objet.getClass().getDeclaredFields();
                //1- verifier si la methode est authorisé
                try {
                    Mapping.checkAuthorisation(theMethod, session, profilName);

                // faut verifier s'il y a eu un formulaire
                if(Mapping.checkIfForm(request,out))
                {
                    Object objets=new Object();
                    if(content.contains("multipart")){
                        out.print("multi");
                        objets=traitement_file_upload(request,objet,out);
                    }
                    else{
                        out.println("non multi");

                        objets=Mapping.getForm(objet,fields,request,out);
                        Mapping.showObject(objet,fields,out);
                        out.println ("end");

                    }

                    out.println(objets.getClass());
                    // si la fonction retourne un json
                    if(annotation.restAPI()){

                    }


//                   executer la fonction si retourne un modelview
                    ModelView modelViews=a.callMethod_from_view(request,out,objets);
                    out.println(modelViews.getSessions().size());
                    //traiter les sessions

//
                    out.println(sessionName);
                    out.println(profilName);
                    //set sessions
                    if(modelViews.getSessions().size() > 0){
                        out.println("getSession");
                        session.setAttribute(sessionName, modelViews.getSessions().get(sessionName));
                        session.setAttribute(profilName, modelViews.getSessions().get(profilName));
                    }

                    out.println("setAttributes");
                    //set attribute for datas
                    for (Map.Entry<String, Object> entry : modelViews.getData().entrySet()) {
                        key = (String) entry.getKey();
                        request.setAttribute((String) key, modelViews.getData().get(key));
                        if(modelViews.isJson()){
                            String objectJsoned = this.gson.toJson(modelViews.getData().get(key));
                            out.println("faut transformer en json sprint 13  \n ======== ");
                            out.println(objectJsoned);
                            out.println("===========\n fin json ");
                        }
                    }

                    request.getRequestDispatcher(modelViews.getVue()).forward(request,response);


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
                catch (NotAllowedMethod notAllowedMethod){
                    out.println("==============================");
                    out.println("ERREUR  "+notAllowedMethod.getMessage());
                    out.println("==============================");

                }

            }
            catch (Exception e)
            {
                out.println("ERREUR "+e.getMessage());
                out.println(e.getClass().getName());
                out.println("cause "+e.getCause());
                StackTraceElement[] stackTrace = e.getStackTrace();
                if (stackTrace != null && stackTrace.length > 0) {
                    out.println(String.valueOf(stackTrace[0].getLineNumber()));
                    out.println(stackTrace[0].getFileName());
                }

                e.printStackTrace(out);
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
