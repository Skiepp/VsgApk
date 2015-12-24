package com.vsg.app.vsgapp.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vsg.app.vsgapp.Menu1.PlasmaEventi;
import com.vsg.app.vsgapp.Menu3.Multimedia;
import com.vsg.app.vsgapp.R;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        boolean first_acc = sharedPref.getBoolean(this.getString(R.string.FIRST_ACCESS), true);
        if (first_acc) {
            startActivityForResult(new Intent(this, firstAccess.class), 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean(this.getString(R.string.FIRST_ACCESS), false);
                editor.commit();
            }
        }
    }

    public void Menu1 (View view) {
        startActivity(new Intent(this, PlasmaEventi.class));
    }
    public void Menu3 (View view) {
        startActivity(new Intent(this, Multimedia.class));
    }
}