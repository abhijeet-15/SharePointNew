package com.sharepoint.client.sharepointnew;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ArrayList product_details = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      /*  Intent i2 = getIntent();
        product_details = i2.getStringArrayListExtra("product_details"); */



    }

    public void select(View view) {






       Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
      //  i.putStringArrayListExtra("product_details",product_details);
        SecondActivity.this.startActivity(i);
    }




}
