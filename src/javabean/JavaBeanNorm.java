package javabean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/*      类名首字母大写、驼峰
        方法、字段 首字母小写、驼峰
        常量全大写
        第一,其中JavaBean为共有类,此类要使用访问权限public进行修饰
        第二,JavaBean定义构造方法时,一定要保留一个无参public构造器
        第三,JavaBean属性[field]通常可以使用访问权限对private进行修饰,此种主要表示私有属性
        第四,使用setXXX()的方法以及getXXX()的方法得到JavaBean里的私有属性XXX数值;
        第五,JavaBean 要放在包内

        字段名称:值
        KEY名称:值
*/
public class JavaBeanNorm  {

    public static void main(String[] args) throws Exception {
//        UserAccount userAccount = new UserAccount("kungreat","AAAAA",18);
//        Map<String, Object> stringObjectMap = BeanMapTransfor.beanTransMap(userAccount);
//        System.out.println(stringObjectMap);

        Map<String, Object> map = new HashMap<>();
        map.put("accountName", "kungreat");
        map.put("address", "CCCCC");
        map.put("age", 18);
        map.put("id", "2015545454");
        map.put("phone", "212121213");
        UserAccount userAccount = BeanMapTransfor.mapTransBean(UserAccount.class, map);
        System.out.println(userAccount);

//        UserAccount userAccount = UserAccount.class.getConstructor().newInstance();
//        BeanInfo beanInfo = Introspector.getBeanInfo(UserAccount.class,Object.class);
//        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//        for(int x=0;x<propertyDescriptors.length;x++){
//            PropertyDescriptor propertyDescriptor = propertyDescriptors[x];
//            if(propertyDescriptor.getPropertyType() == String.class){
//                propertyDescriptor.getWriteMethod().invoke(userAccount,"AAAA"+x);
//            }else if(propertyDescriptor.getPropertyType() == Integer.class){
//                propertyDescriptor.getWriteMethod().invoke(userAccount,x);
//            }
//        }
//        for(int x=0;x<propertyDescriptors.length;x++){
//            PropertyDescriptor propertyDescriptor = propertyDescriptors[x];
//            Object invoke = propertyDescriptor.getReadMethod().invoke(userAccount);
//            System.out.println(propertyDescriptor.getName()+":"+invoke);
//        }
    }

}
