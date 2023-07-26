package ETU1950.framework.exeptions;

public class NotAllowedMethod extends Exception{
    public NotAllowedMethod() {
    }

    public NotAllowedMethod(String message) {
        super(message);
    }
}
