package reflex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflex {

    /*  System.getProperties()
        user.home:C:\Users\kungreat
        java.class.path:C:\Users\kungreat\IdeaProjects\baseTeachJava\out\production\baseTeachJava
    * sun.boot.library.path:C:\Program Files\Java\jdk-11.0.5\bin
    * java.home:C:\Program Files\Java\jdk-11.0.5
    * file.encoding:UTF-8
      path.separator:;
    */

    public static void main(String[] args) throws Exception {
        Reflex reflex = new Reflex();
        runEverything(reflex,"ts","run所有");
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
