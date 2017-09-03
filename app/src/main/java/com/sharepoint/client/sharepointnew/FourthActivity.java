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

public class FourthActivity extends AppCompatActivity {
    ArrayList product_details = new ArrayList<String>();
    int position;
    EditText mEditText;
    String code;
    int counter = 0;
    int c = 1;
    boolean codeFound = false;
    Intent i4;
    boolean firstDiscount = false;
    boolean secondDiscount = false;
    boolean thirdDiscount = false;
    int discountPhaseCounter = 1;
    String currentDiscountPhase = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        mEditText = (EditText) findViewById(R.id.editText);
        mEditText.setText("");

        i4 = getIntent();

        product_details = i4.getStringArrayListExtra("product_details");
        position = i4.getIntExtra("position", 0);
        counter = 0;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("product_details", product_details);
        outState.putInt("value", counter);
        outState.putInt("position", position);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        product_details = savedInstanceState.getStringArrayList("product_details");

        position = savedInstanceState.getInt("position");
    }

    public void final_step(View view) {



        yes();


    }

    public void yes() {
        discountPhaseCounter = 1;


        code = mEditText.getText().toString();


        if (!code.equals("")) {  //CHECKS IF THE ENTERED CODE ISN'T EMPTY

            for (int i = 0; i < product_details.size(); i = i + 5) {

                if (code.equals(product_details.get(i))) { // CHECK IF CODE EXISTS IN THE PRICE REDUCTION FILE
                    codeFound = true;
                    String zero = "zero";



                    for (int j = i + 2; j < (i + 5); j++) { // FINDING THE CURRENT DISCOUNT PHASE

                        if (!(zero.equals(product_details.get(j)))) {

                            discountPhaseCounter++; // CURRENT DISCOUNT PHASE  VALUE OBTAINED AS INTEGER


                        }


                    }

                    // SETTING THE DISCOUNT PHASE AS STRING

                    if (discountPhaseCounter == 2)
                        currentDiscountPhase = "First Markdown";
                    else if (discountPhaseCounter == 3)
                        currentDiscountPhase = "Further Price";
                    else if (discountPhaseCounter == 4)
                        currentDiscountPhase = "Final Markdown";


                    if (zero.equals(product_details.get(i + position))) { // IF THE  REDUCTION PHASE USER SELECTED IS EMPTY

                        AlertDialog.Builder builder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            builder = new AlertDialog.Builder(FourthActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                        } else {
                            builder = new AlertDialog.Builder(FourthActivity.this);
                        }
                        if (currentDiscountPhase != "") { // IF THE PRODUCT HAS AT LEAST ONE DISCOUNT PHASE
                            builder.setTitle("INVALID SELECTION PHASE")
                                    .setMessage("Code entered is not in the selected reduction phase. Please select " + currentDiscountPhase)


                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                            Intent intent = new Intent(FourthActivity.this, ThirdActivity.class);
                                            intent.putStringArrayListExtra("product_details", product_details);
                                            FourthActivity.this.startActivity(intent);


                                        }
                                    })

                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        } else { // IF NO REDUCTION EXISTS FOR THE PRODUCT

                            builder.setTitle("NO REDUCTION")
                                    .setMessage("No reductions currently")


                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                            Intent intent = new Intent(FourthActivity.this, ThirdActivity.class);
                                            intent.putStringArrayListExtra("product_details", product_details);
                                            FourthActivity.this.startActivity(intent);


                                        }
                                    })

                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();

                        }

                        break;

                    } else { // IF REDUCTION PHASE THAT USER SELECTED IS NOT EMPTY

                        codeFound = true;

                        if (position == discountPhaseCounter) { // IF USER SELECTED THE PHASE AND IT IS THE CURRENT DISCOUNT PHASE


                            String oPrice = (String) product_details.get(i + 1);
                            String oDate = (String) product_details.get(0);
                            String dPrice = (String) product_details.get(i + position);

                            Intent intent = new Intent(FourthActivity.this, FifthActivity.class);
                            intent.putExtra("oDate", oDate);
                            intent.putExtra("oPrice", oPrice);
                            intent.putExtra("dPrice", dPrice);
                            intent.putExtra("Discount", position);
                            intent.putExtra("code", code);
                            intent.putStringArrayListExtra("product_details", product_details);

                            FourthActivity.this.startActivity(intent);
                            break;

                        } else { // IF THE SELECTED PHASE IS NOT THE CURRENT PHASE

                            AlertDialog.Builder builder;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                builder = new AlertDialog.Builder(FourthActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                            } else {
                                builder = new AlertDialog.Builder(FourthActivity.this);
                            }
                            builder.setTitle("INVALID CODE.")
                                    .setMessage("Invalid phase selected ! Please select " + currentDiscountPhase)

                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                            mEditText.setText("");



                                            Intent intent = new Intent(FourthActivity.this, ThirdActivity.class);
                                            intent.putStringArrayListExtra("product_details", product_details);
                                            FourthActivity.this.startActivity(intent);


                                        }
                                    })

                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();


                        }
                    }


                }


            }

            if (!codeFound) { // IF ENTERED CODE DOES NOT EXIST

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


                            }
                        })

                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                Log.i("the entered is", code);


            }




        }

        else { // IF NO CODE IS ENTERED



            if (product_details.isEmpty()) {
                product_details = i4.getStringArrayListExtra("product_details");
                position = i4.getIntExtra("position", 0);
                for (int l = 0; l < product_details.size(); l++) {
                    // Log.i("The array list is","item is "+product_details.get(l));
                }
            } else {

                for (int l = 0; l < product_details.size(); l++) {
                    //   Log.i("The array list is","item is "+product_details.get(l));
                }


            }


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
        Intent intent = new Intent(FourthActivity.this, ThirdActivity.class);

        intent.putStringArrayListExtra("product_details", product_details);
        Log.i("THE SIZE", "IS " + product_details.size());
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

