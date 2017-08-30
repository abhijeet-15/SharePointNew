package com.sharepoint.client.sharepointnew;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
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
    boolean codeFound = false;
    Intent i4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        mEditText = (EditText)findViewById(R.id.editText);
        mEditText.setText("");

         i4 = getIntent();
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


    yes();



    }

    public void yes(){


        code = mEditText.getText().toString();
        //  Toast.makeText(FourthActivity.this,code,Toast.LENGTH_LONG).show();

        if (! code.equals("")) {

            for (int i = 0; i < product_details.size(); i=i+5) {

                if (code.equals(product_details.get(i))) {
                    codeFound = true;
                    String zero = "zero";

                    // Toast.makeText(FourthActivity.this,"YES",Toast.LENGTH_LONG).show();

                    if (zero.equals(product_details.get(i + position))) {
                      //  Toast.makeText(FourthActivity.this, "Invalid discount phase", Toast.LENGTH_SHORT).show();


                            AlertDialog.Builder builder;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                builder = new AlertDialog.Builder(FourthActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                            } else {
                                builder = new AlertDialog.Builder(FourthActivity.this);
                            }
                            builder.setTitle("CODE NOT FOUND.")
                                    .setMessage("Code entered is not in the selected reduction phase")

                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                            Intent intent = new Intent(FourthActivity.this, ThirdActivity.class);
                                            FourthActivity.this.startActivity(intent);



                                        }
                                    })

                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();




                    /*    Intent intent = new Intent(FourthActivity.this, ThirdActivity.class);
                        intent.putStringArrayListExtra("product_details", product_details);
                        FourthActivity.this.startActivity(intent); */
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
                        intent.putExtra("code" ,code);
                        intent.putStringArrayListExtra("product_details", product_details);

                        FourthActivity.this.startActivity(intent);
                        break;

                    }



                }






            }

            if(!codeFound ) {

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(FourthActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(FourthActivity.this);
                }
                builder.setTitle("CODE NOT FOUND.")
                        .setMessage("Entered code does not exists")

                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            mEditText.setText("");

                                // modified code

                                if(product_details.isEmpty()){
                                    product_details = i4.getStringArrayListExtra("product_details");
                                    position = i4.getIntExtra("position", 0);
                                }



                            }
                        })

                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }

            //


        }
        // if code is empty
        else {

            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(FourthActivity.this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(FourthActivity.this);
            }
            builder.setTitle("FAILED.")
                    .setMessage("Enter a code ")

                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete







                        }
                    })

                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }

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

