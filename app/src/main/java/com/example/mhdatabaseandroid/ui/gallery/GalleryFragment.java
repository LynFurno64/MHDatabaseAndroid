package com.example.mhdatabaseandroid.ui.gallery;

import java.util.ArrayList;
import java.util.HashMap;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

import com.example.mhdatabaseandroid.R;

public class GalleryFragment extends ListFragment {

    String[] players={"Seltas","Desert Seltas","Seltas Queen","Desert Seltas Queen","Ahtal-Ka","Velocidrome","Izuchi","Malfestio","Nightcloak Malfestio","Aknosom","Rathalos","Azure Rathalos","Silver Rathalos","Dreadking Rathalos","Seregios","Shogun Ceanataur","Terra Shogun Ceanataur","Rustrazor Ceanataur","Daimyo Hermitaur","Tetsucabra","Drilltusk Tetsucabra","Zamtrios","Tetranadon","Arzuros","Redhelm Arzuros","Gammoth","Elderfrost Gammoth","Goss Harag","Lagiacrus","Ivory Lagiacrus",
            "Abyssal Lagiacrus","Agnaktor","Glacial Agnaktor","Najarala","Tidal Najarala","Brachydios","Raging Brachydios","Glavenus","Acidic Glavenus","Hellblade Glavenus","Anjanath","Fulgur Anjanath","Zinogre","Stygian Zinogre","Thunderlord Zinogre","Magnamalo","Valstrax","Crimson Glow Valstrax","Nergigante","Ruiner Nergigante","Ibushi","Narwa","Narwa Allmother","Gore Magala","Shagaru Magala","Chaotic Gore Magala","Fatalis","Gogmazios","Xeno'jiiva","Rathian","Velkhana"};
    int[] images={R.drawable.seltas,R.drawable.seltas_2,R.drawable.seltas_queen,R.drawable.seltas_queen_2,R.drawable.ahtal_ka,R.drawable.velocidrome,R.drawable.mizutsune,R.drawable.malfestio,R.drawable.malfestio_5,R.drawable.aknosom,R.drawable.rathalos,R.drawable.rathalos_2,R.drawable.rathalos_3,R.drawable.rathalos_5,R.drawable.seregios,R.drawable.shogun_ceanataur,R.drawable.shogun_ceanataur_2,R.drawable.shogun_ceanataur_5,R.drawable.daimyo_hermitaur,R.drawable.tetsucabra
    ,R.drawable.tetsucabra_5,R.drawable.zamtrios,R.drawable.tetranadon,R.drawable.arzuros,R.drawable.arzuros_5,R.drawable.gammoth,R.drawable.gammoth_5,R.drawable.goss_harag,R.drawable.lagiacrus,R.drawable.lagiacrus_2,R.drawable.lagiacrus_3,R.drawable.agnaktor,R.drawable.agnaktor_2,R.drawable.najarala,R.drawable.najarala_2,R.drawable.brachydios,R.drawable.brachydios_4,R.drawable.glavenus,R.drawable.glavenus_2,R.drawable.glavenus_5,R.drawable.anjanath,R.drawable.anjanath_2,
            R.drawable.zinogre,R.drawable.zinogre_2,R.drawable.zinogre_5,R.drawable.magnamalo,R.drawable.valstrax,R.drawable.valstrax_4,R.drawable.nergigante,R.drawable.nergigante_4,R.drawable.ibushi,R.drawable.narwa,R.drawable.narwa_4,R.drawable.gore_magala,R.drawable.shagaru_magala,R.drawable.gore_magala_4,R.drawable.__question_mark,R.drawable.__question_mark,R.drawable.xeno_jiiva,R.drawable.rathian,R.drawable.velkhana};

    ArrayList<HashMap<String, String>> data=new ArrayList<HashMap<String,String>>();
    SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        //MAP
        HashMap<String, String> map=new HashMap<String, String>();

        //FILL
        for(int i=0;i<players.length;i++)
        {
            map=new HashMap<String, String>();
            map.put("Player", players[i]);
            map.put("Image", Integer.toString(images[i]));

            data.add(map);
        }

        //KEYS IN MAP
        String[] from={"Player","Image"};

        //IDS OF VIEWS
        int[] to={R.id.nameTxt2,R.id.imageView2};

        //ADAPTER
        adapter=new SimpleAdapter(getActivity(), data, R.layout.models2, from, to);
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        getListView().setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                // TODO Auto-generated method stub

                Toast.makeText(getActivity(), data.get(pos).get("Player"), Toast.LENGTH_SHORT).show();

            }
        });
    }

}