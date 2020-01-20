package com.example.aaiapp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    public int log_stat;
    Context context;
    AlertDialog alertDialog;
    BackgroundWorker(Context ctx)
    {
        context=ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url="http://192.168.43.174/login.php";
        if(type.equals("login")){
            try {
                String us_nm=params[1];
                String psd=params[2];
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("user_name","UTF-8")+URLEncoder.encode(us_nm,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+URLEncoder.encode(psd,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    System.out.println(line);
                    result+=line;

                }
                System.out.println(line);
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                System.out.println("catch1");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("catch2");
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");

    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        if(result=="login success")
            log_stat=1;
        System.out.println(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
