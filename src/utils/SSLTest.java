package utils;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.util.Arrays;

public class SSLTest {

    /*
    *
    * KeyManager  本地
    * TrustManager 可信任的远程 双向认证用
    *
    */

    public static void main(String[] args) throws Exception {
     /*   new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    SSLTest.runSSLClientSocket();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        runSSLServerSocket();*/
        getCertificates();
    }
/*    此类充当基于密钥材料源的关键经理的工厂。每个密钥管理器管理特定类型的密钥材料，
    以供安全套接字使用。密钥材料基于密钥库和 或 提供程序特定的源。*/
    public static KeyManager[] keyManagerFactory() throws Exception {
        System.out.println(KeyManagerFactory.getDefaultAlgorithm());//SunX509
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        try(FileInputStream fis = new FileInputStream("C:\\Users\\kungreat\\dw.keystore")){
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(fis,"yjssaje".toCharArray());
            keyManagerFactory.init(keyStore,"yjssaje".toCharArray());
            System.out.println(keyManagerFactory);
        }catch (Exception e){
            e.printStackTrace();
        }
        return keyManagerFactory.getKeyManagers();
    }

    /*    此类充当基于信任材料源的信任管理器的工厂。每个信任管理器管理特定类型的信任材料，
    以供安全套接字使用。信任材料基于密钥库和/或特定于提供程序的源。*/
    public static TrustManager[] trustManagerFactory() throws Exception {
        System.out.println(TrustManagerFactory.getDefaultAlgorithm());//PKIX
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX");
        try(FileInputStream fis = new FileInputStream("C:\\Users\\kungreat\\dw.keystore")){
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(fis,"yjssaje".toCharArray());
            trustManagerFactory.init(keyStore);
            System.out.println(Arrays.toString(trustManagerFactory.getTrustManagers()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return trustManagerFactory.getTrustManagers();
    }
//此类的实例表示安全套接字协议实现，它充当安全套接字工厂的工厂。此类使用一组可选的密钥和信任管理器以及安全随机字节源进行初始化。SSLEngine
    public static SSLContext sslContext() throws Exception{
        System.out.println(SSLContext.getDefault().getProtocol());
        SSLContext tls = SSLContext.getInstance("TLS");
        tls.init(keyManagerFactory(),trustManagerFactory(), SecureRandom.getInstance("SHA1PRNG"));
        System.out.println(tls.getDefaultSSLParameters());
        SSLSocketFactory socketFactory = tls.getSocketFactory();
        //TLS_AES_128_GCM_SHA256 TLS 协议 AES 密钥交换算法 对称加密算法 AES_128位 工作模式 GCM 消息摘要 SHA256
        System.out.println(Arrays.toString(socketFactory.getSupportedCipherSuites()));
        SSLServerSocketFactory serverSocketFactory = tls.getServerSocketFactory();
        System.out.println(socketFactory);
        return tls;
    }

   /*
     SSLSocket  设置加密套件, 处理连接握手 并管理 SSLSession
   此类使用"安全套接字层"（SSL） 或 IETF"传输层安全性"（TLS） 协议等协议扩展并提供安全套接字。Socket
   *
   *这些类型的保护由"密码套件"指定，该套件是给定 SSL 连接使用的加密算法的组合。在协商过程中，
   * 两个终结点必须就两个环境中可用的密码套件达成一致。如果没有共同的此类套件，则无法建立SSL连接，也无法交换任何数据。
使用的密码套件是通过称为"握手"的协商过程建立的。此过程的目标是创建或重新加入"会话"，随着时间的推移，
* 这可能会保护许多连接。握手完成后，可以使用getSession方法访问会话属性。可以通过以下三种方式之一启动此连接上的初始握手：
显式开始握手的呼叫，或                           startHandshake
任何试图在此套接字上读取或写入应用程序数据的尝试都会导致隐式握手，或者
如果当前没有有效的会话，则调用尝试设置会话，并且执行隐式握手。getSession
如果握手因任何原因而失败，则关闭，并且无法进行进一步的通信。SSLSocket
*
   *在管理密码套件时，您需要了解两组密码套件：
支持的密码套件：SSL 实现支持的所有套件。此列表是使用getSupportedCipherSuites 报告的。
已启用的密码套件，可能少于完整的受支持套件集。此组使用setEnabledCipherSuites方法进行设置，并使用getEnabledCipherSuites方法进行查询。最初，将在表示建议的最低配置的新套接字上启用一组默认密码套件。
   * */
    public static SSLSocket sslSocket() throws Exception{
        SSLSocketFactory socketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket)socketFactory.createSocket();
        sslSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),20066));
        sslSocket.connect(new InetSocketAddress("www.kungreat.cn",443));

