package io;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class OutputStreamTest {

    public static void main(String st[]) throws Exception {
        URL url1 = new URL("http://127.0.0.1/hello");
        HttpURLConnection urlConnection = (HttpURLConnection)url1.openConnection();
        urlConnection.setDoOutput(true);
//        System.out.println(urlConnection.getContentEncoding());
//        System.out.println(urlConnection.getContentLength());
//        System.out.println(urlConnection.getContentType());
//        System.out.println(new Date(urlConnection.getDate()).toLocaleString());
//        Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
//        Set<Map.Entry<String, List<String>>> entries =  headerFields.entrySet();
//        for(Map.Entry<String, List<String>> mp:entries){
//            System.out.println(mp.getKey()+":"+mp.getValue());
//        }
        String rt = "echo=哈哈大12345689";
//        urlConnection.setFixedLengthStreamingMode(rt.getBytes().length);
        try(OutputStream outputStream = urlConnection.getOutputStream()){
            outputStream.write(rt.getBytes());
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(urlConnection.getResponseCode());
        System.out.println(urlConnection.getRequestMethod());
//        System.out.println(urlConnection.getResponseMessage());
        try(InputStream inputStream = urlConnection.getInputStream()){
            byte[] bytes = inputStream.readAllBytes();
            System.out.println(new String(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println((char)21704);
        //0101 010011 001000
    }
}
