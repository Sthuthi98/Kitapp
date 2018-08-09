package com.dandd.kitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class page6 extends AppCompatActivity {

    String[] infoArray = {
            "Video 1",

            "Video 2",
            "Video 3",
            "Video 4",
            "Video 5",
            "Video 6"
    };

    Integer[] imageArray = {R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo};
    ListView lists;
    List list = new ArrayList();
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page6);
        lists =(ListView) findViewById(R.id.lists);
        CustomListAdapter rows = new CustomListAdapter(this, infoArray, imageArray);
        lists.setAdapter(rows);


    }
}