        System.out.println(Arrays.toString(socketFactory.getSupportedCipherSuites()));
        System.out.println(Arrays.toString(socketFactory.getDefaultCipherSuites()));
        //sslSocket  一些设置
        System.out.println(sslSocket);
        System.out.println(sslSocket.isConnected());
        //在此连接上启动 SSL 握手。 在读取 写入 获得session 时会自动触发
        sslSocket.startHandshake();
        //返回在 SSL/TLS 握手期间构造的。SSLSession
        System.out.println(sslSocket.getHandshakeSession());
        //返回此连接正在使用的 SSL 会话。
        SSLSession session = sslSocket.getSession();
        //返回作为定义会话的一部分而建立的对等方的证书。
        System.out.println(session.getPeerCertificates().length);
        System.out.println(sslSocket.isConnected());
        return sslSocket;
    }

//运行SSL 服务端 socket
    public static void runSSLServerSocket() throws Exception{
        SSLContext tls = SSLContext.getInstance("TLS");
        tls.init(keyManagerFactory(),trustManagerFactory(),SecureRandom.getInstance("SHA1PRNG"));
        SSLServerSocketFactory serverSocketFactory = tls.getServerSocketFactory();
        SSLServerSocket serverSocket = (SSLServerSocket)serverSocketFactory.createServerSocket();
        //是否必需要双向认证 正常情况单向就够了.有些是银行需要客户端也提供证书验证 默认 false
        System.out.println(serverSocket.getNeedClientAuth());
        //是否想要双向认证  默认 false
        System.out.println(serverSocket.getWantClientAuth());
        //是否使用 客户端  默认 false
        System.out.println(serverSocket.getUseClientMode());
        serverSocket.setUseClientMode(false);
        serverSocket.setNeedClientAuth(false);
        serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),20066));
        SSLSocket accept = (SSLSocket)serverSocket.accept();
        //从上边的server copy过来的config
        System.out.println("accept1:"+accept.getUseClientMode());
        System.out.println("accept2:"+accept.getNeedClientAuth());
        accept.setSoLinger(true,30);
        InputStream inputStream = accept.getInputStream();
        System.out.println("server-read");
        byte[] bytes = inputStream.readAllBytes();
        System.out.println(new String(bytes));
        accept.getOutputStream().write("server-answer".getBytes());
        accept.shutdownOutput();
    }
    // 运行SSL client socket
    public static void runSSLClientSocket() throws Exception{
        System.out.println("client - start");
        SSLContext tls = SSLContext.getInstance("TLS");
        tls.init(keyManagerFactory(),trustManagerFactory(),SecureRandom.getInstance("SHA1PRNG"));
        SSLSocketFactory socketFactory = tls.getSocketFactory();
        SSLSocket sslSocket = (SSLSocket)socketFactory.createSocket();
        //是否必需要双向认证 正常情况单向就够了.有些是银行需要客户端也提供证书验证 默认 false
        sslSocket.setNeedClientAuth(false);
        System.out.println("client"+sslSocket.getNeedClientAuth());
        System.out.println("client"+sslSocket.getWantClientAuth());
        //默认 true
        System.out.println("client"+sslSocket.getUseClientMode());
        sslSocket.setReuseAddress(true);
        sslSocket.setSoLinger(true,30);
//        sslSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),30066));
        sslSocket.connect(new InetSocketAddress(InetAddress.getLocalHost(),20066));
        System.out.println("client-startHandshake");
        sslSocket.getOutputStream().write("client-answer".getBytes());
        sslSocket.shutdownOutput();
        InputStream inputStream = sslSocket.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        System.out.println(new String(bytes));
        System.out.println(sslSocket.getSession());
    }

    public static void getCertificates() throws Exception {
        SSLSocketFactory factory = (SSLSocketFactory)SSLSocketFactory.getDefault();
        SSLSocket socket =(SSLSocket) factory.createSocket("www.kungreat.cn", 443);
        socket.startHandshake();
        SSLSession session = socket.getSession();
        Certificate[] peerCertificates = session.getPeerCertificates();
        //获得证书后输出为文件
        for(int x=0;x<peerCertificates.length;x++){
            String name = x+".cer";
            File file = new File("C:\\Users\\kungreat",name);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(peerCertificates[x].getEncoded());
            fileOutputStream.flush();
        }
    }
}
