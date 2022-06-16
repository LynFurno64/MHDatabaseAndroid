package com.example.mhdatabaseandroid;

import android.os.Bundle;
import android.widget.SearchView;

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

public class mainView extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> name = new ArrayList<>();
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAssest());
            JSONArray jsonArray = jsonObject.getJSONArray("MHinfo");
            for (int i =0;i<jsonArray.length();i++){
                JSONObject names = jsonArray.getJSONObject(i);
                name.add(names.getString("name"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        customAdapter = new CustomAdapter(name,mainView.this);
        recyclerView.setAdapter(customAdapter);

    }


    private String JsonDataFromAssest() {
        String json = null;
        try {
            InputStream is = getAssets().open("monster.json");
            int size = is.available();
            byte[]bufferData = new byte[size];
            is.read(bufferData);
            is.close();
            json = new String(bufferData,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}