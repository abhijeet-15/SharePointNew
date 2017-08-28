package com.sharepoint.client.sharepointnew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class FifthActivity extends AppCompatActivity {
    TextView mOrigiinalDate;
    TextView mOriginalPrice;
    TextView mDiscountedPrice;
    TextView mDiscountName;
    ArrayList product_details = new ArrayList();
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        mOrigiinalDate = (TextView)findViewById(R.id.original_date);
        mDiscountedPrice = (TextView)findViewById(R.id.textView2);
        mOriginalPrice = (TextView)findViewById(R.id.textView4);
        mDiscountName = (TextView)findViewById(R.id.textView);



        Intent i = getIntent();
        String a = i.getStringExtra("oDate");
        String b = i.getStringExtra("oPrice");
        String c =i.getStringExtra("dPrice");
         n = i.getIntExtra("Discount",0);
        product_details = i.getStringArrayListExtra("product_details");

        mOrigiinalDate.setText(a);
        mDiscountedPrice.setText("$"+c);
        mOriginalPrice.setText("$"+b);

        if(n == 2)
            mDiscountName.setText("First Markdown");
        else if(n == 3)
            mDiscountName.setText("Further Price");
        else
            mDiscountName.setText("Final Markdown");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FifthActivity.this,FourthActivity.class);
        intent.putExtra("position",n);
        intent.putStringArrayListExtra("product_details",product_details);
        FifthActivity.this.startActivity(intent);


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
