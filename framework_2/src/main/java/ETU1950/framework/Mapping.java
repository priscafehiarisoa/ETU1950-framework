package ETU1950.framework;

//import ETU1950.framework.DataObject.Person;
import ETU1950.framework.annnotation.MethodAnnotation;
import ETU1950.framework.exeptions.Method_doesnt_match;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Mapping {
    String className;
    String methods;

    public Mapping(String className, String methods) {
        this.className = className;
        this.methods = methods;
    }

    public Mapping() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public static HashMap<String, Mapping> getMethodsHashMapFromPackage(String packageDirectory, String ObjectPackage) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        HashMap<String, Mapping> mapping = new HashMap<>();

        String[] classes = getClassList(packageDirectory);

        for (int i = 0; i < classes.length; i++) {
            System.out.println("name: " + classes[i]);
            System.out.println("package directory: "+packageDirectory);
            System.out.println("object package"+ ObjectPackage);
            Class<?> tempClass = Class.forName(ObjectPackage + classes[i]);
            Object obj = tempClass.newInstance();
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (int j = 0; j < methods.length; j++) {
                if (methods[j].isAnnotationPresent(MethodAnnotation.class)) {
                    String url = methods[j].getAnnotation(MethodAnnotation.class).url();
                    String className = ObjectPackage + classes[i];
                    String methodName = methods[j].getName();
                    mapping.put(url, new Mapping(className, methodName));
                }
            }
        }
        return mapping;
    }

    public static String[] getClassList(String directoryPath) {
        File file = new File(directoryPath);
//        ty zany afaka simplifier-na otran'zao
//        de filtrena fotsiny le .java avy eo
        File[] f = file.listFiles();
        FilenameFilter textFilefilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
//        String lowercaseName = name.toLowerCase();
                if (name.endsWith(".java")) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        String[] listefile = file.list(textFilefilter);
        for (int i = 0; i < listefile.length; i++) {
            listefile[i] = listefile[i].split(".java")[0];
        }
        return listefile;
    }

    public String callMethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> tempClass = Class.forName(this.getClassName());
        Object obj = tempClass.newInstance();
        ModelView other = (ModelView) obj.getClass().getMethod(this.getMethods()).invoke(obj);
        return other.getVue();
    }

    public ModelView callMethodModelView(HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> tempClass = Class.forName(this.getClassName());
        Object obj = tempClass.newInstance();
        ModelView model= (ModelView) obj.getClass().getMethod(this.getMethods()).invoke(obj);
        model.setAttributes(request);
        return model;
    }

    //    call methods on sprint 8
    public ModelView callMethodModelView(String [] attibuts,PrintWriter out,HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Method_doesnt_match, NoSuchMethodException, InvocationTargetException, ParseException {
        Class<?> tempClass = Class.forName(this.getClassName());
        Object obj = tempClass.newInstance();
        Parameter[] parametresFonctions =this.get_all_arguments_from_function(obj);
        Class<?>[] params_types=new Class<?>[parametresFonctions.length];
        Object [] argument=new Object[parametresFonctions.length];

        //obtenir les types des paramettres
        for (int i = 0; i < parametresFonctions.length; i++) {
            params_types[i]=parametresFonctions[i].getType();
        }

        if(parametresFonctions.length== attibuts.length){
            //creer l'objet pour l'argument de la fonction
            for (int i = 0; i < params_types.length; i++) {
                argument[i]=convertString(attibuts[i],params_types[i]);
                out.println("attributs : "+attibuts[i]);
            }
            Method methode= obj.getClass().getMethod(this.getMethods(),params_types);
            ModelView m=(ModelView) methode.invoke(obj, argument);
            m.setAttributes(request);
            return m;
        }
        else{
            out.println("call else");
            throw new Method_doesnt_match(parametresFonctions.length, attibuts.length);
        }

    }

    public static String upper(String toupper) {
        return (toupper.substring(0, 1)).toUpperCase() + toupper.substring(1, toupper.length());
    }

    public static boolean checkIfForm( HttpServletRequest request, PrintWriter out) {
        Enumeration<String> parameterNames = request.getParameterNames();
        if(parameterNames.hasMoreElements()){
            return true;
        }
        return false;
    }

//    maka ny avy any anaty form raha mifanaraka @ le class
    public  Object getfromForm(Object objet, Field[] fields, HttpServletRequest request, PrintWriter out) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Object myobject=null;
        out.println(6);

        for (int i = 0; i < fields.length; i++) {
            out.println(7);

            Object argument = request.getParameter(fields[i].getName());
            out.println(fields[i].getType());
            Method method = objet.getClass().getMethod("set" + upper(fields[i].getName()), String.class);
            out.println(9);
            method.invoke(objet, argument);
            out.println(11);
//            out.println(objet.getClass().getMethod("set"+upper(fields[i].getName())).invoke(objet));
        }
        out.println(10);


        return objet;
    }
    public ModelView callMethod_from_view(HttpServletRequest request,PrintWriter out,Object object) throws ClassNotFoundException, ParseException, Method_doesnt_match, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Enumeration<String> parameterNames = request.getParameterNames();

        Parameter[] parametres = this.get_all_arguments_from_function(object);
        String [] attributs=new String[parametres.length];
        int index=0;
        if(parametres.length>0){
            while (parameterNames.hasMoreElements()){
                for (int i = 0; i < parametres.length; i++) {
                    if(parameterNames.nextElement().contains("[]")){

                    }
                    else if(parameterNames.nextElement().equals(parametres[i].getName())){
                        attributs[index]=request.getParameter(parametres[i].getName());
                        index++;
                    }
                }
            }
        }
        Parameter[] parametresFonctions =this.get_all_arguments_from_function(object);
        Class<?>[] params_types=new Class<?>[parametresFonctions.length];
        Object [] argument=new Object[parametresFonctions.length];

        //obtenir les types des paramettres
        for (int i = 0; i < parametresFonctions.length; i++) {
            params_types[i]=parametresFonctions[i].getType();
        }
        if(parametresFonctions.length== attributs.length){
            //creer l'objet pour l'argument de la fonction
            for (int i = 0; i < params_types.length; i++) {
                out.println("parametres types : "+params_types[i]);
                argument[i]=convertString(attributs[i],params_types[i]);
                out.println("attributs : "+attributs[i]);
            }
            Method methode= object.getClass().getMethod(this.getMethods(),params_types);
            ModelView model=(ModelView) methode.invoke(object, argument);
           model.setAttributes(request);
            return model;
        }
        else{
            out.println("call else");
            throw new Method_doesnt_match(parametresFonctions.length, attributs.length);
        }
    }

    public ModelView callMethod_from_view_2(HttpServletRequest request,PrintWriter out,Object object) throws ClassNotFoundException, ParseException, Method_doesnt_match, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Enumeration<String> parameterNames = request.getParameterNames();
        Parameter[] parametres = this.get_all_arguments_from_function(object);
        Class<?>[] params_types=new Class<?>[parametres.length];
        Object [] argument=new Object[parametres.length];
        //obtenir les types des paramettres
        for (int i = 0; i < parametres.length; i++) {
            params_types[i]=parametres[i].getType();
        }
        String [] attributs=new String[parametres.length];
        int index=0;
        if(parametres.length>0){
            while (parameterNames.hasMoreElements()){
                for (int i = 0; i < parametres.length; i++) {
                    if(parameterNames.nextElement().contains("[]")){

//                        if()
                    }
                    else if(parameterNames.nextElement().equals(parametres[i].getName())){
                        attributs[index]=request.getParameter(parametres[i].getName());
                        index++;
                    }
                }
            }
        }


        if(parametres.length== attributs.length){
            //creer l'objet pour l'argument de la fonction
            for (int i = 0; i < params_types.length; i++) {
                out.println("parametres types : "+params_types[i]);
                argument[i]=convertString(attributs[i],params_types[i]);
                out.println("attributs : "+attributs[i]);
            }
            Method methode= object.getClass().getMethod(this.getMethods(),params_types);
            ModelView model=(ModelView) methode.invoke(object, argument);
            model.setAttributes(request);
            return model;
        }
        else{
            out.println("call else");
            throw new Method_doesnt_match(parametres.length, attributs.length);
        }
    }


    public static void showObject(Object objet, Field[] fields,PrintWriter out) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (int i = 0; i < fields.length; i++) {
            out.println(objet.getClass().getMethod("get"+upper(fields[i].getName())).invoke(objet));
        }

