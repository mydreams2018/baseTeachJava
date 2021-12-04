package utils;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/*
*AlgorithmParameterSpec  加密参数的（透明）规范。
*AlgorithmParameters  此类用作加密参数的不透明表示形式。
 */
public class DHUtils {

    private static SecretKey keyA ;
    private static SecretKey keyB ;


    private static byte[] changesA ;
    private static byte[] changesB ;
    public static void main(String[] args)throws Exception {
        changeA();
        //false
        System.out.println(keyA==keyB);
        //true
        System.out.println(Arrays.equals(keyA.getEncoded(), keyB.getEncoded()));
        //A加密
        String strA = "{我是刘大胖子啊.你是谁啊....456465李厚霖要sdfsdfsdf}";
        Cipher cipherEncodeA = Cipher.getInstance("AES");
        cipherEncodeA.init(Cipher.ENCRYPT_MODE,keyA);
        byte[] encodeBytes = cipherEncodeA.doFinal(strA.getBytes());
        System.out.println(new String(encodeBytes));
        //B解密
        Cipher cipherEncodeB = Cipher.getInstance("AES");
        cipherEncodeB.init(Cipher.DECRYPT_MODE, keyB);
        byte[] decodeBytes = cipherEncodeB.doFinal(encodeBytes);
        System.out.println(new String(decodeBytes));
    }

    private static void changeA() throws Exception {
        //我们实现了Diffie-Hellman密钥交换算法
        KeyPairGenerator dhA = KeyPairGenerator.getInstance("DH");
        dhA.initialize(1024);
        KeyPair keyPair = dhA.genKeyPair();
        DHPublicKey aPublic =(DHPublicKey) keyPair.getPublic();
        DHPrivateKey aprivateKey = (DHPrivateKey)keyPair.getPrivate();
        changesA = aPublic.getEncoded();
        changeB();
        //解析乙方公钥   转换公钥材料   PKCS8EncodedKeySpec generatePrivate 转换私钥材料
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(changesB);
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        PublicKey aPubKey = keyFactory.generatePublic(x509KeySpec);

        KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
        keyAgreement.init(aprivateKey);
        keyAgreement.doPhase(aPubKey,true);
        //交换后生成自已的 SecretKey
        byte[] secret = keyAgreement.generateSecret();
        int keysize = secret.length;
        SecretKey skey = null;
        for(int idx = SingleSecurity.AES_KEYSIZES.length - 1; skey == null && idx >= 0; --idx) {
            if (keysize >= SingleSecurity.AES_KEYSIZES[idx]) {
                keysize = SingleSecurity.AES_KEYSIZES[idx];
                skey = new SecretKeySpec(secret, 0, keysize, "AES");
            }
        }
        keyA=skey;
    }

    private static void changeB() throws Exception {
        //解析甲方公钥   转换公钥材料   PKCS8EncodedKeySpec generatePrivate 转换私钥材料
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(changesA);
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance("DH");
        PublicKey aPubKey = keyFactory.generatePublic(x509KeySpec);

        KeyPairGenerator dhB = KeyPairGenerator.getInstance("DH");
        //由甲方公钥构建
        dhB.initialize(1024);
        KeyPair keyPair = dhB.genKeyPair();
        DHPublicKey bPublic =(DHPublicKey) keyPair.getPublic();
        DHPrivateKey bprivateKey = (DHPrivateKey)keyPair.getPrivate();
        //给甲方的公钥 byte[]
        changesB = bPublic.getEncoded();

        KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
        keyAgreement.init(bprivateKey);
        keyAgreement.doPhase(aPubKey,true);
        //交换后生成自已的 SecretKey
        byte[] secret = keyAgreement.generateSecret();
        int keysize = secret.length;
        SecretKey skey = null;
        for(int idx = SingleSecurity.AES_KEYSIZES.length - 1; skey == null && idx >= 0; --idx) {
            if (keysize >= SingleSecurity.AES_KEYSIZES[idx]) {
                keysize = SingleSecurity.AES_KEYSIZES[idx];
                skey = new SecretKeySpec(secret, 0, keysize, "AES");
            }
        }
        keyB=skey;
    }

}
