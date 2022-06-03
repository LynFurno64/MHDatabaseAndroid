package com.example.mhdatabaseandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mhdatabaseandroid.ui.JSON.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MHinfo extends AppCompatActivity {

    Bundle b = new Bundle();

    RecyclerView recyclerView;

    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> group = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhinfo);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getMonsters();

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