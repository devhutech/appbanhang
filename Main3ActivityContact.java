package com.example.admin.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.admin.myapplication.R;

public class Main3ActivityContact extends AppCompatActivity {

   TextView tvBackcontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_contact);

        tvBackcontact = (TextView) findViewById(R.id.tvBackcontact);
        tvBackcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Main3ActivityContact.this,MainActivity.class);
                startActivity(intent1);
            }
        });

    }
}
