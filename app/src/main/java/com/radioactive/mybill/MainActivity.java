package com.radioactive.mybill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

ImageButton cBtn1,cBtn2;
SharedPreferences sp;
    FileHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButtonOperations();



    }
public void ButtonOperations()
{
    cBtn1=(ImageButton)findViewById(R.id.compBtn1);
    cBtn2=(ImageButton)findViewById(R.id.compBtn2);
    sp=getApplicationContext().getSharedPreferences("compPref", 0);
    SharedPreferences.Editor editor = sp.edit();
    if(sp.contains("comp_name"))
    {
        editor.remove("comp_name");

    }

    cBtn1.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            sp=getApplicationContext().getSharedPreferences("compPref", 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("comp_name","desco");
            editor.commit();
            Intent intent=new Intent(MainActivity.this,AddDeviceActivity.class);
            startActivity(intent);
        }
    });

    cBtn2.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            sp=getApplicationContext().getSharedPreferences("compPref", 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("comp_name","bpdb");
            editor.commit();
            Intent intent=new Intent(MainActivity.this,AddDeviceActivity.class);
            startActivity(intent);
        }
    });

}
    @Override
    public void onBackPressed()
    {
        finish();
    System.exit(0);
    }

    }

