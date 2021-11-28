package utils;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.UrlBase64;

import java.security.Security;

public class BcprovUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) {
        urlBase64();
    }

    public static void base64(){
        String src = "测试base64编码";
        byte[] bytes = src.getBytes();
        byte[] encode = Base64.encode(bytes);
        System.out.println(new String(encode));
        byte[] decode = Base64.decode(encode);
        System.out.println(new String(decode));
    }

    public static void urlBase64(){
        String src = "http://www.kungreat.cn?name=kungreat&age=18";
        byte[] bytes = src.getBytes();
        byte[] encode = UrlBase64.encode(bytes);
        System.out.println(new String(encode));
        byte[] decode = UrlBase64.decode(encode);
        System.out.println(new String(decode));
    }
}
