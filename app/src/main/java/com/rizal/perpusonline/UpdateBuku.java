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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_buku);
        kdBuku = getIntent().getExtras().getString("kode_buku");
        Toast.makeText(UpdateBuku.this, "Loading " + kdBuku, Toast.LENGTH_LONG).show();
        //setupTool();

            setDeklarasi();
            getDataId();


    }
    private void setupTool(){
        Toolbar tool = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Buku");


    }
    private void setIsi() {

    }

    private void setDeklarasi() {



    }

    public void getDataId() {



                }

    public void update() throws IOException {

    }



    private Call getData(String url, OkHttpClient client) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return client.newCall(request);
    }
}
