package com.rizal.perpusonline;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rizal.perpusonline.model.BukuModel;
import com.rizal.perpusonline.utility.OkHttpRequest;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Downloader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class InputBuku extends AppCompatActivity {
    //deklarasi
    EditText inKode;
    EditText inJudul;
    EditText inPengarang;
    EditText inPenerbit;
    EditText inTahun;
    EditText inHarga;
    EditText inStok;
    Button simpan;

    String kdBuku;
    String judulBuku;
    String pengarang;
    String penerbit;
    String tahun;
    String harga;
    String stok;
    String url;

    private final OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_buku);
        setupTool();
        deklarasi();
        url = "http://bukuapi.azurewebsites.net/api/buku";
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIsi();
                post();
            }
        });
    }


    private void deklarasi() {
        inKode = (EditText)findViewById(R.id.inKode);
        inJudul = (EditText)findViewById(R.id.inJudul);
        inPengarang = (EditText)findViewById(R.id.inPengarang);
        inPenerbit = (EditText)findViewById(R.id.inPenerbit);
        inTahun = (EditText)findViewById(R.id.inTahun);
        inHarga = (EditText)findViewById(R.id.inHarga);
        inStok = (EditText)findViewById(R.id.inStok);
        simpan = (Button) findViewById(R.id.tambah);
    }
    private void setIsi() {
        kdBuku = inKode.getText().toString().trim();
        judulBuku = inJudul.getText().toString().trim();
        pengarang = inPengarang.getText().toString().trim();
        penerbit = inPenerbit.getText().toString().trim();
        tahun = inTahun.getText().toString().trim();
        harga = inHarga.getText().toString().trim();
        stok = inStok.getText().toString().trim();
    }

    public void post()  {
        RequestBody body = new  FormBody.Builder()
                        .add("kode_buku",kdBuku)
                        .add("judul_buku",judulBuku)
                        .add("pengarang",pengarang)
                        .add("penerbit",penerbit)
                        .add("tahun",tahun)
                        .add("stok",stok)
                        .add("harga",harga)
                        .build();
        OkHttpRequest.postData(url,body).enqueue(new okhttp3.Callback() {
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
                    InputBuku.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(),fpesan,Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getBaseContext(),MainActivity.class);
                            startActivity(i);
                        }
                    });
                }
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void setupTool() {
        Toolbar tol = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tol);
        getSupportActionBar().setTitle("Input Buku");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
