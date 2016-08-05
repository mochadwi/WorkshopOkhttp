package com.rizal.perpusonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.rizal.perpusonline.utility.OkHttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateBuku extends AppCompatActivity {
        public static String kdBuku = "";
        EditText inKode;
        EditText inJudul;
        EditText inPengarang;
        EditText inPenerbit;
        EditText inTahun;
        EditText inHarga;
        EditText inStok;
        Button simpan;

        String judulBuku;
        String pengarang;
        String penerbit;
        String tahun;
        String harga;
        String stok;
        String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_buku);
        kdBuku = getIntent().getExtras().getString("kode_buku");
        url = "http://bukuapi.azurewebsites.net/api/buku/"+kdBuku;
        Toast.makeText(UpdateBuku.this, "Loading " + kdBuku, Toast.LENGTH_LONG).show();
        //setupTool();

            deklarasi();
        try {
            getDataId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIsi();
                update();
            }
        });

    }
    private void setupTool(){
        Toolbar tool = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Buku");


    }
    private void deklarasi() {
        inJudul = (EditText)findViewById(R.id.edJudul);
        inPengarang = (EditText)findViewById(R.id.edPengarang);
        inPenerbit = (EditText)findViewById(R.id.edPenerbit);
        inTahun = (EditText)findViewById(R.id.edTahun);
        inHarga = (EditText)findViewById(R.id.edHarga);
        inStok = (EditText)findViewById(R.id.edStok);
        simpan = (Button) findViewById(R.id.update);
    }
    private void setIsi() {

        judulBuku = inJudul.getText().toString().trim();
        pengarang = inPengarang.getText().toString().trim();
        penerbit = inPenerbit.getText().toString().trim();
        tahun = inTahun.getText().toString().trim();
        harga = inHarga.getText().toString().trim();
        stok = inStok.getText().toString().trim();
    }

    public void getDataId() throws IOException {
        OkHttpRequest.getDataFromServer(url).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {
                    JSONObject object = new JSONObject(response.body().string());
                    JSONArray array = new JSONArray(object.getString("result"));
                    JSONObject jobject = array.getJSONObject(0);
                    judulBuku = jobject.getString("judulBuku");
                    pengarang = jobject.getString("pengarang");
                    penerbit = jobject.getString("penerbit");
                    tahun = jobject.getString("tahun");
                    harga = jobject.getString("harga");
                    stok = jobject.getString("stok");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                UpdateBuku.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        inJudul.setText(judulBuku);
                        inPengarang.setText(pengarang);
                        inPenerbit.setText(penerbit);
                        inTahun.setText(tahun);
                        inHarga.setText(harga);
                        inStok.setText(stok);
                    }
                });

            }
        });
        }

     public void update(){
         RequestBody body = new  FormBody.Builder()
                 .add("kode_buku",kdBuku)
                 .add("judul_buku",judulBuku)
                 .add("pengarang",pengarang)
                 .add("penerbit",penerbit)
                 .add("tahun",tahun)
                 .add("stok",stok)
                 .add("harga",harga)
                 .build();
         OkHttpRequest.putData(url,body).enqueue(new okhttp3.Callback() {
             @Override
             public void onFailure(Call call, IOException e) {
                 e.printStackTrace();
             }

             @Override
             public void onResponse(Call call, Response response) throws IOException {
                 if(response.isSuccessful()){
                     String pesan = "";
                     try {
                         JSONObject jsonObject = new JSONObject(response.body().string());
                         pesan = jsonObject.optString("message");

                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                     final String fpesan = pesan;
                     UpdateBuku.this.runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             Toast.makeText(getBaseContext(),fpesan,Toast.LENGTH_LONG).show();
                             Bundle b = new Bundle();
                             b.putString("kdBuku",kdBuku);
                             Intent i = new Intent(getBaseContext(),DetailBuku.class);
                             i.putExtras(b);
                             startActivity(i);
                         }
                     });
                 }
             }
         });

     }


        }

