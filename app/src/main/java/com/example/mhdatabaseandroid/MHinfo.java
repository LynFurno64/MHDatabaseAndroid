package com.example.mhdatabaseandroid;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mhdatabaseandroid.ui.Handler.HttpHandler;
import com.example.mhdatabaseandroid.ui.JSON.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MHinfo extends AppCompatActivity {

    Bundle b = new Bundle();
    private ProgressDialog pDialog;
    private ListView lv;

    private final String TAG = MHinfo.class.getSimpleName();
    // URL to get contacts JSON
    private static final String url = "https://api.androidhive.info/contacts/";

    ArrayList<HashMap<String, String>> contactList;
    RecyclerView recyclerView;

    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> group = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhinfo);

        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getMonsters();
        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class  GetContacts extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MHinfo.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("contacts");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String email = c.getString("email");

                        // Phone node is JSON Object
                        JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("email", email);
                        contact.put("mobile", mobile);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MHinfo.this, contactList,
                    R.layout.list_item, new String[]{ "email",
                    "mobile"}, new int[]{R.id.email, R.id.mobile});

            lv.setAdapter(adapter);
        }
    }
    private void getMonsters()
    {
        //gets click item from list
        b = getIntent().getExtras();
        String click = b.getString("name");
        try
        {
            //Get root JSON object node
            JSONObject object = new JSONObject(loadJSONFromAsset());
            //Get monsters array node
            JSONArray jsonArray = object.getJSONArray("MHinfo");
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    //Get monsters JSON object node
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    //Get monster details
                    if (click.equals(jsonObject.getString("name"))){
                        name.add(jsonObject.getString("name"));
                        group.add(jsonObject.getString("group"));
                    }
                }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        CustomAdapter customAdapter = new CustomAdapter(name,group,MHinfo.this);
        recyclerView.setAdapter(customAdapter);

    }

    /**
     * read json file from assets to display in textview
     *
     */
    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("monster.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}