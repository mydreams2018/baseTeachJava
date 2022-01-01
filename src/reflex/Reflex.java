package reflex;

import base.PermissionTest;
import test.EnumTest;

import javax.sound.midi.Soundbank;
import java.beans.JavaBean;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

public class Reflex {

    /*  System.getProperties()
        user.home:C:\Users\kungreat
        java.class.path:C:\Users\kungreat\IdeaProjects\baseTeachJava\out\production\baseTeachJava
    * sun.boot.library.path:C:\Program Files\Java\jdk-11.0.5\bin
    * java.home:C:\Program Files\Java\jdk-11.0.5
    * file.encoding:UTF-8
      path.separator:;
    */
    //在同一个加载器链路上 Class<?>  同一个包、类  只存在一份
    public static void main(String[] args) throws Exception {
        Class<PermissionTest> permissionTestClass = PermissionTest.class;
        Field[] declaredFields = permissionTestClass.getDeclaredFields();
        for(int x=0;x<declaredFields.length;x++){
            Field declaredField = declaredFields[x];
            System.out.println(declaredField.getDeclaringClass());
            System.out.println(Arrays.toString(declaredField.getDeclaredAnnotations()));
            int modifiers = declaredField.getModifiers();
            declaredField.setAccessible(true);

            declaredField.set(null,"AAA");
            if(Modifier.isStatic(modifiers)){
                Object o = declaredField.get(null);
                System.out.println("static:"+o);
            }
        }

    }

    public void ts(String st){
        System.out.println("ts::"+st);
    }

    public static void runEverything(Object obj,String methodName,Object... args) throws InvocationTargetException, IllegalAccessException {
        Class<?> aClass = obj.getClass();
        if(!aClass.isPrimitive()){
            Method[] declaredMethods = aClass.getDeclaredMethods();
            if(declaredMethods != null &&declaredMethods.length>0){
                for(int x=0;x<declaredMethods.length;x++){
                    Method methods = declaredMethods[x];
                    String name = methods.getName();
                    if(name.equals(methodName)){
                        if(Modifier.isStatic(methods.getModifiers())){
                            System.out.println("isStatic=true");
                            methods.invoke(null,args);
                        }else{
                            System.out.println("isStatic=false");
                            methods.invoke(obj,args);
                        }
                    }
                }
            }
        }
    }
}
