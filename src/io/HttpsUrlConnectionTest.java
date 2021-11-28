package io;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class HttpsUrlConnectionTest {

    public static void main(String st[]) throws Exception {
        URL url = new URL("https://www.kungreat.cn/api/report/queryReport");
        HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
        urlConnection.connect();
        System.out.println(urlConnection.getContentLength());
        System.out.println(urlConnection.getCipherSuite());
        System.out.println(urlConnection.getLocalPrincipal());//null
        System.out.println(urlConnection.getPeerPrincipal());//CN=www.kungreat.cn
        System.out.println(urlConnection.getServerCertificates().length);
        System.out.println(urlConnection.getLocalCertificates());//本地默认为null
    }
}
