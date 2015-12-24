package com.vsg.app.vsgapp.Menu1;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Plasma extends AppCompatActivity {

    private ViewPager viewPager;
    private SwipeAdapter swipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plasma);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        swipeAdapter = new SwipeAdapter(this);

        String url = this.getString(R.string.PHP_PLASMA);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int status = 0;
                        try {
                            status = response.getInt("status");
                            if (status != 0) {
                                Toast.makeText(Plasma.this, "Connection error", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                int num = response.getInt("num");
                                swipeAdapter.setFinalCount(num);

                                JSONArray array = response.getJSONArray("values");
                                RequestQueue queue = Volley.newRequestQueue(Plasma.this);
                                for (int i = 0; i < num; i++) {
                                    ImageRequest request = new ImageRequest(array.getString(i),
                                            new Response.Listener<Bitmap>() {
                                                @Override
                                                public void onResponse(Bitmap bitmap) {
                                                    swipeAdapter.addBitmap(bitmap);
                                                    if (swipeAdapter.endReached())
                                                        viewPager.setAdapter(swipeAdapter);
                                                }
                                            }, 0, 0, null,
                                            new Response.ErrorListener() {
                                                public void onErrorResponse(VolleyError error) {
                                                }
                                            });
                                    queue.add(request);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Plasma.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(Plasma.this).add(jsObjRequest);
    }

}
