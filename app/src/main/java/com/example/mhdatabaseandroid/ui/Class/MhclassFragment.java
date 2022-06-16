package com.example.mhdatabaseandroid.ui.Class;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mhdatabaseandroid.R;
import com.example.mhdatabaseandroid.databinding.FragmentHomeBinding;

public class MhclassFragment extends Fragment {

    private FragmentHomeBinding binding;
    ListView listView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String[] classes = {"Neopteron","Temnoceran","Bird Wyvern","Flying Wyvern","Piscine Wyvern","Carapaceon","Amphibian","Fanged Beast","Leviathan","Snake Wyvern","Brute Wyvern","Fanged Wyvern","Elder Dragons"};
        listView = (ListView) root.findViewById(R.id.monster_class);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
             getActivity(),
                android.R.layout.simple_list_item_1,
                classes
        );
        listView.setAdapter(listAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void Search(View view){
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        Search(view);
    }
}