package com.example.admin.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.myapplication.R;
import com.example.admin.myapplication.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongtinKHActivity extends AppCompatActivity {

    TextView tvBack;
    EditText edittextTen , edittextSDT , edittextEmail ;
    Button btnDangnhap ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_kh);
        Anhxa();
        EvenButton();
    }

    private void EvenButton() {
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = edittextTen.getText().toString().trim();
                final String sdt = edittextSDT.getText().toString().trim();
                final String email = edittextEmail.getText().toString().trim();
                if(ten.length()>0 && sdt.length()>0 && email.length()>0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, Server.duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang",madonhang);
                            if(Integer.parseInt(madonhang)>0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(com.android.volley.Request.Method.POST, Server.duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1")){
                                            MainActivity.manggiohang.clear();
                                            Toast.makeText(ThongtinKHActivity.this, "Bạn đã đăng ký mua hàng thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent =new Intent(ThongtinKHActivity.this,MainActivity.class);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(ThongtinKHActivity.this, "Dữ liệu mua hàng của bạn đã bị lỗi", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i=0;i<MainActivity.manggiohang.size();i++){
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham",MainActivity.manggiohang.get(i).getIdps());
                                                jsonObject.put("tensanpham",MainActivity.manggiohang.get(i).getTensp());
                                                jsonObject.put("giasanpham",MainActivity.manggiohang.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham",MainActivity.manggiohang.get(i).getSoluongsp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap = new HashMap<String,String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String,String>();
                            hashMap.put("tenkhachhang", ten);
                            hashMap.put("sdt", sdt);
                            hashMap.put("email", email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else {
                    Toast.makeText(ThongtinKHActivity.this, "Xin nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void Anhxa() {
        tvBack = (TextView) findViewById(R.id.tvBack);
        edittextTen = (EditText) findViewById(R.id.edittextTen);
        edittextSDT = (EditText) findViewById(R.id.edittextSDT);
        edittextEmail = (EditText) findViewById(R.id.edittextEmail);
        btnDangnhap = (Button) findViewById(R.id.btnDangnhap);

        tvBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ThongtinKHActivity.this,MainActivity.class);
                startActivity(intent);
                
            }
        });
    }
}
