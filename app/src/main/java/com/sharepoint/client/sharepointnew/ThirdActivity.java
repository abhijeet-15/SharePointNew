package com.sharepoint.client.sharepointnew;

import android.Manifest;

import android.app.ProgressDialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;



public class ThirdActivity extends AppCompatActivity  {

    ArrayList product_details = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        product_details = intent.getStringArrayListExtra("product_details");


    }




    public void get_code1(View view) {



            Intent i = new Intent(ThirdActivity.this, FourthActivity.class);
            i.putExtra("position", 2);
            i.putStringArrayListExtra("product_details", product_details);

            ThirdActivity.this.startActivity(i);



    }

    public void get_code2(View view) {



            Intent i = new Intent(ThirdActivity.this, FourthActivity.class);
            i.putExtra("position", 3);
            i.putStringArrayListExtra("product_details", product_details);

            ThirdActivity.this.startActivity(i);

    }

    public void get_code3(View view) {



            Intent i = new Intent(ThirdActivity.this, FourthActivity.class);
            i.putExtra("position", 4);
            i.putStringArrayListExtra("product_details", product_details);
            // Toast.makeText(ThirdActivity.this, "size" + product_details.size(), Toast.LENGTH_SHORT).show(); // throws null pointer exception after top left back button is pressed

            ThirdActivity.this.startActivity(i);


    }

    public void onBackPressed() {
        Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);

        intent.putStringArrayListExtra("product_details", product_details);

        // edited for clearning the backstage

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);

        ThirdActivity.this.startActivity(intent);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // changing to downlaod here








}
