package com.example.mhdatabaseandroid.ui.JSON;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mhdatabaseandroid.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{
    ArrayList<String> name;
    ArrayList<String> group;
    Context ctx;

    public CustomAdapter(ArrayList<String>name,ArrayList<String>group,Context ctx)
    {
        this.ctx = ctx;
        this.name = name;
        this.group = group;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {


        holder.name.setText("Name: "+name.get(position));
        holder.group.setText("Group: "+ group.get(position));

    }

    @Override
    public int getItemCount()
    {
        return name.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,group;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            group = itemView.findViewById(R.id.group);
        }
    }
}
