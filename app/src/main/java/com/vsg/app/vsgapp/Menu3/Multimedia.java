package com.vsg.app.vsgapp.Menu3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vsg.app.vsgapp.Generic.SwipeAdapter;
import com.vsg.app.vsgapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Multimedia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);
    }

    public void Gazzetta(View view) {
        startActivity(new Intent(this, Gazzetta.class));
    }
}
