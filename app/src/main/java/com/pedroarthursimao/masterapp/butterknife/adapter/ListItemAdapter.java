package com.pedroarthursimao.masterapp.butterknife.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedroarthursimao.masterapp.R;
import com.pedroarthursimao.masterapp.butterknife.model.ListItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder>{

    private ArrayList<ListItem> dataSet;

    public ListItemAdapter(ArrayList<ListItem> dataSet){
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        View v = holder.itemView;
        ListItem tip = dataSet.get(position);
        holder.ivImage.setImageBitmap(tip.getImage());
        holder.textTitle.setText(tip.getTitle());
        holder.textContent.setText(tip.getContent());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public ListItem getItem(int position){
        return  dataSet.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.ivImage) ImageView ivImage;
        @Bind(R.id.textTitle) TextView textTitle;
        @Bind(R.id.textContent) TextView textContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
