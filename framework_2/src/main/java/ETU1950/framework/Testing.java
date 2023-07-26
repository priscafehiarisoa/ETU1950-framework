package ETU1950.framework;

import ETU1950.framework.annnotation.MethodAnnotation;
import database.core.DBObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Testing extends DBObject {
    String t;
    int io;

    public void getsmt(String arbre, int nombre, Date date, double smt, float another, Integer integer, long lgn, BigDecimal bd, BigInteger Bi,boolean bol,byte by,char chr){

    }

    public void somme(Integer deux,double trois){
        //return deux+trois;
    }
    public void tes(){

    }
    @MethodAnnotation( auth = "test", hasArguments = true)
    public void func(){

    }

    public static void main(String[] args) throws NoSuchMethodException, ParseException, InvocationTargetException, IllegalAccessException, InstantiationException {
       Mapping mapping=new Mapping("Testing","func");

//       Mapping.checkAuthorisation();
    }
}
