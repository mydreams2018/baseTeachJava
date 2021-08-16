package auth;

import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;

public class MyAuth extends Authenticator {
    @Override
    public PasswordAuthentication requestPasswordAuthenticationInstance(String host, InetAddress addr, int port, String protocol, String prompt, String scheme, URL url, RequestorType reqType) {
        char[] ps = {'y','j','s','s','a','j','e'};
        System.out.println("requestPasswordAuthenticationInstance");
        return new PasswordAuthentication("kungreat",ps);
    }
}
