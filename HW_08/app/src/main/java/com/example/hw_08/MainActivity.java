package com.example.hw_08;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity implements View.OnClickListener {
    ImageView thanhnien, vnex, tuoitre;

    int index = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thanhnien = (ImageView) findViewById(R.id.thanhnien);
        vnex = (ImageView) findViewById(R.id.vnex);
        tuoitre = (ImageView) findViewById(R.id.tuoitre);

        thanhnien.setOnClickListener(this);
        vnex.setOnClickListener(this);
        tuoitre.setOnClickListener(this);

        this.setTitle("NEWS APP");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.thanhnien):{
                index = 0;
                break;
            }
            case (R.id.vnex):{
                index = 1;
                break;
            }
            case (R.id.tuoitre):{
                index = 2;
                break;
            }
        }
        Intent callChannels = new Intent(MainActivity.this, Channels.class);
        //prepare a Bundle and add the input arguments: url & caption
        Bundle myData = new Bundle();
        myData.putInt("Index", index);
        callChannels.putExtras(myData);
        startActivity(callChannels);
    }
}