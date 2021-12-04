package utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PEBUtils {

    public static void main(String[] ss) throws Exception {

       secretKey2();


    }

    public static void secretKey()throws Exception{
        //盐
        SecureRandom sha1PRNG = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = sha1PRNG.generateSeed(8);
        //自已的密码转换材料  密码、盐、迭代计数。
        PBEKeySpec pbeKeySpec = new PBEKeySpec("password".toCharArray(),salt,100);
        SecretKeyFactory pbeWithHmacSHA1AndAES_128 = SecretKeyFactory.getInstance("PBEWithHmacSHA1AndAES_128");
        SecretKey secretKey = pbeWithHmacSHA1AndAES_128.generateSecret(pbeKeySpec);
        System.out.println(secretKey);
        Cipher cipher = Cipher.getInstance("PBEWithHmacSHA1AndAES_128");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        System.out.println(cipher);
    }

    public static void secretKey2()throws Exception{
        //自已的密码转换材料  密码
        PBEKeySpec pbeKeySpec = new PBEKeySpec("password".toCharArray());
        SecretKeyFactory pbeWithHmacSHA1AndAES_128 = SecretKeyFactory.getInstance("PBEWithHmacSHA1AndAES_128");
        SecretKey secretKey = pbeWithHmacSHA1AndAES_128.generateSecret(pbeKeySpec);
        System.out.println(secretKey);
        //盐
        SecureRandom sha1PRNG = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = sha1PRNG.generateSeed(8);
        //安全参数材料
        PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt,100);

        Cipher cipher = Cipher.getInstance("PBEWithHmacSHA1AndAES_128");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,pbeParameterSpec);
        System.out.println(cipher);
    }
}
