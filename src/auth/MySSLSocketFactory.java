package auth;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySSLSocketFactory extends SSLSocketFactory{

    @Override
    public String[] getDefaultCipherSuites() {
        System.out.println("getDefaultCipherSuites");
        return new String[0];
    }

    @Override
    public String[] getSupportedCipherSuites() {
        System.out.println("getSupportedCipherSuites");
        return new String[0];
    }

    @Override
    public Socket createSocket(Socket socket, String s, int i, boolean b) throws IOException {
        System.out.println("createSocket1");
        return null;
    }

    @Override
    public Socket createSocket(String s, int i) throws IOException, UnknownHostException {
        System.out.println("createSocket2");
        return null;
    }

    @Override
    public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException, UnknownHostException {
        System.out.println("createSocket3");
        return null;
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        System.out.println("createSocket4");
        return null;
    }

    @Override
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) throws IOException {
        System.out.println("createSocket5");
        return null;
    }
}
