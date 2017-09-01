package com.sharepoint.client.sharepointnew;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ThirdActivity extends AppCompatActivity  {

    Intent i;
    boolean dialougeChecker = false;

    // used variables
    Boolean failedDownload;

    String access_token;
    String path = "/Documents%20partages/Document.docx";
    String fetched_data;
    String client_id = "42739324-b19a-4dab-B18A-cf87cec0417b%40a3ab0148-9db7-440f-ba18-1f0c2ae80969";
    String client_secret = "VgmSpSDdQ2zOwXRhqmBA7xRfn4itxEmfZj0PPowjvs0%3D";
    ArrayList product_details = new ArrayList();
    ProgressDialog dialog;
    String res;


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_PHONE_STATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);



     /*   Intent i3 = getIntent();
        product_details = i3.getStringArrayListExtra("product_details");
        i = new Intent(ThirdActivity.this, FourthActivity.class); */

        verifyStoragePermissions(ThirdActivity.this);
        failedDownload = isNetworkOnline(ThirdActivity.this);
        Log.i("failedDownload" , "is"+failedDownload);
        proceed();






    }

    public void proceed(){

       /* if(dialougeChecker )
            dialog.dismiss(); */



        if (failedDownload ==  true) {

            new PostTask().execute(); }
        else {

            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(ThirdActivity.this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(ThirdActivity.this);
            }
            builder.setTitle("FAILED.")
                    .setMessage("Download of Price Reduction File Failed")

                    .setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete

                            failedDownload = isNetworkOnline(ThirdActivity.this) ;
                            if(failedDownload)
                                new PostTask().execute();
                            else
                                proceed();


                        }
                    })

                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }

    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE

            );


        }
    }



    public static boolean isNetworkOnline(Context con)
    {
        boolean status = false;
        try
        {
            ConnectivityManager cm = (ConnectivityManager) con
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);

            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);

                if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                    status = true;
                } else {
                    status = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return status;
    }


    public void get_code1(View view) {

        if(failedDownload == true) {

            Intent i = new Intent(ThirdActivity.this, FourthActivity.class);
            i.putExtra("position", 2);
            i.putStringArrayListExtra("product_details", product_details);

            ThirdActivity.this.startActivity(i);
        }
        else
            proceed();


    }

    public void get_code2(View view) {

        if (failedDownload == true) {

            Intent i = new Intent(ThirdActivity.this, FourthActivity.class);
            i.putExtra("position", 3);
            i.putStringArrayListExtra("product_details", product_details);

            ThirdActivity.this.startActivity(i);
        }
        else
            proceed();
    }

    public void get_code3(View view) {

        if(failedDownload == true) {

            Intent i = new Intent(ThirdActivity.this, FourthActivity.class);
            i.putExtra("position", 4);
            i.putStringArrayListExtra("product_details", product_details);
            // Toast.makeText(ThirdActivity.this, "size" + product_details.size(), Toast.LENGTH_SHORT).show(); // throws null pointer exception after top left back button is pressed

            ThirdActivity.this.startActivity(i);
        }
        else
            proceed();
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

    public class PostTask extends AsyncTask<String, String, String> {
        public void main(String[] args) {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialougeChecker = true;
            //  Toast.makeText(MainActivity.this, "DOWNLOAD STARTED", Toast.LENGTH_SHORT).show();



            dialog  = new ProgressDialog(ThirdActivity.this);
            dialog.setMessage("Wait");
            dialog.setCancelable(false);
            dialog.show();






        }

        @Override
        protected String doInBackground(String... data) {
            // Create a new HttpClient and Post Header


            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials%09&client_id%09=42739324-b19a-4dab-B18A-cf87cec0417b%40a3ab0148-9db7-440f-ba18-1f0c2ae80969&client_secret%09=VgmSpSDdQ2zOwXRhqmBA7xRfn4itxEmfZj0PPowjvs0%3D&resource%09=00000003-0000-0ff1-ce00-000000000000%2Fwilliems.sharepoint.com%40a3ab0148-9db7-440f-ba18-1f0c2ae80969");

            Request request = new Request.Builder()
                    .url("https://accounts.accesscontrol.windows.net/a3ab0148-9db7-440f-ba18-1f0c2ae80969/tokens/OAuth/2")
                    .post(body)
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "8ebfcd3b-fd82-2154-370a-d710c2c3875e")
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String ss = response.body().contentType().toString();
                Log.i("the content type is", ss);
                ss = response.body().string();
                //  Log.i("json is" , ss);
                JSONObject jsonObject = new JSONObject(ss);
                access_token = jsonObject.getString("access_token");
                Log.i("the access token is", access_token);

            } catch (Exception e) {
                e.printStackTrace();


            }

            // get request

            OkHttpClient client1 = new OkHttpClient();


            Request request1 = new Request.Builder().
                    addHeader("Authorization", "Bearer " + access_token).
                    addHeader("Accept", "application/json;odata=verbose").
                    url("https://williems.sharepoint.com/_api/web/GetFileByServerRelativeUrl('/Documents%20partages/PriceReduction.csv')/$value?@target='D:/Dicn.docx'").
                    build();

            try {

                Response r1 = client1.newCall(request1).execute();
                fetched_data = r1.body().string();
                Log.i("The json is", fetched_data);
            } catch (Exception e) {
                e.printStackTrace();


            }

            // writing to file

            File file = null;
            FileOutputStream outputStream;
            // modified code
            if (Build.VERSION.SDK_INT < 20) {


                try {

                    file = new File(getExternalFilesDir(null), "Test.txt");


                    outputStream = new FileOutputStream(file);
                    outputStream.write(fetched_data.getBytes());
                    outputStream.close();
                    Log.i("The file is written ?", "YES");

                } catch (Exception e) {
                    e.printStackTrace();



                }


            } else {

                try {

                    file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "TEST.txt");

                    outputStream = new FileOutputStream(file);
                    outputStream.write(fetched_data.getBytes());
                    outputStream.close();
                    Log.i("The file is written ?", "YES");



                } catch (Exception e) {
                    e.printStackTrace();



                }


            }

            if (file.length() != 0) {

                int lineIndex = 0;
                CSVReader reader = null;
                try {
                    reader = new CSVReader(new FileReader(file));


                    String[] nextLine;
                    int i = 0;
                    while ((nextLine = reader.readNext()) != null) {
                        // nextLine[] is an array of values from the line
                        i++;

                        //       if(i > 3) {


                        String s1 = nextLine[0];
                        product_details.add(s1);

                        String s2 = nextLine[1];
                        product_details.add(s2);

                        String s3 = nextLine[2];
                        if(s3 == "")
                            s3 = "zero";
                        product_details.add(s3);

                        String s4 = nextLine[3];
                        if(s4 == "")
                            s4 = "zero";
                        product_details.add(s4);

                        String s5 = nextLine[4];
                        if(s5 == "")
                            s5 = "zero";
                        product_details.add(s5);


                        //     }





                    }
                    res = "success";
                    // return "success";
                } catch (Exception e) {
                    e.printStackTrace();
                    String res = "failed";

                    proceed();
                    return "failed";

                }


            }
            return res;
        }
        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
            dialog.dismiss();


            if (s == "success"){
                //  Toast.makeText(MainActivity.this, "DOWNLOAD SUCCESSFUL", Toast.LENGTH_LONG).show();
                if((Build.VERSION.SDK_INT < 20)) {
                    Toast.makeText(ThirdActivity.this, "Downlaoded at : sdcard/Android/data/com.sharepoint.client/files/Test.txt", Toast.LENGTH_SHORT).show();
                    dialougeChecker = false;
                }  else
                    Toast.makeText(ThirdActivity.this ,"Downlaoded at : storage/emulated/0/Download/Test.txt",Toast.LENGTH_LONG).show();



               /*  Intent intent = new Intent(ThirdActivity.this,FourthActivity.class);
                intent.putStringArrayListExtra("product_details",product_details);
                ThirdActivity.this.startActivity(intent);  */

            }
            else
            {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(ThirdActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(ThirdActivity.this);
                }
                builder.setTitle("FAILED.")
                        .setMessage("Download of Price Reduction File Failed")

                        .setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete

                                failedDownload = isNetworkOnline(ThirdActivity.this) ;
                                if(failedDownload)
                                    new PostTask().execute();
                                else
                                    proceed();


                            }
                        })

                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }




        }
    }






}



