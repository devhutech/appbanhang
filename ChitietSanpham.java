package com.example.admin.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.model.Giohang;
import com.example.admin.myapplication.model.Sanpham1;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChitietSanpham extends AppCompatActivity {

    TextView tvBack , tvtenCTSP , tvGiaCTSP , tvmotaCTSP;
    Spinner spinner;
    Button btnThemgiahang;
    ImageView imgCTSP;
    ImageButton IconGiohang2;

    int Id = 0 ;
    String Tensanpham = "" ;
    int Giasanpham = 0 ;
    String Hinhanhsanpham = "" ;
    String Motasanpham = "" ;
    int Idsanpham = 0 ;
    int IdNSX = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_sanpham);

        Anhxa();
        GetchitietSP();
        CatchEventSpinner();
        EventButton();

    }

    private void EventButton() {

        btnThemgiahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.manggiohang.size()>0){
                    int sl =Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false ;
                    for(int i=0 ; i < MainActivity.manggiohang.size(); i++)
                    {
                        if(MainActivity.manggiohang.get(i).getIdps() == Id){
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl );
                            if(MainActivity.manggiohang.get(i).getSoluongsp()>=10){
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(Long.valueOf(Giasanpham * MainActivity.manggiohang.get(i).getSoluongsp()));
                            exists = true ;
                        }
                    }
                    if(exists == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong * Giasanpham ;
                        MainActivity.manggiohang.add(new Giohang(Id,Tensanpham,Giamoi,Hinhanhsanpham,soluong));
                    }
                }else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * Giasanpham ;
                    MainActivity.manggiohang.add(new Giohang(Id,Tensanpham,Giamoi,Hinhanhsanpham,soluong));
                }
                Intent intent =new Intent(getApplicationContext(),ActivityGiohang.class);
                startActivity(intent);
            }
        });


    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }
    private void GetchitietSP() {
        Sanpham1 sanpham = (Sanpham1) getIntent().getSerializableExtra("thongtinsanpham");
        Id = sanpham.getId();
        Tensanpham = sanpham.getTensanpham();
        Giasanpham = sanpham.getGiasanpham();
        Hinhanhsanpham = sanpham.getHinhanhsanpham();
        Motasanpham = sanpham.getMotasanpham();
        Idsanpham = sanpham.getIdsanpham();
        IdNSX = sanpham.getIdNSX();

        Log.d("testCTSP", sanpham.toString());

        tvtenCTSP.setText(Tensanpham);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGiaCTSP.setText(decimalFormat.format(Giasanpham)+ " VND");
        tvmotaCTSP.setText(Motasanpham);
        Picasso.with(getApplicationContext()).load(Hinhanhsanpham).into(imgCTSP);
    }
    private void Anhxa() {
        tvtenCTSP = (TextView) findViewById(R.id.tvtenCTSP);
        tvGiaCTSP = (TextView) findViewById(R.id.tvGiaCTSP);
        tvmotaCTSP = (TextView) findViewById(R.id.tvmotaCTSP);
        tvBack = (TextView) findViewById(R.id.tvBack);
        spinner = (Spinner) findViewById(R.id.spinner);
        btnThemgiahang = (Button) findViewById(R.id.btnThemgiahang);
        imgCTSP = (ImageView) findViewById(R.id.imgCTSP);
        IconGiohang2 = (ImageButton) findViewById(R.id.IconGiohang2);

        tvBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChitietSanpham.this,MainActivity.class);
                startActivity(intent);
            }
        });

        IconGiohang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9 = new Intent(ChitietSanpham.this,ActivityGiohang.class);
                startActivity(intent9);
            }
        });
    }
}
