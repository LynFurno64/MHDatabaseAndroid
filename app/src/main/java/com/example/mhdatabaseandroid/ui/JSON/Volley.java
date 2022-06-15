package com.example.mhdatabaseandroid.ui.JSON;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.TextView;

import com.example.mhdatabaseandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Volley extends AppCompatActivity {

    TextView name;
    TextView group;
    TextView element;
    TextView aliment;
    TextView weaknesselement;
    TextView games;
    TextView cut;
    TextView impact;
    TextView projectiles;

    ProgressDialog progressDialog;
    String myUrl = "https://127.0.0.1:5000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
    }

    private class MyTask extends AsyncTask<Void, Void, String> implements com.example.mhdatabaseandroid.ui.JSON.MyTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experience
            progressDialog = new ProgressDialog(Volley.this);
            progressDialog.setMessage("processing results");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(Void... voids) {
            // Fetch data from the API in the background.

            String result = "";
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(myUrl);
                    //open a URL coonnection

                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();

                    while (data != -1) {
                        result += (char) data;
                        data = isw.read();

                    }

                    // return the data to onPostExecute method
                    return result;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return result;
        }
        @Override
        protected void onPostExecute(String s) {

            // dismiss the progress dialog after receiving data from API
            progressDialog.dismiss();
            try {

                JSONObject jsonObject = new JSONObject(s);

                //gets json array name
               // JSONArray jsonArray1 = jsonObject.getJSONArray();

                //JSONObject jsonObject1 =jsonArray1.getJSONObject();
                //String id = jsonObject1.getString("id");
               // String name = jsonObject1.getString("name");

                //Display data with the Textview
                //resultsTextView.setText();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        }

}