/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etu2004.framework.servlet;

import com.google.gson.Gson;
import ETU1950.framework.Mapping;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import utilitaire.ModelView;
import utilitaire.MyAnnotation;
import utilitaire.Utile;
import utilitaire.restApi;


@WebServlet
@MultipartConfig(location = "./")
public class FrontServlet extends HttpServlet {
    
    String profilName;
    String sessionName;
    Gson gson = new Gson();
    HashMap<String, etu2004.framework.Mapping> MappingUrls;
    HashMap<String, Object> instance_list;
   
    @Override
    public void init() throws ServletException {
        try {      
            String packageName = getInitParameter("package_name");
            setMappingUrls(Utile.getAllHashMap(packageName));
            setInstance_list(Utile.getAllSengletonClasses(packageName));
            this.profilName = getInitParameter("profil_name");
            this.sessionName = getInitParameter("session_name");
        } catch (Exception ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        boolean isRestMethode = false;
        
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();

        String uri = request.getRequestURI();
        String context = request.getContextPath();
        String nomMethode = uri.substring(context.length()+1);
        String packageName = getInitParameter("package_name");       
        String nomDeClasse = packageName+"."+(String) MappingUrls.get(nomMethode).getClassName();
        Class cl = java.lang.Class.forName(nomDeClasse);
        
        Object objet = instance_list.get(nomDeClasse);
        
        if(objet == null){
            objet = cl.newInstance();
            MyAnnotation annotation = (MyAnnotation) cl.getDeclaredAnnotation(MyAnnotation.class);
            if(annotation.isSegleton()){
                instance_list.put(nomDeClasse, objet);
            }
        }
        
        String method = (String) MappingUrls.get(nomMethode).getMethod();
        Method methode = null;
        Method[] methodes = objet.getClass().getDeclaredMethods();
        
        for(Method m : methodes){
            if(m.getName().contains(method)){
                methode = m;
                break;
            }
        }
        
        if(methode.getDeclaredAnnotation(restApi.class) != null) isRestMethode = true;
        
        Object retour = new Object(); //instance à l'objet servant de modelview 
        
        String contentType = request.getContentType(); //obtient le type de la requête
        
        Utile.checkAuthorisation(methode, session, profilName);
        
        //maka ny donnée anaty session pour une méthode contenant l'annotaion session, izany hoe méthode mila session
        Utile.setUserDataSession(objet, methode, request);
        
        //ito le mitraiter hoe file sa tsy file
        if(contentType != null) retour = Utile.request_multipart_traitor(objet, retour, request, methode); //si c'est du type 'multipart/form-data'
        //ito ndray le ze tina
        else if(contentType == null) retour = Utile.request_traitor(objet, retour, request, methode); //sinon
       
        if(!isRestMethode){
            //not a rest method
            ModelView m = (ModelView) retour;
            String key = "";
            //get key
            for (Map.Entry<String, Object> entry : m.getData().entrySet()) {
                key = (String) entry.getKey();
            }
            if(m.getSessions().size() > 0){
                session.setAttribute(this.sessionName, m.getSessions().get(this.sessionName));
                session.setAttribute(this.profilName, m.getSessions().get(this.profilName));
            }
            if(m.isIsJSON()){
                String objectJsoned = this.gson.toJson(m.getData().get(key));
                out.println(objectJsoned);
            }
            else{
                request.setAttribute((String) key, m.getData().get(key));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/"+((ModelView) retour).getView());
                requestDispatcher.forward(request,response);
            }
        }
        else{
           //rest method
           String objectJsoned = this.gson.toJson(retour);
           out.println(objectJsoned);
        }    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException | SAXException | ParserConfigurationException ex) {
               out.println(ex);
        } catch (Exception ex) {
            out.println(ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
           out.println(ex);
        } catch (SAXException | ParserConfigurationException ex) {
            out.println(ex);
        } catch (Exception ex) {
            out.println(ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public HashMap<String, Object> getInstance_list() {
        return instance_list;
    }

    public void setInstance_list(HashMap<String, Object> instance_list) {
        this.instance_list = instance_list;
    }
    
    public HashMap<String, Mapping> getMappingUrls() {
        return MappingUrls;
    }

    public void setMappingUrls(HashMap<String, Mapping> MappingUrls) {
        this.MappingUrls = MappingUrls;
    }
}