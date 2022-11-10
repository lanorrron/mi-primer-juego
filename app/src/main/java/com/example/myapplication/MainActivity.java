package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button Jugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Jugar = findViewById(R.id.jugar);
        Jugar.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        Intent sedIntent = new Intent(this, MainActivity2.class);
        startActivity(sedIntent);
    }
}
