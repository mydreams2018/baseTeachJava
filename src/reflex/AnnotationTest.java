package reflex;

import base.PermissionTest;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/*
  @Target(ElementType.METHOD)  注解可以出现在位置
  @Retention(RetentionPolicy.SOURCE)  注解生命周期
*/
@PermissionUser(value = "11111",name = "22222")
@PermissionUser(value = "33333",name = "44444")
public class AnnotationTest extends AnnotationSuper{

    public static void main(String[] args) {
//        PermissionTest permissionTest = new PermissionTest("name","age");

        Annotation[] annotations = AnnotationTest.class.getAnnotations();
        System.out.println(Arrays.toString(annotations));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
