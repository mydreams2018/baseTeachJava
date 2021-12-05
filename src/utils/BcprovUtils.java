package utils;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.encoders.UrlBase64;

import java.io.File;
import java.io.FileReader;
import java.security.KeyPair;
import java.security.Security;
/*
* apache   commons.codec也提供了相应的工具类
*/
public class BcprovUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        pem();
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
    //16进制
    public static void hex(){
        String src = "16进制编码";
        byte[] bytes = src.getBytes();
        byte[] encode = Hex.encode(bytes);
        System.out.println(new String(encode));
        byte[] decode = Hex.decode(encode);
        System.out.println(new String(decode));
    }

    public static void pem() throws Exception {
        File file = new File("D:\\nginx-1.18.0\\conf\\cert\\5616160_www.kungreat.cn.key");
        PEMParser pemParser = new PEMParser(new FileReader(file));
        Object object = pemParser.readObject();
        PEMDecryptorProvider decProv = new JcePEMDecryptorProviderBuilder().build("password".toCharArray());
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
        KeyPair kp;
        if (object instanceof PEMEncryptedKeyPair) {
            System.out.println("Encrypted key - we will use provided password");
            kp = converter.getKeyPair(((PEMEncryptedKeyPair)object).decryptKeyPair(decProv));
        } else {
            System.out.println("Unencrypted key - no password needed");
            kp = converter.getKeyPair((PEMKeyPair) object);
        }
        System.out.println(kp.getPrivate());
        System.out.println(kp.getPublic());
    }
}
