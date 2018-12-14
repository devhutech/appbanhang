package com.example.admin.myapplication.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.myapplication.R;
import com.example.admin.myapplication.adapter.LoaispAdapter;
import com.example.admin.myapplication.adapter.NSXAdapter;
import com.example.admin.myapplication.adapter.SanphamAdapter;
import com.example.admin.myapplication.model.Giohang;
import com.example.admin.myapplication.model.Loaisp;
import com.example.admin.myapplication.model.NSX;
import com.example.admin.myapplication.model.Sanpham1;
import com.example.admin.myapplication.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //Khai bao

    android.support.v7.widget.Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView Navigationview;
    DrawerLayout drawerLayout;
    TextView textviewLoaisp,textviewNSX,X,Registration,Login,Contactlht,Order,Introduce,tvlht;
    ImageButton IconGiohang ;

    ListView lvo;
    ListView lvo2;
    ListView lvo3;



    ArrayList<Loaisp> mangloaisp ;
    ArrayList<NSX> mangNSX;
    ArrayList<Sanpham1> mangSanpham ;

    public static  ArrayList<Giohang> manggiohang ;

    LoaispAdapter loaispAdapter ;
    NSXAdapter nsxAdapter;
    SanphamAdapter sanphamAdapter ;


    int Congtac1 = 0;
    int Congtac2 = 0;

    int idloaisp1 = 0;
    int idNSX1 = 0;
    int page = 1;

    int id = 0;
    String tenloaisp = "";
    String tennhasanxuat = "";



    // -- Khai bao


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();

        ActionBar();
        ActionViewFlipper();

        GetdulieuLoaisp();
        GetdulieuNSX();


        GetIdLoaiSP();
        GetId();
        Getdata(page);
        Getdata2(page);

        GetdulieuSPmoinhat();

        Sukien();
    }

    private void Getdata(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.duongdanLoadtheoLoaisp + String.valueOf(page);
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int Id = 0 ;
                String Tensanpham = "" ;
                int Giasanpham = 0 ;
                String Hinhanhsanpham = "" ;
                String Motasanpham = "" ;
                int Idsanpham = 0 ;
                int IdNSX = 0 ;
                if(response != null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Id = jsonObject.getInt("id");
                            Tensanpham = jsonObject.getString("tensp");
                            Giasanpham = jsonObject.getInt("giasp");
                            Hinhanhsanpham = jsonObject.getString("hinhanhsp");
                            Motasanpham = jsonObject.getString("motasp");
                            Idsanpham = jsonObject.getInt("idsanpham");
                            IdNSX = jsonObject.getInt("idNSX");
                            mangSanpham.add(new Sanpham1(Id,Tensanpham,Giasanpham,Hinhanhsanpham,Motasanpham,Idsanpham,IdNSX));
                            sanphamAdapter.notifyDataSetChanged();  //?????? Sai chổ
//                            Log.d("pppp1", Tensanpham);
//                            Log.d("oooo1", mangSanpham.toString());
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else { Toast.makeText(MainActivity.this, "Khong load dc ", Toast.LENGTH_SHORT).show();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "JSON click theo loaisp loi~", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("idsanpham",String.valueOf(idloaisp1));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Getdata2(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.duongdanLoadtheoNSX + String.valueOf(page);
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int Id = 0 ;
                String Tensanpham = "" ;
                int Giasanpham = 0 ;
                String Hinhanhsanpham = "" ;
                String Motasanpham = "" ;
                int Idsanpham = 0 ;
                int IdNSX = 0 ;
                if(response != null){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Id = jsonObject.getInt("id");
                            Tensanpham = jsonObject.getString("tensp");
                            Giasanpham = jsonObject.getInt("giasp");
                            Hinhanhsanpham = jsonObject.getString("hinhanhsp");
                            Motasanpham = jsonObject.getString("motasp");
                            Idsanpham = jsonObject.getInt("idsanpham");
                            IdNSX = jsonObject.getInt("idNSX");
                            mangSanpham.add(new Sanpham1(Id,Tensanpham,Giasanpham,Hinhanhsanpham,Motasanpham,Idsanpham,IdNSX));
                            sanphamAdapter.notifyDataSetChanged();  //?????? Sai chổ
//                            Log.d("pppp1", Tensanpham);
//                            Log.d("oooo1", mangSanpham.toString());
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else { Toast.makeText(MainActivity.this, "khong load duoc ", Toast.LENGTH_SHORT).show();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "JSON click theo loaisp ERROR", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("idNSX",String.valueOf(idNSX1));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }


    private void GetIdLoaiSP() {
        lvo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idloaisp1 = mangloaisp.get(position).getId();
                mangSanpham.removeAll(mangSanpham);
                Getdata(idloaisp1);
               //Toast.makeText(MainActivity.this, mangloaisp.get(position).getTenloaisp(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void GetId() {
          lvo2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  idNSX1 = mangNSX.get(position).getID();
                  mangSanpham.removeAll(mangSanpham);
                  Getdata2(idNSX1);

               //   Toast.makeText(MainActivity.this, mangNSX.get(position).getTenNSX(), Toast.LENGTH_SHORT).show();
              }
          });
    }

    private void GetdulieuSPmoinhat() {
        mangSanpham.removeAll(mangSanpham);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanSPmoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null) {
                     int Id = 0 ;
                     String Tensanpham = "" ;
                     int Giasanpham = 0 ;
                     String Hinhanhsanpham = "" ;
                     String Motasanpham = "" ;
                     int Idsanpham = 0 ;
                     int IdNSX = 0 ;
                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Id = jsonObject.getInt("id");
                            Tensanpham = jsonObject.getString("tensp");
                            Giasanpham = jsonObject.getInt("giasp");
                            Hinhanhsanpham = jsonObject.getString("hinhanhsp");
                            Motasanpham = jsonObject.getString("motasp");
                            Idsanpham = jsonObject.getInt("idsanpham");
                            IdNSX = jsonObject.getInt("idNSX");
                            mangSanpham.add(new Sanpham1(Id,Tensanpham,Giasanpham,Hinhanhsanpham,Motasanpham,Idsanpham,IdNSX));
                            sanphamAdapter.notifyDataSetChanged();  //?????? Sai chổ

                            Log.d("pppp1", Tensanpham);
                            Log.d("oooo1", mangSanpham.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else { Toast.makeText(MainActivity.this, "JSON null", Toast.LENGTH_SHORT).show();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "JSON ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetdulieuNSX() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest   = new JsonArrayRequest(Server.duongdanNSX, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for (int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            tennhasanxuat=jsonObject.getString("tennhasanxuat");

                            mangNSX.add(new NSX(id,tennhasanxuat));

                            nsxAdapter.notifyDataSetChanged();

                            Log.d("iiii1", tennhasanxuat);
                            Log.d("uuuu1", mangNSX.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetdulieuLoaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest   = new JsonArrayRequest(Server.duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for (int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            tenloaisp=jsonObject.getString("tenloaisp");
                            mangloaisp.add(new Loaisp(id,tenloaisp));
                            loaispAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper(){
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://scontent.fsgn2-4.fna.fbcdn.net/v/t1.0-9/46463218_755729814774202_5437041813456158720_o.jpg?_nc_cat=111&_nc_ht=scontent.fsgn2-4.fna&oh=4889bfa1d4da3ec0d65e5d4171391b60&oe=5C79F594");
        mangquangcao.add("https://scontent.fhan2-1.fna.fbcdn.net/v/t1.0-9/46486157_755729808107536_8313728749887553536_o.jpg?_nc_cat=102&_nc_ht=scontent.fhan2-1.fna&oh=695e642bcf04e3b728ecda6f0b70d59c&oe=5C765921");
        mangquangcao.add("https://scontent.fsgn2-1.fna.fbcdn.net/v/t1.0-9/46451723_755729834774200_5593320804180819968_o.jpg?_nc_cat=107&_nc_ht=scontent.fsgn2-1.fna&oh=100088e9a54d7df64bc74c6ed90fa5cc&oe=5C836A63");
        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

        Animation animation_slite_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slite_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slite_out_right);
        viewFlipper.setInAnimation(animation_slite_in);
        viewFlipper.setOutAnimation(animation_slite_out);
    }

    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }

    private void Sukien() {
        //   Bật tắc loại SP
        textviewLoaisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Congtac1 == 0){
                    lvo.setVisibility(View.VISIBLE);
                    Congtac1 = 1;
                }else {
                    lvo.setVisibility(View.GONE);
                    Congtac1 = 0;
                }
            }
        });
//        Bật tắc  NSX
        textviewNSX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Congtac2 == 0){
                    lvo2.setVisibility(View.VISIBLE);
                    Congtac2 = 1;
                }else {
                    lvo2.setVisibility(View.GONE);
                    Congtac2 = 0;
                }
            }
        });
        //Đóng Navigation
        X.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        Introduce.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2ActivityIntroduce.class);
                startActivity(intent);
            }
        });

        Contactlht.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this,Main3ActivityContact.class);
                startActivity(intent2);
            }
        });

        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainActivity.this,Main4ActivityOder.class);
                startActivity(intent5);
            }
        });
        tvlht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // finish();
                Intent intent6 = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent6);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainActivity.this,ThongtinKHActivity.class);
                startActivity(intent5);
            }
        });
        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainActivity.this,DangKyActivity.class);
                startActivity(intent5);
            }
        });


        lvo3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent7=new Intent(getApplicationContext(),ChitietSanpham.class);
                intent7.putExtra("thongtinsanpham",mangSanpham.get(position));
                startActivity(intent7);
                Log.d("testmangSanpham", mangSanpham.get(position).toString());
            }
        });

        lvo3.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        IconGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent8 = new Intent(MainActivity.this,ActivityGiohang.class);
                startActivity(intent8);
            }
        });

    }

    private void Anhxa(){
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewlipper);
        Navigationview = (NavigationView) findViewById(R.id.navigationview);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        textviewLoaisp = (TextView) findViewById(R.id.textviewLoaisp);
        textviewNSX = (TextView) findViewById(R.id.textviewNSX);
        X = (TextView) findViewById(R.id.X);
        Introduce = (TextView) findViewById(R.id.Introduce);
        Order = (TextView) findViewById(R.id.Order);
        Contactlht = (TextView) findViewById(R.id.Contact);
        Login = (TextView) findViewById(R.id.Login);
        Registration = (TextView) findViewById(R.id.Registration);
        tvlht = (TextView) findViewById(R.id.tvlht);
        IconGiohang = (ImageButton) findViewById(R.id.IconGiohang);


        lvo = (ListView) findViewById(R.id.lvo);
        lvo2 = (ListView) findViewById(R.id.lvo2);
        lvo3 = (ListView) findViewById(R.id.lvo3);



        mangloaisp = new ArrayList<>();
        mangNSX = new ArrayList<>();
        mangSanpham = new ArrayList<>();

        loaispAdapter = new LoaispAdapter(mangloaisp,getApplicationContext());
        nsxAdapter = new NSXAdapter(mangNSX,getApplicationContext());
        sanphamAdapter = new SanphamAdapter(mangSanpham,getApplicationContext());

        lvo.setAdapter(loaispAdapter);
        lvo2.setAdapter(nsxAdapter);
        lvo3.setAdapter(sanphamAdapter);

        if(manggiohang != null){}
        else {manggiohang = new ArrayList<>();}




    }



}
