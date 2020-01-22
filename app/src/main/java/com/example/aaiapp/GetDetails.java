package com.example.aaiapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

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

public class GetDetails extends AsyncTask<String, Void, String> {
    @SuppressLint("StaticFieldLeak")


    private Context context;

    GetDetails(Context ctx)
    {
        context=ctx;
    }


    @Override
    protected String doInBackground(String... params) {

        String type=params[1];
        String details_url = "http://192.168.0.6/details.php";
        if(type.equals("info")){
            try {
                String id=params[0];

                URL url=new URL(details_url);
                HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setDoInput(true);
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String post_data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") ;                bufferedWriter.write(post_data);
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
                System.out.println();
                bufferedReader.close();
                inputStream.close();
                httpsURLConnection.disconnect();
                System.out.println(result);
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
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(context, InfoDetails.class);
        Bundle bundle = new Bundle();
        bundle.putString("message", result);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
