package com.example.aaiapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BackgroundWorker extends AsyncTask<String,Void,String> {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private AlertDialog alertDialog;
    BackgroundWorker(Context ctx)
    {
        context=ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url = "http://192.168.1.47/login.php";
        if(type.equals("login")){
            try {
                String us_nm=params[1];
                String psd=params[2];
                URL url=new URL(login_url);
                HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(us_nm, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(psd, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpsURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
                String result="";
                String line;
                while((line=bufferedReader.readLine())!=null)
                {
                    System.out.println(line);
                    result = result.concat(line);

                }
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
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
        System.out.println(result);
        if (result.equals("login success")) {
            Intent intent = new Intent(context, Main2Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
        System.out.println(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
