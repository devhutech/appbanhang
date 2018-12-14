package com.example.admin.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myapplication.R;

public class DangKyActivity extends AppCompatActivity {
    TextView tvBack , edittextTen2 , edittextSDT ,edittextDiachi;
    Button btnDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        tvBack = (TextView) findViewById(R.id.tvBack);
        edittextTen2 = (TextView) findViewById(R.id.edittextTen2);
        edittextSDT = (TextView) findViewById(R.id.edittextSDT);
        edittextDiachi = (TextView) findViewById(R.id.edittextDiachi);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String ten2 = edittextTen2.getText().toString().trim();
                 String sdt2 = edittextSDT.getText().toString().trim();
                 String diachi2 = edittextDiachi.getText().toString().trim();
                if(ten2.length()>0 && sdt2.length()>0 && diachi2.length()>0){
                    Toast.makeText(DangKyActivity.this, "Đăng ký thành Công", Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(DangKyActivity.this,MainActivity.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(DangKyActivity.this, "Xin điền đủ thông tin !", Toast.LENGTH_LONG).show();
                }
            }
        });

        tvBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DangKyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
