package com.vsg.app.vsgapp.Menu1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vsg.app.vsgapp.Menu1.Plasma;
import com.vsg.app.vsgapp.R;

public class PlasmaEventi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plasma_eventi);
    }

    public void Plasma(View view) {
        startActivity(new Intent(this, Plasma.class));
    }

}
