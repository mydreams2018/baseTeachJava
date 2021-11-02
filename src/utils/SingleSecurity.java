package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Set;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

/*
*
*/
public class SingleSecurity {


    public static void main(String sr[]) throws Exception {
        checkSum();
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
    public static void checkSum() throws Exception {
        Checksum checksum = new Adler32();
        System.out.println(checksum.getValue());
        checksum.update("dfsdfsdfsdfdfefsdfsdfxxx-".getBytes());
        System.out.println(checksum.getValue());
        checksum.reset();
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
        //指定算法 获得实例对象 也可指定Provider
        AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance("DSA");
        //与算法无关
        algorithmParameterGenerator.init(512);
//        algorithmParameterGenerator.init(512,SecureRandom.getInstance("DRBG"));
        //与算法相关   TODO AlgorithmParameterSpec
//        algorithmParameterGenerator.init(AlgorithmParameterSpec);
        AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();
        byte[] encoded = algorithmParameters.getEncoded();
        BigInteger bigInteger = new BigInteger(encoded);
        System.out.println(bigInteger);
        //---------------------------------------分割线
        //指定算法 获得实例对象 也可指定Provider
        AlgorithmParameters gcm = AlgorithmParameters.getInstance("DSA");
        //需要初始化 只能使用一次
        gcm.init(encoded);
        System.out.println(gcm.getAlgorithm());
        System.out.println(new BigInteger(gcm.getEncoded()));
    }

    //是一个引擎类  密钥对生成器  通过 Providers.txt 查询支持的算法
    public static void keyPAirGenerator() throws Exception{
        //指定算法 获得实例对象 也可指定Provider
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        //与算法无关
        rsa.initialize(1024);
//        rsa.initialize(1024,SecureRandom.getInstance("DRBG"));
        //与算法相关   TODO AlgorithmParameterSpec
//        rsa.initialize(AlgorithmParameterSpec);
        //获得KeyPair
        KeyPair keyPair = rsa.genKeyPair();
        System.out.println(keyPair);
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();
        System.out.println(aPrivate);
        System.out.println(aPublic);
        System.out.println(rsa);
    }
    //是一个引擎类  密钥对生成器  通过 Providers.txt 查询支持的算法
    public static void keyFactory() throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] privateEncode = keyPair.getPrivate().getEncoded();
        //KeySpec 密钥规范
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateEncode);

        //指定算法 获得实例对象 也可指定Provider
        KeyFactory rsa = KeyFactory.getInstance("RSA");
        Key privateKey = rsa.generatePrivate(pkcs8EncodedKeySpec);
//        rsa.translateKey(Key)  将提供者可能未知或不受信任的密钥对象转换成此密钥工厂对应的密钥对象
        //返回给定密钥对象的规范 KeySpec
//        PKCS8EncodedKeySpec keySpec = rsa.getKeySpec(privateKey, PKCS8EncodedKeySpec.class);
//        System.out.println(pkcs8EncodedKeySpec == keySpec );
        System.out.println(privateKey);
        System.out.println(rsa);
    }
    //安全随机数生成器
    public static void secureRandom(){
        //默认算法实现
        SecureRandom secureRandom = new SecureRandom();
        //默认算法实现  传入随机种子值
//        SecureRandom secureRandom2 = new SecureRandom(SecureRandom.getSeed(512));
//        SecureRandom.getInstance("") 通过指定算法初始化
        //传入值 生成种子字节数组
        byte[] bytes = secureRandom.generateSeed(512);
        //设置随机数种子值
        secureRandom.setSeed(bytes);
        byte[] bts= new byte[64];//任意数量
        secureRandom.nextBytes(bts);
        for(int x=0;x<5;x++){
            System.out.println(secureRandom.nextInt());
        }
    }
    //验证数字签名 引擎类   私钥签名 公钥验签
    public static void signature() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        Signature instance = Signature.getInstance("SHA512withRSA");
        //初始化用于签名 私有密钥
        instance.initSign(keyPair.getPrivate());

        String st = "kdifsdf在平在人间.-=ljjlkjl我夺123123";
        //更新签名 数据
        instance.update(st.getBytes());
        //返回所有数据 已更新的签名字节
        byte[] sign = instance.sign();
        //给定一个数组封装数据.长度不能少于 算法返回的长度
