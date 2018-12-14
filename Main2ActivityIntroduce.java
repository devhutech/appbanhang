package com.example.admin.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.admin.myapplication.R;

public class Main2ActivityIntroduce extends AppCompatActivity {

    TextView tvBack;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_introduce);
        Anhxa();

    }

    private void Anhxa() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2ActivityIntroduce.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