//        System.out.println(objet.getClass().getMethod("get"+));
    }

    public static Object getForm(Object objet, Field[] fields, HttpServletRequest request, PrintWriter out) throws NoSuchFieldException, NoSuchMethodException {
        Enumeration<String> attributesName = request.getParameterNames();
        while (attributesName.hasMoreElements()) {
            String attributeName = attributesName.nextElement();
            String attributeValue = (String) request.getParameter(attributeName);
            Field field = objet.getClass().getDeclaredField(attributeName);
            if (field != null) {
                Method setMethode = objet.getClass().getDeclaredMethod("set" + upper(field.getName()), field.getType());
            }
        }
        return new Object();
    }


    public Parameter[] get_all_arguments_from_function(Object object){
        Method []m= object.getClass().getDeclaredMethods();
        Parameter[] param=null;
        for (int i = 0; i < m.length; i++) {
            if(m[i].getName().equals(this.getMethods())) {
                param=m[i].getParameters();
                return param;
            }
        }
        return param;
    }

    // traitement des liens
    public static String[] get_Parameters_from_url(String context)
    {
        //split numero 1
        String prefix1="/";
        String[] split2=context.split(prefix1);

//        //split numero 2
        String prefix2="!";
        String s=split2[split2.length-1];
        String[] result=s.split(prefix2);
        return result;
//        return split2;
    }

    public static String getKey(String context){
        String []keys=get_Parameters_from_url(context);
        return keys[0];
    }

    // maka an'le attributs avy any anaty url
