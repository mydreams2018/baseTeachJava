package utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MacDoFinal {

    public static void main(String[] args) throws Exception {
        doFinal();
    }
    //带有秘钥的消息摘要
    public static void doFinal() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] encoded = secretKey.getEncoded();

        //还原秘钥 通用方式通过指定算法名称匹配还原秘钥  --> KeySpec, SecretKey
        SecretKeySpec secretKeySpec = new SecretKeySpec(encoded,"HmacMD5");

        Mac hmacMD5 = Mac.getInstance("HmacMD5");
        hmacMD5.init(secretKeySpec);
        System.out.println(hmacMD5.getMacLength());
        byte[] bytes = hmacMD5.doFinal("Mac安全消息摘要".getBytes());
        System.out.println(Hex.encodeHexString(bytes));
    }


}
