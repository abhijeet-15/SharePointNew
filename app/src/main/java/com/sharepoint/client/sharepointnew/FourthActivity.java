package com.sharepoint.client.sharepointnew;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class FourthActivity extends AppCompatActivity  {
    ArrayList product_details = new ArrayList<String>();
    int position;
    EditText mEditText;
    String code;
    int counter = 0;
    int c =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        mEditText = (EditText)findViewById(R.id.editText);
        mEditText.setText("");

        Intent i4 = getIntent();
        if(savedInstanceState == null) {
            product_details = i4.getStringArrayListExtra("product_details");
            position = i4.getIntExtra("position", 0);
            counter = 0;
            //  Toast.makeText(FourthActivity.this,"The length is "+ product_details.size(),Toast.LENGTH_LONG).show();

        }
        else {

            product_details = savedInstanceState.getStringArrayList("product_details");
            position = savedInstanceState.getInt("position");
        }

     /*   for(int j =0 ; j <product_details.size() ; j++){
            Log.i("The code is ",product_details.get(j).toString());
        } */

        //  Toast.makeText(FourthActivity.this,"YES works",Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("product_details",product_details);
        outState.putInt("value",counter);
        outState.putInt("position",position);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        product_details = savedInstanceState.getStringArrayList("product_details");

        position = savedInstanceState.getInt("position");
    }

    public void final_step(View view) {
// crash check


        code = mEditText.getText().toString();
        //  Toast.makeText(FourthActivity.this,code,Toast.LENGTH_LONG).show();

        if (! code.equals("")) {

            for (int i = 0; i < product_details.size(); i++) {

                if (code.equals(product_details.get(i))) {
                    c = 0;
                    String zero = "zero";

                    // Toast.makeText(FourthActivity.this,"YES",Toast.LENGTH_LONG).show();

                    if (zero.equals(product_details.get(i + position))) {
                        Toast.makeText(FourthActivity.this, "Invalid discount phase", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(FourthActivity.this, ThirdActivity.class);
                        intent.putStringArrayListExtra("product_details", product_details);
                        FourthActivity.this.startActivity(intent);
                        break;

                    } else {
                        String oPrice = (String) product_details.get(i + 1);
                        String oDate = (String) product_details.get(0);
                        String dPrice = (String) product_details.get(i + position);

                        Intent intent = new Intent(FourthActivity.this, FifthActivity.class);
                        intent.putExtra("oDate", oDate);
                        intent.putExtra("oPrice", oPrice);
                        intent.putExtra("dPrice", dPrice);
                        intent.putExtra("Discount", position);
                        intent.putStringArrayListExtra("product_details", product_details);

                        FourthActivity.this.startActivity(intent);
                        break;

                    }



                }



            }

            //


        }
        if(c!= 0)
            Toast.makeText(FourthActivity.this, "ENTER A VALID CODE", Toast.LENGTH_SHORT).show();

        mEditText.setText("");

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FourthActivity.this,ThirdActivity.class);

        intent.putStringArrayListExtra("product_details",product_details);
        FourthActivity.this.startActivity(intent);
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


}