//    le ao afaran'ny key
    public static String[] get_parameters_from_url(String context){
        //traitement ana liens
        String []keys=get_Parameters_from_url(context);
        //copier-na le lien de asorina le indice voalohany
        return Arrays.copyOfRange(keys, 1, keys.length);
    }


/**----------------------------GLOBAL---------------------------*/
public static Object convertString(String value, String targetType) throws ParseException, ParseException {
    switch (targetType) {
        case "java.lang.String":
            return value;
        case "int":
            return Integer.parseInt(value);
//    } else if (targetType.equals("java.util.Date")) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        return dateFormat.parse(value);
        case "double":
            return Double.parseDouble(value);
        case "float":
            return Float.parseFloat(value);
        case "java.lang.Integer":
            return Integer.parseInt(value);
        case "long":
            return Long.parseLong(value);
        case "java.math.BigDecimal":
            return new BigDecimal(value);
        case "java.math.BigInteger":
            return new BigInteger(value);
        case "boolean":
            return Boolean.parseBoolean(value);
        case "byte":
            return Byte.parseByte(value);
        case "char":
            if (value.length() == 1) {
                return value.charAt(0);
            } else {
                throw new IllegalArgumentException("La chaîne doit contenir un seul caractère pour la conversion en char.");
            }
        default:
            throw new IllegalArgumentException("Type de conversion non pris en charge.");
    }
}

    public static Object convertString(String value, Class<?> targetType) throws ParseException, ParseException {
        switch (targetType.getName()) {
            case "java.lang.String":
                return value;
            case "int":
                return Integer.parseInt(value);
            case "java.util.Date":
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.parse(value);
            case "java.sql.Date":
                SimpleDateFormat dateFormat_sql = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = dateFormat_sql.parse(value);
                return new java.sql.Date(utilDate.getTime());
            case "double":
                return Double.parseDouble(value);
            case "float":
                return Float.parseFloat(value);
            case "java.lang.Integer":
                return Integer.parseInt(value);
            case "long":
                return Long.parseLong(value);
            case "java.math.BigDecimal":
                return new BigDecimal(value);
            case "java.math.BigInteger":
                return new BigInteger(value);
            case "boolean":
                return Boolean.parseBoolean(value);
            case "byte":
                return Byte.parseByte(value);
            case "char":
                if (value.length() == 1) {
                    return value.charAt(0);
                } else {
                    throw new IllegalArgumentException("La chaîne doit contenir un seul caractère pour la conversion en char.");
                }
            default:
                throw new IllegalArgumentException("Type de conversion non pris en charge.");
        }
    }

    public static Object convertPart(Part part,Class<?> classe,HttpServletRequest request) throws IOException, ParseException {
        switch (classe.getName()) {
            case "java.lang.String":
                return request.getParameter(part.getName());
            case "int":
                return Integer.parseInt(request.getParameter(part.getName()));
            case "java.util.Date":
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.parse(request.getParameter(part.getName()));
            case "java.sql.Date":
                SimpleDateFormat dateFormat_sql = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = dateFormat_sql.parse(request.getParameter(part.getName()));
                return new java.sql.Date(utilDate.getTime());
            case "double":
                return Double.parseDouble(request.getParameter(part.getName()));
            case "float":
                return Float.parseFloat(request.getParameter(part.getName()));
            case "java.lang.Integer":
                return Integer.parseInt(request.getParameter(part.getName()));
            case "long":
                return Long.parseLong(request.getParameter(part.getName()));
            case "java.math.BigDecimal":
                return new BigDecimal(request.getParameter(part.getName()));
            case "java.math.BigInteger":
                return new BigInteger(request.getParameter(part.getName()));
            case "boolean":
                return Boolean.parseBoolean(request.getParameter(part.getName()));
            case "byte":
                return Byte.parseByte(request.getParameter(part.getName()));
            case "ETU1950.framework.file.File":
                InputStream inputStream=part.getInputStream();
                return new ETU1950.framework.file.File(part.getSubmittedFileName(), "./upload",inputStream.readAllBytes());
            case "char":
                if (request.getParameter(part.getName()).length() == 1) {
                    return request.getParameter(part.getName()).charAt(0);
                } else {
                    throw new IllegalArgumentException("La chaîne doit contenir un seul caractère pour la conversion en char.");
                }
            default:
                throw new IllegalArgumentException("Type de conversion non pris en charge.");
        }
    }
    public static Object[] convertParts(Part part,Class<?> classe,HttpServletRequest request,PrintWriter out ) throws ParseException, IOException {
        String [] params=request.getParameterValues(part.getName());
        Object [] obj=new Object[params.length];
        out.println("pooiiuuy");
        out.println(classe.getName());
    switch (classe.getName()) {
            case "java.lang.String[]":
                out.println("eto");

                return  params;
            case "int":
                for (int i = 0; i < params.length; i++) {
                    obj[i]=Integer.parseInt(params[i]);
                }
            case "java.util.Date":
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (int i = 0; i < params.length; i++) {
                    obj[i]=dateFormat.parse(params[i]);
                }
            case "java.sql.Date":
                SimpleDateFormat dateFormat_sql = new SimpleDateFormat("yyyy-MM-dd");
                for (int i = 0; i < params.length; i++) {

                     java.util.Date utilDate =  dateFormat_sql.parse(params[i]);
                    obj[i]= new java.sql.Date(utilDate.getTime());
                }

            case "double":
                for (int i = 0; i < params.length; i++) {
                    obj[i]=Double.parseDouble(params[i]);
                }
            case "float":
                for (int i = 0; i < params.length; i++) {
                    obj[i]=Float.parseFloat(params[i]);
                }
            case "java.lang.Integer":
                for (int i = 0; i < params.length; i++) {
                    obj[i]=Integer.parseInt(params[i]);
                }
            case "long":
                for (int i = 0; i < params.length; i++) {
                    obj[i]=Long.parseLong(params[i]);
                }
            case "java.math.BigDecimal":
                for (int i = 0; i < params.length; i++) {
                    obj[i]=new BigDecimal(params[i]);
                }
            case "java.math.BigInteger":
                for (int i = 0; i < params.length; i++) {
                    obj[i]=new BigInteger(params[i]);
                }
            case "boolean":
                for (int i = 0; i < params.length; i++) {
                    obj[i]=Boolean.parseBoolean(params[i]);
                }
            case "byte":
                for (int i = 0; i < params.length; i++) {
                    obj[i]=Byte.parseByte(params[i]);
                }
        }
        return obj;
    }



}
