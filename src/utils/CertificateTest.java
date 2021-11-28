package utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.*;
import java.util.Collection;
import java.util.Iterator;

public class CertificateTest {

    public static void main(String[] args) throws Exception {
        String defaultType = KeyStore.getDefaultType();
        System.out.println(defaultType);//pkcs12
        x509Certificate();
    }

/*   Certificate 用于管理各种身份证书的抽象类。身份证书是主体与公钥的绑定，公钥由另一个主体担保。
      （主体表示实体，如个人用户、组或公司。此类是具有不同格式但重要的常见用途的证书的抽象。
    例如，不同类型的证书（如 X.509 和 PGP）共享常规证书功能（如编码和验证）和某些类型的信息（如公钥）。
    X.509、PGP 和 SDSI 证书都可以通过子类化 Certificate 类来实现，即使它们包含不同的信息集，
    并且它们以不同的方式存储和检索信息。*/
    public static void certificate() throws  Exception {
//    以下示例解析存储在文件中的 PKCS#7 格式的证书回复并从中提取所有证书：
        FileInputStream fis = new FileInputStream("C:\\Users\\kungreat\\dw.cer");
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Collection c = cf.generateCertificates(fis);
        Iterator i = c.iterator();
        while (i.hasNext()) {
            Certificate cert = (Certificate)i.next();
            System.out.println(cert.getType());
//验证此证书是否使用与指定公钥对应的私钥进行签名
//            cert.verify(publicKey);
        }

    }

  /* CertificateFactory  此类定义了证书工厂的功能，用于根据编码生成证书、证书路径 (CertPath) 和证书吊销列表 (CRL) 对象。
    对于由多个证书组成的编码，当您想要解析可能不相关的证书集合时，请使用 generateCertificates。否则，
    当您想要生成 CertPath（证书链）并随后使用 CertPathValidator 对其进行验证时，请使用 generateCertPath。
    X.509 的证书工厂必须返回作为 java.security.cert.X509Certificate 实例的证书和作为
    java.security.cert.X509CRL 实例的 CRL。下面的示例读取一个带有
    Base64 编码证书的文件，每个证书以 -----BEGIN CERTIFICATE----- 开头，以 -----END CERTIFICATE----- 结尾.
    我们将 FileInputStream（不支持标记和重置）转换为 BufferedInputStream（支持那些方法），
    这样每次调用 generateCertificate 只消耗一个证书，并且输入流的读取位置定位到下一个证书中文件：
    */
    public static void certificateFactory() throws Exception {
        FileInputStream fis = new FileInputStream("C:\\Users\\kungreat\\dw.cer");
        BufferedInputStream bis = new BufferedInputStream(fis);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        while (bis.available() > 0) {
            Certificate cert = cf.generateCertificate(bis);
            System.out.println(cert.getType());
        }
    }

  /*  X.509 证书的抽象类。这提供了一种访问 X.509 证书的所有属性的标准方法。
  *这些证书广泛用于支持互联网安全系统中的身份验证和其他功能。常见应用程序包括隐私增强邮件
  * （PEM）、传输层安全性 （SSL）、用于可信软件分发的代码签名和安全电子事务 （SET）。
这些证书由证书颁发机构（CA） 管理和担保。CA 是通过将数据以 X.509 标准格式放置，
* 然后对该数据进行数字签名来创建证书的服务。CA充当受信任的第三方，在彼此没有直接了解的委托人之间进行介绍。
* CA 证书要么由其自身签名，要么由其他一些 CA（如"根"CA）签名。
  *
   */
    public static void x509Certificate() throws Exception {
        try (InputStream inStream = new FileInputStream("C:\\Users\\kungreat\\dw.cer")) {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)cf.generateCertificate(inStream);
            cert.checkValidity();
            System.out.println(cert.getSigAlgName());
            System.out.println(cert.getIssuerX500Principal().getName());
            System.out.println(cert.getSubjectX500Principal().getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
