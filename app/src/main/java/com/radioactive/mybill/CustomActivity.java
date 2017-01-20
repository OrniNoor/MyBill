package com.radioactive.mybill;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomActivity extends AppCompatActivity {
EditText ed1,ed2;
    TextView txt2;
 Button btn1,backBtn;
    FileHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        addBackButton();
        txt2=(TextView)findViewById(R.id.textView22);
        ed1=(EditText)findViewById(R.id.editText1);
        ed2=(EditText)findViewById(R.id.editText2);
        btn1=(Button)findViewById(R.id.buttonAdd);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable()) {
                    String in1 = ed1.getText().toString();
                    String in2 = ed2.getText().toString();
                    if (in1.isEmpty() || in2.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please fill up all fields!", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        handler.writeFile("device.txt", in1 + ";" + in2 + ":", getApplicationContext());
                        Toast.makeText(getApplicationContext(), "Device added!", Toast.LENGTH_LONG).show();


                    }
                } else {
                    txt2 = (TextView) findViewById(R.id.textView22);
                    txt2.setText("Check Your Internet Connection!");
                    return;
                }
            }
        });

    }
    public  void addBackButton()
    {
        backBtn=(Button)findViewById(R.id.backBtn22);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent=new Intent(CustomActivity.this,AddDeviceActivity.class);
                startActivity(intent);
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