//        byte[] bts = new byte[512];
//        int sign1 = instance.sign(bts, 5, 256);

        //初始化验证签名 公有密钥
        instance.initVerify(keyPair.getPublic());
        //初始化验证签名 来自给定证书的公钥
//        instance.initVerify(Certificate);
        //更新验证数据
        instance.update(st.getBytes());
        //验证签名
        boolean verify = instance.verify(sign);
        System.out.println(verify);
    }
    //验证数字签名封装类  私钥签名 公钥验签
    public static void singedObject() throws Exception {
        String st = "kdifsdf在平在人间.-=ljjlkjl我夺123123";
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        Signature instance = Signature.getInstance("SHA512withRSA");
        //初始化 验证对象
        SignedObject signedObject = new SignedObject(st, keyPair.getPrivate(), instance);
        Object object = signedObject.getObject();
        System.out.println(object);//  st
        //获得签名值
        byte[] signature = signedObject.getSignature();
        boolean verify = signedObject.verify(keyPair.getPublic(), instance);
        System.out.println(verify);//true
    }
    //用于封装签署时间戳 称为数子时间戳
    public static void tiemstamp() throws Exception {
        //构造对象并指定证书类型
        CertificateFactory x509 = CertificateFactory.getInstance("X509");
        System.out.println(x509.getType());//X509
        //获得证书路径
        CertPath certPath = x509.generateCertPath(new FileInputStream("../x.cer"));
        //生成数字时间戳
        Timestamp timestamp = new Timestamp(new Date(),certPath);
//        timestamp.equals(Object)  比较对象是否同一类型 数子时间戳是否一致 证书是否一致
    }

    //代码签名 和 数子时间戳配合使用  TODO
    public static void codeSigner() throws Exception {
        //构造对象并指定证书类型
        CertificateFactory x509 = CertificateFactory.getInstance("X509");
        System.out.println(x509.getType());//X509
        //获得证书路径
        CertPath certPath = x509.generateCertPath(new FileInputStream("../x.cer"));
        //生成数字时间戳
        Timestamp timestamp = new Timestamp(new Date(),certPath);
//        timestamp.equals(Object)  比较对象是否同一类型 数子时间戳是否一致 证书是否一致
        CodeSigner codeSigner = new CodeSigner(certPath, timestamp);
//        codeSigner.equals()  比较对象是否同一类型 数子时间戳是否一致 证书是否一致
    }
    //密钥库,用来管理密钥和证书的存储
    public static void keyStore() throws Exception {
        String defaultType = KeyStore.getDefaultType();
        System.out.println(defaultType);//pkcs12

        //通过名称获得一个实例
        KeyStore jks = KeyStore.getInstance("pkcs12");
        char[] chars = "yjssaje".toCharArray();
        //使用前要加载 密钥文件 和 密码  也可通过keytool 生成
        jks.load(new FileInputStream("C:/Users/kungreat/.keystore"), chars);//注意关闭流
//        jks.store();  存储数据 到文件
        System.out.println(jks.getType());//pkcs12
        System.out.println(jks.size());//4 个别名密钥库
        System.out.println(jks);
        Enumeration<String> aliases = jks.aliases();
        while(aliases.hasMoreElements()){
            System.out.println(aliases.nextElement());// ca ca1 ca2 e1
        }
        KeyStore.PrivateKeyEntry ca = (KeyStore.PrivateKeyEntry)jks.getEntry("ca",
                new KeyStore.PasswordProtection(chars));
        PrivateKey privateKey = ca.getPrivateKey();
        Certificate certificate = ca.getCertificate();
        Certificate[] certificateChain = ca.getCertificateChain();
        Set<KeyStore.Entry.Attribute> attributes = ca.getAttributes();
        System.out.println(privateKey);
        Certificate cac = jks.getCertificate("ca");
    }


}