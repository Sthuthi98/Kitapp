package com.dandd.kitapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Page8 extends AppCompatActivity {
    final Context context = this;
    private EditText ipAddress;
    private Button up, down,right,left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8);
    }
}
