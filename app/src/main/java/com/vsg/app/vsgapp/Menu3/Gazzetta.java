package com.vsg.app.vsgapp.Menu3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vsg.app.vsgapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Gazzetta extends AppCompatActivity {

    private Map<Date, Integer> pages = new TreeMap<>();
    private ListView numeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gazzetta);
        numeri = (ListView) findViewById(R.id.numeri_gazze);

        String url = this.getString(R.string.PHP_GAZZETTA);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int status = 0;
                        try {
                            status = response.getInt("status");
                            if (status != 0) {
                                Toast.makeText(Gazzetta.this, "Connection error", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                int num = response.getInt("num");
                                DateFormat df = new SimpleDateFormat("dd.MM.yy");
                                JSONObject tmp;

                                JSONArray array = response.getJSONArray("values");
                                RequestQueue queue = Volley.newRequestQueue(Gazzetta.this);
                                for (int i = 0; i < num; i++) {
                                    tmp = array.getJSONObject(i);
                                    pages.put(df.parse(tmp.getString("date")), tmp.getInt("num"));
                                }
                                //for (Date d : pages.keySet()) {
                                //    numeri.add
                                //}
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Gazzetta.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(Gazzetta.this).add(jsObjRequest);
    }
}
