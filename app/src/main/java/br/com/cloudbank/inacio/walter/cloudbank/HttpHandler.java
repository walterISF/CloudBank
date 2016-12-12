package br.com.cloudbank.inacio.walter.cloudbank;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by aluno on 07/12/16.
 */
public class HttpHandler {
    private final int readTimeOut = 15000;
    private final int connectionTimeOut = 15000;
    private String response;

    public String doHttpRequest(String URLRequest)throws Exception{

        int cont = 0;
        URL url;

        try{
            url = new URL(URLRequest);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(readTimeOut);
            conn.setConnectTimeout(connectionTimeOut);
            conn.connect();

            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                cont=0;
                InputStream is = conn.getInputStream();
                this.response = getJSONFromInputStream(is);
            }
            else{
                if(cont<3){
                    cont++;
                    doHttpRequest(URLRequest);
                }
                else{
                    throw new Exception("Error, Impossible to connect with the URL");
                }
            }
            return response;
        }
        catch (IOException e) {
            if(cont<3){
                cont++;
                System.out.println("Response Code : " + e.getMessage());
                doHttpRequest(URLRequest);

            }
            else{
                throw new Exception("Error, Impossible to establish connection");
            }
        }
        return response;
    }

    private String getJSONFromInputStream(InputStream is)throws Exception{
        int cont=0;
        try{
            StringBuilder response = new StringBuilder();
            String reader;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while((reader = br.readLine())!=null){
                //System.out.println(reader);
                response.append(reader);
            }
            return response.toString();
        } catch (IOException e){
            if(cont<3){
                cont++;
                getJSONFromInputStream(is);
            }
            else{
                throw new Exception("Impossible to read from server");
            }
        }
        return null;
    }
}
