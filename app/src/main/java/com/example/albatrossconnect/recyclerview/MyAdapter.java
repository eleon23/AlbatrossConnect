package com.example.albatrossconnect.recyclerview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.albatrossconnect.R;


import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final ArrayList<String> nameList;/* Movie Names*/
    private final ArrayList<String> dirList;/* Director Names*/
    private final int [] imgList; /* Image Src*/
    private final com.example.albatrossconnect.recyclerview.RVClickListener RVlistener; /*listener defined in main activity*/
    private final boolean layoutType; /* To fetch Layout Type*/
    private final Context context;
    public MyAdapter(ArrayList<String> theList, ArrayList<String> dir_list,int [] imgSrc,com.example.albatrossconnect.recyclerview.RVClickListener listener, boolean layoutType, Activity context){
        this.nameList = theList;
        this.dirList = dir_list;
        this.imgList = imgSrc;
        this.RVlistener = listener;
        this.layoutType = layoutType;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listView;
        /* Switch between grid and linear layout*/
        listView = inflater.inflate(R.layout.chatviewlayout, parent, false);
        return new ViewHolder(listView, RVlistener);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /* Set Various fields with values in the layout*/
        holder.name.setText(nameList.get(position));
        holder.dir_name.setText(dirList.get(position));
        //holder.image.setImageResource(imgList[position]);
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name; /* Movie Name*/
        public TextView dir_name; /* Director Name*/
        public ImageView image; /* Image View*/
        public CardView card;
        private final com.example.albatrossconnect.recyclerview.RVClickListener listener;
        private View itemView;
        public ViewHolder(@NonNull View itemView, com.example.albatrossconnect.recyclerview.RVClickListener passedListener) {
            super(itemView);
            /* Get all View Values*/
            name = (TextView) itemView.findViewById(R.id.nameofuser);
            dir_name = (TextView) itemView.findViewById(R.id.statusofuser);
            card = (CardView) itemView.findViewById(R.id.cardviewofuser);
            //image = (ImageView) itemView.findViewById(R.id.img_item);
            this.itemView = itemView;
            this.listener = passedListener;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
            Log.i("ON_CLICK", "in the onclick in view holder");
        }
    }
}
