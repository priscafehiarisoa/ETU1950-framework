package ETU1950.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;

public class Testing {

    public void getsmt(String arbre, int nombre, Date date, double smt, float another, Integer integer, long lgn, BigDecimal bd, BigInteger Bi,boolean bol,byte by,char chr){

    }

    public void somme(Integer deux,double trois){
        //return deux+trois;
    }

    public static void main(String[] args) throws NoSuchMethodException, ParseException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Mapping m=new Mapping("Testing","somme");
        Parameter[] param=m.get_all_arguments_from_function(new Testing());

        //Method meth=Testing.class.getMethod(m.getMethods());
        for (int i = 0; i < param.length; i++) {
           // System.out.println(param[i].getType());
        }
        try {
            Object i = Mapping.convertString("12", "int");
            //System.out.println(i.getClass().getTypeName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        String [] arguments={"12","23"};

        Object[] listeArg=new Object[arguments.length];
        Class [] parametres=new Class[arguments.length];
        Object [] listeParametres=new Object[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            System.out.println(String.valueOf(param[i].getType()).replace("class ",""));
            listeArg[i]=Mapping.convertString(arguments[i], String.valueOf(param[i].getType()));
            //parametres[i]=(param[i].getType());
            //listeParametres[i]=param[i].getType().newInstance();

        }
        for (int i = 0; i < listeArg.length; i++) {
            System.out.println(listeArg[i].getClass());
        }

        Method methode=Testing.class.getMethod(m.getMethods(),parametres);

        System.out.println(methode.invoke(listeArg,listeParametres));
        System.out.println(methode.getName());
        //12
        //cat
    }
}
