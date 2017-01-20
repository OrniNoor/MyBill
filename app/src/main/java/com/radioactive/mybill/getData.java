package com.radioactive.mybill;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Orni on 1/9/2017.
 */
public class getData {

FileHandler handler;
    public void makeJsonArrayRequest() {

        String url = "http://infiniti-bd.com/infiniti/watt_data.json";


        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("on response", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object
                            String[] rating=new String[3];
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject devices = (JSONObject) response.get(i);

                                rating[i] = devices.getString("rating");


                            }

                          handler=new FileHandler();


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
        AppController.getInstance().addToRequestQueue(req);

    }
}
