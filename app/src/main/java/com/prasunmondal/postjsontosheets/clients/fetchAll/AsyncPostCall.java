package com.prasunmondal.postjsontosheets.clients.fetchAll;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.function.Consumer;

import javax.net.ssl.HttpsURLConnection;

public class AsyncPostCall extends AsyncTask<String, Void, String> {
    private Consumer<String> onCompletion;
    private Consumer<String> onCompletion2;
    private JSONObject postDataParams;
    private URL scriptUrl;

    public AsyncPostCall(URL scriptUrl, JSONObject postDataParams, Consumer<String> onCompletion2, Consumer<String> onCompletion) {
        this.scriptUrl = scriptUrl;
        this.postDataParams = postDataParams;
        this.onCompletion = onCompletion;
        this.onCompletion2 = onCompletion2;
    }

    protected void onPreExecute() {
    }

    protected String doInBackground(String... arg0) {
        try {
            Log.e("DBCall:: Outbound", postDataParams.toString());

            HttpURLConnection conn = (HttpURLConnection) scriptUrl.openConnection();
            conn.setReadTimeout(150000 /* milliseconds */);
            conn.setConnectTimeout(150000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, StandardCharsets.UTF_8));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                in.close();
                return sb.toString();
            } else {
                return "Error in making post call: " + responseCode;
            }
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }

    @Override
    public void onPostExecute(String result) {
        Log.e("DBCall::  Inbound", result);
        if(onCompletion != null)
            onCompletion.accept(result);
        if(onCompletion2 != null)
            onCompletion2.accept(result);
    }

    private String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while (itr.hasNext()) {
            String key = itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
}