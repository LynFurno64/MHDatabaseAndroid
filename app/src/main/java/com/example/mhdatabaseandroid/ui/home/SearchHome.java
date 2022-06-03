package com.example.mhdatabaseandroid.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.mhdatabaseandroid.MHinfo;
import com.example.mhdatabaseandroid.R;


public class SearchHome extends AppCompatActivity {
    ListView listView;
    public String selectedFromList;

    //monster hunter world monsters
    String[] name = {"Rathalos","Sliver Rathalos","Azure Rathalos","Brachydios","" +
            "Raging Brachydios","Glavenus","Acidic Glavenus","Anjanath","Fulgur Anjanath","" +
            "Zinogre","Stygian Zinogre","Fatalis"};

    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_home);
        listView=findViewById(R.id.listview);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, name);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(SearchHome.this, "Searching " + name[i], Toast.LENGTH_LONG).show();
            selectedFromList = (String) listView.getItemAtPosition(i);
            Intent intent = new Intent(SearchHome.this, MHinfo.class);
            intent.putExtra("name",name[i]);
            startActivity(intent);

        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}
