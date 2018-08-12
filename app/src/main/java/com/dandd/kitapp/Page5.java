package com.dandd.kitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Page5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5);
    }
    public void next45(View fr){
        Intent intent = new Intent(this, page6.class);
        startActivity(intent);
    }
    public void next55(View fr){
        Intent intent = new Intent(this, Page8.class);
        startActivity(intent);
    }
}
