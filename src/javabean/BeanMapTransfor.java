package javabean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

public class BeanMapTransfor {


    public static Map<String,Object> beanTransMap(Object bean) throws Exception {
        Map<String,Object> mp = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(),Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        if(propertyDescriptors != null && propertyDescriptors.length>0){
            for(int x=0;x<propertyDescriptors.length;x++){
                PropertyDescriptor propertyDescriptor = propertyDescriptors[x];
                Object invoke = propertyDescriptor.getReadMethod().invoke(bean);
                mp.put(propertyDescriptor.getName(),invoke);
            }
        }
        return mp;
    }

    public static <T> T mapTransBean(Class<T> cls,Map<String,Object> map) throws Exception {
        T t = cls.getConstructor().newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(cls,Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        if(propertyDescriptors != null && propertyDescriptors.length>0){
            for(int x=0;x<propertyDescriptors.length;x++){
                PropertyDescriptor propertyDescriptor = propertyDescriptors[x];
                String name = propertyDescriptor.getName();
                if(map.containsKey(name)){
                    Object o = map.get(name);
                    if(propertyDescriptor.getPropertyType().isInstance(o)){
                        propertyDescriptor.getWriteMethod().invoke(t,o);
                    }
                }
            }
        }
        return t;
    }
}
