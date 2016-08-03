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

    private final OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_buku);
        setupTool();

    }



    private void deklarasi() {

    }
    private void setIsi() {

    }

    public void post()  {




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
