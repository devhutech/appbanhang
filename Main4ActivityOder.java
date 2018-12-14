package com.example.admin.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.admin.myapplication.R;

public class Main4ActivityOder extends AppCompatActivity {

    TextView tvBackOder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_oder);

        tvBackOder= (TextView) findViewById(R.id.tvBackOder);
        tvBackOder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Main4ActivityOder.this,MainActivity.class);
                startActivity(intent3);
            }
        });
    }
}
