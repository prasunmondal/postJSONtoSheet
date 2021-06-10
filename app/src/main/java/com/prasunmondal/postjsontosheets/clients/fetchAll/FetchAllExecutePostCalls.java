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

public class FetchAllExecutePostCalls extends AsyncTask<String, Void, String> {
    private Consumer<String> onCompletion;
    private JSONObject postDataParams;
    private URL scriptUrl;

    public FetchAllExecutePostCalls(URL scriptUrl, JSONObject postDataParams, Consumer<String> onCompletion) {
        this.onCompletion = onCompletion;
        this.postDataParams = postDataParams;
        this.scriptUrl = scriptUrl;
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
                return "false : " + responseCode;
            }
        } catch (Exception e) {
            return "Exception: " + e.getMessage();
        }
    }

    @Override
    public void onPostExecute(String result) {
        if(onCompletion == null)
            return;
        Log.e("DBCall::  Inbound", result);
        onCompletion.accept(result);
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