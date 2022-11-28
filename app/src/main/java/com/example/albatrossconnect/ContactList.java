package com.example.albatrossconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ContactList extends AppCompatActivity {
    ArrayList<String> myList;
    ArrayList<String> dirList;
    int [] img_names;
    RecyclerView movieView;
    com.example.albatrossconnect.recyclerview.RVClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_user_page);
        movieView = findViewById(R.id.recycler_user_chat);/* Set View as recycler view*/
        List<String> names = com.example.albatrossconnect.data.Constants.dirNames;
        List<String> dir_names = com.example.albatrossconnect.data.Constants.movieNames;
        myList = new ArrayList<>();
        dirList = new ArrayList<>();
        myList.addAll(names);
        dirList.addAll(dir_names);
        /* the listener with a lambda and access the list of names with the position passed in*/
        listener = (view, position)->{
//            Intent openlink = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerLinks.get(position)));
//            startActivity(openlink);
            Intent openChat = new Intent(this, ChatActivity.class);
            startActivity(openChat);
        };
        com.example.albatrossconnect.recyclerview.MyAdapter adapter = new com.example.albatrossconnect.recyclerview.MyAdapter(myList, dirList,img_names,listener, true, ContactList.this);
        movieView.setHasFixedSize(true);
        movieView.setLayoutManager(new LinearLayoutManager(this));/* Default Layout is Linear*/
        movieView.setAdapter(adapter);
    }
}