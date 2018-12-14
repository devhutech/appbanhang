package com.example.admin.myapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.adapter.GiohangAdapter;

import java.text.DecimalFormat;

public class ActivityGiohang extends AppCompatActivity {

    TextView tvBack;
    static TextView tvTongtien;
    TextView lvThongBao;
    ListView lvGiohang ;
    Button btnThanhtoanGH , btnTieptucMH ;
    GiohangAdapter giohangAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        CheckData();
        EvenUltil();
        CacthOnItemLV();
        EvenButton();

    }

    private void EvenButton() {
        tvBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ActivityGiohang.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnTieptucMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ActivityGiohang.this,MainActivity.class);
                startActivity(intent);

            }
        });

        btnThanhtoanGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.manggiohang.size() > 0 ){
                    Intent intent  = new Intent(ActivityGiohang.this,ThongtinKHActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(ActivityGiohang.this, "Giỏ hàng của bạn hiện đang trống", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void CacthOnItemLV() {
        lvGiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityGiohang.this);
                builder.setTitle("Xác hận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.manggiohang.size() <= 0){
                            lvThongBao.setVisibility(View.VISIBLE);
                        }else {
                            MainActivity.manggiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            EvenUltil();
                            if(MainActivity.manggiohang.size() <= 0){
                                lvThongBao.setVisibility(View.VISIBLE);
                            }else {
                                lvThongBao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });

    }

    public static void EvenUltil() {
        long tongtien = 0 ;
        for(int i = 0 ; i < MainActivity.manggiohang.size(); i++){
            tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTongtien.setText(decimalFormat.format(tongtien)+" VND");
    }

    private void CheckData() {
        if(MainActivity.manggiohang.size()<=0){
            giohangAdapter.notifyDataSetChanged();
            lvThongBao.setVisibility(View.VISIBLE);
            lvGiohang.setVisibility(View.INVISIBLE);
        }else {
            giohangAdapter.notifyDataSetChanged();
            lvThongBao.setVisibility(View.INVISIBLE);
            lvGiohang.setVisibility(View.VISIBLE);
        }
    }


    private void Anhxa() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        lvThongBao = (TextView) findViewById(R.id.lvThongBao);
        tvTongtien = (TextView) findViewById(R.id.tvTongtien);
        lvGiohang = (ListView) findViewById(R.id.lvGiohang);
        btnThanhtoanGH = (Button) findViewById(R.id.btnThanhtoanGH);
        btnTieptucMH = (Button) findViewById(R.id.btnTieptucMH);
        giohangAdapter = new GiohangAdapter(ActivityGiohang.this, MainActivity.manggiohang);
        lvGiohang.setAdapter(giohangAdapter);


    }
}
