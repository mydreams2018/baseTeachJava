package utils;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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
    final static int[] AES_KEYSIZES = new int[]{16, 24, 32};

    public static void main(String sr[]) throws Exception {
        desKeySpec();
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
        jks.load(new FileInputStream("C:/Users/kungreat/dw.keystore"), chars);//注意关闭流
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
//安全消息摘要   消息认证
    public static void mac() throws Exception {
        // 秘密密钥 生成器
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
        SecretKey secretKey = keyGenerator.generateKey();
        System.out.println(secretKey.getAlgorithm());//HmacSHA512

        Mac mac = Mac.getInstance(secretKey.getAlgorithm());//HmacSHA512
        mac.init(secretKey);
        mac.update((byte) 15);
        //获得安全消息摘要 后的信息
        byte[] bytes = mac.doFinal();
    }


//此类提供密钥协议（或密钥交换）协议的功能。
    public static void keyAgreement1() throws Exception {
        //我们实现了Diffie-Hellman密钥交换算法
        KeyPairGenerator dh = KeyPairGenerator.getInstance("DH");
        KeyPair keyPair1 = dh.genKeyPair();
        KeyPair keyPair2 = dh.genKeyPair();
        System.out.println(dh.getAlgorithm());
        //实例化
        KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
        //私钥初始化keyAgreement
        keyAgreement.init(keyPair1.getPrivate());
/*对于密钥交换中的每个通讯员，需要调用doPhase 。 例如，如果此密钥交换与另一方进行，
doPhase需要调用lastPhase一次，并将lastPhase标志设置为true 。
如果此密钥交换与另外两方进行， doPhase需要调用doPhase两次，第一次将lastPhase标志设置为false ，
第二次将其设置为true 。 密钥交换可能涉及任意数量的各方。*/
        keyAgreement.doPhase(keyPair2.getPublic(),true);
        byte[] secret = keyAgreement.generateSecret();

        int keysize = secret.length;
        SecretKey skey = null;
        for(int idx = SingleSecurity.AES_KEYSIZES.length - 1; skey == null && idx >= 0; --idx) {
            if (keysize >= SingleSecurity.AES_KEYSIZES[idx]) {
                keysize = SingleSecurity.AES_KEYSIZES[idx];
                skey = new SecretKeySpec(secret, 0, keysize, "AES");
            }
        }
        System.out.println(skey);
    }
/*
*密钥工厂用于将密钥（类型为不透明的加密密钥）转换为密钥规范（底层密钥材料的透明表示），
* 反之亦然。 秘密密钥工厂仅对秘密（对称）密钥进行操作。 密钥工厂是双向的，即它们允许从给定的密钥规范（密钥材料）
* 构建不透明的密钥对象，或以合适的格式检索密钥对象的底层密钥材料。
*/
    public static void secretKeyFactory() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] encoded = secretKey.getEncoded();
/*   KeySpec
* 构成加密密钥的密钥材料的（透明）规范。 如果密钥存储在硬件设备上，则其规范可能包含有助于识别设备上的密钥的信息。
* 可以以特定于算法的方式或独立于算法的编码格式（例如 ASN.1）指定密钥。
* 例如，DSA 私钥可以由其组件 x、p、q 和 g（请参阅 DSAPrivateKeySpec）指定，
* 或者可以使用其 DER 编码（请参阅 PKCS8EncodedKeySpec）指定。 此接口不包含任何方法或常量。
* 它的唯一目的是对所有关键规范进行分组（并为其提供类型安全）。 所有关键规范都必须实现这个接口。
* */
        DESKeySpec desKeySpec = new DESKeySpec(encoded);

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        //从所提供的密钥规范（关键材料）生成对象
        SecretKey secretKeyFt = secretKeyFactory.generateSecret(desKeySpec);
        System.out.println(secretKeyFt);
    }


