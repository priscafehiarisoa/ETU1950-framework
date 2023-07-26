package ETU1950.framework.annnotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodAnnotation{
    public String url() default"";
    public String auth() default "";
    public boolean hasArguments() default false;
    public boolean restAPI () default false;
}
