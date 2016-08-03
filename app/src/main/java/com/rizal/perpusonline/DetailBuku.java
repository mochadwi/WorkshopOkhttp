package com.rizal.perpusonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rizal.perpusonline.utility.OkHttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DetailBuku extends AppCompatActivity {
    public static String kdBuku = "";
    TextView judul;
    TextView pengarangs;
    TextView penerbit;
    TextView tahun;
    String juduls;
    String pengarang;
    String penerbits;
    String url = "";
    int tahuns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_buku);
        kdBuku = getIntent().getExtras().getString("kdBuku");
        url = "http://bukuapi.azurewebsites.net/api/buku/"+kdBuku;

        judul = (TextView)findViewById(R.id.judul_bukus);
        pengarangs = (TextView)findViewById(R.id.spengarang);
        penerbit = (TextView)findViewById(R.id.spenerbit);
        tahun = (TextView)findViewById(R.id.stahun);

        prepareData();
        setupTool();



    }
    private void prepareData() {
        OkHttpClient okhtpp = new OkHttpClient();
        Call call = getData(url,okhtpp);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){

                    try {

                        JSONObject obeject = new JSONObject(response.body().string());
                        JSONArray jArray = new JSONArray(obeject.getString("result"));

                            JSONObject jsonObject = jArray.getJSONObject(0);

                                    String kdBuku = jsonObject.getString("kdBuku");
                                    juduls = jsonObject.getString("judulBuku");
                                    pengarang=jsonObject.getString("pengarang");
                                    penerbits=jsonObject.getString("penerbit");
                                    tahuns = jsonObject.getInt("harga");


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(),"gagal ambil data",Toast.LENGTH_LONG).show();
                    }
                    DetailBuku.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            judul.setText(juduls);
                            pengarangs.setText(pengarang);
                            penerbit.setText(penerbits);
                            tahun.setText(String.valueOf(tahuns));

                        }
                    });
                }
            }
        });
    }
    private void hapus() {

    }
    private void setupTool(){
        Toolbar tool = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Buku");


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private Call getData(String url, OkHttpClient client) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return client.newCall(request);
    }
}