//此类提供加密密码的功能，用于加密和解密。它构成了JCE框架的核心。[算法/模式/填充 | 算法]
    public static void cipher() throws Exception{
        // Base64.getEncoder() 有特殊包装适合一次的调用.
        String encrypt = CipherUtils.DEFAULTA.encryptByIV("{我是刘大胖子啊.你是谁啊....456465李厚霖要sdfsdfsdf}",
                "AES/CBC/PKCS5Padding");
        System.out.println(CipherUtils.DEFAULTA.decryptByIV(encrypt,"AES/CBC/PKCS5Padding"));

        String encryptNoIV = CipherUtils.DEFAULTA.encryptNoIV("{我是刘大胖子啊.你是谁啊....456465李厚霖要sdfsdfsdf}",
                "AES/ECB/PKCS5Padding");
        System.out.println(CipherUtils.DEFAULTA.decryptNoIV(encryptNoIV,"AES/ECB/PKCS5Padding"));

        //多次调用update效果  apache   Hex.encodeHexString 工具类
        String encryptNoIVMany = CipherUtils.DEFAULTA.encryptNoIVMany("{我是刘大胖子啊.你是谁啊....456465李厚霖要sdfsdfsdf}",
                "AES/ECB/PKCS5Padding");
        System.out.println(CipherUtils.DEFAULTA.decryptNoIVMany(encryptNoIVMany,"AES/ECB/PKCS5Padding"));
    }

    // 方法的作用是把原始的密钥通过某种加密算法包装为加密后的密钥，这样就可以避免在传递密钥的时候泄漏了密钥的明文。
    public static void cipherWrap() throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGenerator.generateKey();
        System.out.println(secretKey);
        byte[] bytes = CipherUtils.DEFAULTA.onWrap(secretKey, "AES/CBC/PKCS5Padding");
        Key deWrap = CipherUtils.DEFAULTA.deWrap("AES/CBC/PKCS5Padding", bytes, "DES",Cipher.SECRET_KEY);
        System.out.println(deWrap);
    }
    //此类使程序员能够创建对象并使用加密算法保护其机密性
    public static void sealedObject() throws Exception{
        String srcObj = "我是没有在朋asfd";
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecretKey secretKey = keyGenerator.generateKey();
        //加密
        Cipher srcCip = Cipher.getInstance("AES");
        srcCip.init(Cipher.ENCRYPT_MODE,secretKey);
        //生成
        SealedObject sealedObject = new SealedObject(srcObj,srcCip);

        //解密
        Cipher tarCip = Cipher.getInstance("AES");
        tarCip.init(Cipher.DECRYPT_MODE,secretKey);
        String deSrc = (String)sealedObject.getObject(tarCip);
        System.out.println(deSrc);
    }

 /*  keySpec  构成加密密钥的密钥材料的（透明）规范。 如果密钥存储在硬件设备上，
    则其规范可能包含有助于识别设备上的密钥的信息。 可以以特定于算法的方式或独立于算法的编码格式（例如 ASN.1）指定密钥。
    例如，DSA 私钥可以由其组件 x、p、q 和 g（请参阅 DSAPrivateKeySpec）指定，
    或者可以使用其 DER 编码（请参阅 PKCS8EncodedKeySpec）指定。
    此接口不包含任何方法或常量。 它的唯一目的是对所有关键规范进行分组（并为其提供类型安全）。
    所有关键规范都必须实现此接口。
    EncodedKeySpec --> keySpec  . 此类表示编码格式的公钥或私钥。
    PKCS8EncodedKeySpec --> EncodedKeySpec 此类表示编码格式的私钥
    X509EncodedKeySpec  --> EncodedKeySpec 此类表示编码格式的公钥

    DSAPublicKeySpec --> keySpec   此类指定 DSA 公钥及其关联的参数。
    DSAPrivateKeySpec --> keySpec  此类指定 DSA 私钥及其关联的参数。
    SecretKeySpec --> keySpec 用于构造秘密密钥规范
*/
    public static void keySpec() throws Exception {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("DSA");
        keygen.initialize(1024);
        KeyPair keyPair = keygen.genKeyPair();
        PublicKey aPublic = keyPair.getPublic();
        byte[] publicEncoded = aPublic.getEncoded();
        System.out.println(aPublic);

        //根据byte数组 转换成对应的KEY  X509EncodedKeySpec  PKCS8EncodedKeySpec
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicEncoded);
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        System.out.println(publicKey);
    }
    //用于构造 秘密密钥规范
    public static void secretKeySpec()throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance("RC2");
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] encoded = secretKey.getEncoded();
        //还原密钥对象
        SecretKeySpec secretKeySpec = new SecretKeySpec(encoded,"RC2");
        System.out.println(secretKeySpec);
    }
    //用于构造 秘密密钥规范
    public static void desKeySpec()throws Exception{
        //src
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] encoded = secretKey.getEncoded();
        //还原密钥对象
        DESKeySpec desKeySpec = new DESKeySpec(encoded);
        SecretKeyFactory secretKeyFactory= SecretKeyFactory.getInstance("DES");
        SecretKey deSecretKey = secretKeyFactory.generateSecret(desKeySpec);
        System.out.println(deSecretKey);
    }

/*   加密参数的（透明）规范。
    此接口不包含任何方法或常量。它的唯一目的是对所有参数规范进行分组（并提供类型安全）。
    所有参数规范都必须实现此接口。*/
    public static void algorithmParameterSpec(){
        //p素数  q次级素数  g基数
        DSAParameterSpec cachedDSAParameterSpec = AlgorithmParameterSpecUtils.getCachedDSAParameterSpec(512, 160);
        System.out.println(cachedDSAParameterSpec);
        System.out.println(cachedDSAParameterSpec.getP());
        System.out.println(cachedDSAParameterSpec.getQ());
        System.out.println(cachedDSAParameterSpec.getG());
    }
}