package ETU1950.framework;

import ETU1950.framework.annnotation.MethodAnnotation;
import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;

public class Mapping {
    String className;
    String methods;

    public Mapping(String className, String methods) {
        this.className = className;
        this.methods = methods;
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

    public ModelView callMethodModelView() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> tempClass = Class.forName(this.getClassName());
        Object obj = tempClass.newInstance();
        return (ModelView) obj.getClass().getMethod(this.getMethods()).invoke(obj);
    }

    public static String upper(String toupper) {
        return (toupper.substring(0, 1)).toUpperCase() + toupper.substring(1, toupper.length());
    }

    public static boolean checkIfForm(Field[] fields, HttpServletRequest request, PrintWriter out) {
        for (int i = 0; i < fields.length; i++) {
            out.println(request.getParameter(fields[i].getName()));
            if (request.getParameter(fields[i].getName()) != null) {
                return true;
            }
        }
        return false;
    }

    public static Object getfromForm(Object objet, Field[] fields, HttpServletRequest request, PrintWriter out) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

//        for (int i = 0; i < fields.length; i++) {
//            out.println(fields[i].getName());
//        }
        Object myobject=null;
        out.println(6);

        for (int i = 0; i < fields.length; i++) {
            out.println(7);

            Object argument = request.getParameter(fields[i].getName());

//            out.println("lol " + argument);
//            out.println("ty eeee");
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




    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//        String directory="/Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/src/main/java/ETU1950/framework_2/DataObject/";
//        String [] e=Mapping.getClassList("/Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/java/ETU1950/framework/DataObject");
//        for (int i = 0; i < e.length; i++) {
//            System.out.println(e[i]);
//
//        }
        String e="eb";
        System.out.println(Mapping.upper(e));


    }


}
