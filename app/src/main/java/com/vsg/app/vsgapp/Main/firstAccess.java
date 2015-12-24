package com.vsg.app.vsgapp.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.vsg.app.vsgapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class firstAccess extends AppCompatActivity {

    private TextView numberCont;
    private String IMEI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_access);

        numberCont = (TextView) findViewById(R.id.numberCont);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        IMEI = telephonyManager.getDeviceId().toString();
        //IMEI = "Gesu";
    }

    public void Login(View view) {
        String url = this.getString(R.string.PHP_LOGIN) + "?cell=" + numberCont.getText().toString();
        url += "&IMEI=" + IMEI;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent returnIntent = new Intent();
                        try {
                            int status = response.getInt("status");
                            switch (status) {
                                case 0:
                                    Toast.makeText(firstAccess.this, response.getString("descr"), Toast.LENGTH_SHORT).show();
                                    returnIntent.putExtra("result", 0);
                                    setResult(Activity.RESULT_OK, returnIntent);
                                    finish();
                                case -1:
                                case -2:
                                case -3:
                                    Toast.makeText(firstAccess.this, response.getString("descr"), Toast.LENGTH_SHORT).show();
                                    setResult(Activity.RESULT_CANCELED, returnIntent);
                                    finish();
                                    break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
        };
        Volley.newRequestQueue(this).add(jsObjRequest);
    }

}
