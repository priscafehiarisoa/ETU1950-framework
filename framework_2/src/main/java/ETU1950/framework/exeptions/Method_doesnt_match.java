package ETU1950.framework.exeptions;

public class Method_doesnt_match extends Exception{
    public Method_doesnt_match(int lengthMethod, int lengthgiven) {
        super("les attributs ne correspondent pas , \n expectations : "+lengthMethod+" \n given : "+lengthgiven);
    }

    public Method_doesnt_match(String message) {
        super(message);
    }
}
