package ETU1950.framework;

import ETU1950.framework.annnotation.MethodAnnotation;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
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

    public static HashMap<String, Mapping> getMethodsHashMapFromPackage(String packageDirectory,String ObjectPackage) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        HashMap<String,Mapping> mapping=new HashMap<>();

        String[] classes=getClassList(packageDirectory);

        for (int i = 0; i < classes.length; i++) {
            System.out.println("name: "+classes[i]);
            Class<?> tempClass=Class.forName(ObjectPackage+classes[i]);
            Object obj=tempClass.newInstance();
            Method [] methods=obj.getClass().getDeclaredMethods();
            for (int j = 0; j < methods.length; j++) {
                if(methods[j].isAnnotationPresent(MethodAnnotation.class))
                {
                    String url=methods[j].getAnnotation(MethodAnnotation.class).url();
                    String className=classes[i];
                    String methodName=methods[j].getName();
                    mapping.put(url,new Mapping(className,methodName));
                }
            }
        }
        return mapping;
    }

    public static String[] getClassList(String directoryPath)
    {
        File file=new File(directoryPath);
//        ty zany afaka simplifier-na otran'zao
//        de filtrena fotsiny le .java avy eo
        File[] f=file.listFiles();
        FilenameFilter textFilefilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
//        String lowercaseName = name.toLowerCase();
                if (name.endsWith(".java")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        String [] listefile=file.list(textFilefilter);
        for (int i = 0; i < listefile.length; i++) {
            listefile[i]=listefile[i].split(".java")[0];
        }
        return listefile;
    }


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String directory="/Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/src/main/java/ETU1950/framework_2/DataObject/";
        String [] e=Mapping.getClassList("/Users/priscafehiarisoadama/IdeaProjects/ETU1950-framework/framework_2/src/main/java/ETU1950/framework/DataObject");
        for (int i = 0; i < e.length; i++) {
            System.out.println(e[i]);

        }

    }


}
