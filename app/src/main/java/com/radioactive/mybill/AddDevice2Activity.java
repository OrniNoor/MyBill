package com.radioactive.mybill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddDevice2Activity extends AppCompatActivity {
    Button backBtn;
    FileHandler handler;
    TextView txtV;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device2);
        handler=new FileHandler();
        Intent iin=getIntent();
        Bundle b=iin.getExtras();

        if(b!=null && isNetworkAvailable()) {
            makeJsonArrayRequest(b.getString("mdv_id"));
        }
        else
        {
        txtV=(TextView)findViewById(R.id.textView13);
            txtV.setText("Check Your Internet Connection!");
        }

        addBackButton();
    }

    public  void addBackButton()
    {
        backBtn=(Button)findViewById(R.id.backBtn2);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void makeJsonArrayRequest(final String d_id) {

        String url ="http://infiniti-bd.com/test/mybill/cdv_data.json";


        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("on response", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            String[] rating=new String[35];
                            String[] name=new String[35];
                            String[] id =new String[35];
                          LinearLayout[] louts=new LinearLayout[8];
                            louts[0] = (LinearLayout) findViewById(R.id.lout1);
                            louts[0].setVisibility(View.INVISIBLE);
                            louts[1] = (LinearLayout) findViewById(R.id.lout2);
                            louts[1].setVisibility(View.INVISIBLE);
                            louts[2] = (LinearLayout) findViewById(R.id.lout3);
                            louts[2].setVisibility(View.INVISIBLE);
                            louts[3] = (LinearLayout) findViewById(R.id.lout4);
                            louts[3].setVisibility(View.INVISIBLE);
                            louts[4] = (LinearLayout) findViewById(R.id.lout5);
                            louts[4].setVisibility(View.INVISIBLE);
                            louts[5] = (LinearLayout) findViewById(R.id.lout6);
                            louts[5].setVisibility(View.INVISIBLE);
                            louts[6] = (LinearLayout) findViewById(R.id.lout7);
                            louts[6].setVisibility(View.INVISIBLE);
                            louts[7] = (LinearLayout) findViewById(R.id.lout8);
                            louts[7].setVisibility(View.INVISIBLE);
                            int n=0;
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject devices = (JSONObject) response.get(i);
                                name[i]=devices.getString("cdv_name");
                                id[i]=devices.getString("mdv_id");
                                rating[i] = devices.getString("rating");
                                if(id[i].equals(d_id))
                                {
                                    TextView tv = new TextView(getApplicationContext());
                                    tv.setText(name[i]);
                                    tv.setId(n+1000);
                                    tv.setTextColor(Color.parseColor("#212121"));
                                    tv.setShadowLayer(1, 0, 0, Color.GRAY);



                                    Button btn=new Button(getApplicationContext());
                                    btn.setText("+");
                                    btn.setId(i);
                                    btn.setWidth(10);
                                    btn.setHeight(10);
                                   LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200,150);
                                    params.setMargins(30, 0, 0, 0);
                                    btn.setLayoutParams(params);

                                    btn.setBackgroundColor(Color.parseColor("#FF5722"));
                                    btn.setTextColor(Color.parseColor("#FFFFFF"));

                                    louts[n].setVisibility(View.VISIBLE);
                                    louts[n].addView(tv);

                                    louts[n].addView(btn,params);


                                    n++;
                                    SharedPreferences pref = getApplicationContext().getSharedPreferences("names", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();

                                    editor.putString("name_"+i,name[i]);
                                    editor.commit();


                                    SharedPreferences pref2 = getApplicationContext().getSharedPreferences("ratings", MODE_PRIVATE);
                                    SharedPreferences.Editor editor2 = pref2.edit();

                                    editor2.putString("rating_"+i,rating[i]);
                                    editor2.commit();

                                    addDevice(btn);
                                }
                            }


                            // Toast.makeText(getApplicationContext(),handler.readFile(getApplicationContext()),Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("on error response", "Error: " + error.getMessage());


            }
        });

        // Adding request to request queue
        com.radioactive.mybill.AppController.getInstance().addToRequestQueue(req);

    }

    public void addDevice(Button btn)
    {
      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


              SharedPreferences pref = getApplicationContext().getSharedPreferences("names", MODE_PRIVATE);

              int i=v.getId();
              String name=pref.getString("name_"+i,null);
              //Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
              SharedPreferences pref2 = getApplicationContext().getSharedPreferences("ratings", MODE_PRIVATE);
              String rating=pref2.getString("rating_"+i,null);
              handler.writeFile("device.txt",name+";"+rating+":",getApplicationContext());
              Toast.makeText(getApplicationContext(),"Device added!",Toast.LENGTH_SHORT).show();
          }
      });
    }
   public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
