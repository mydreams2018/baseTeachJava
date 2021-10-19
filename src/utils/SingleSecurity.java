package utils;

import java.security.*;
import java.util.Arrays;
import java.util.Set;

/*
*
*/
public class SingleSecurity {


    public static void main(String sr[]) throws NoSuchAlgorithmException {
        singleMessageCheck();
    }
//输出当前提供的安全服务类所支持的算法
    public static void getProvider(){
        Provider[] providers = Security.getProviders();
        for(int x=0;x<providers.length;x++){
            Provider p= providers[x];
            System.out.println(providers[x]);
            Set<Object> objects = p.keySet();
            for(Object o : objects){
                System.out.println("\t"+o);
            }
        }
    }
    //数据完整性验证 主要[MD|SHA] 通过 Providers.txt 查询支持的算法 如MessageDigest.SHA-256
    public static void singleMessageCheck() throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        //更新摘要 多种入参
        instance.update((byte)12);
        //完成摘要并返回摘要结果  也可传入值先更新 摘要再返回
        byte[] digest = instance.digest();
        System.out.println(Arrays.toString(digest));
        System.out.println(instance.getDigestLength());
        //重置摘要还原初始状态
        instance.reset();

    }
    //是一个引擎类 提供密码参数的不透明表示 通过 Providers.txt 查询支持的算法
    public static void algorithmParamters() throws Exception {
        //获得实例对象 也可指定Provider
        AlgorithmParameters gcm = AlgorithmParameters.getInstance("GCM");
        System.out.println(gcm.getEncoded());
    }
}
