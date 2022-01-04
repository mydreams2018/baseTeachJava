package reflex;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = PermissionUsers.class)
public @interface PermissionUser {
    String value();
    String name() default "kungreat";
}
