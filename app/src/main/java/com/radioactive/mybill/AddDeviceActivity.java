package com.radioactive.mybill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AddDeviceActivity extends AppCompatActivity {
Button backBtn,calcBtn;

    ImageButton img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12;
    FileHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        handler=new FileHandler();

        addImageButton();
        addBackButton();

    }

    public  void addImageButton()
    {
        img1=(ImageButton)findViewById(R.id.imageButton);
        img2=(ImageButton)findViewById(R.id.imageButton2);
        img3=(ImageButton)findViewById(R.id.imageButton3);
        img4=(ImageButton)findViewById(R.id.imageButton4);
        img5=(ImageButton)findViewById(R.id.imageButton5);
        img6=(ImageButton)findViewById(R.id.imageButton6);
        img7=(ImageButton)findViewById(R.id.imageButton7);
        img8=(ImageButton)findViewById(R.id.imageButton8);
        img9=(ImageButton)findViewById(R.id.imageButton9);
        img10=(ImageButton)findViewById(R.id.imageButton10);
        img11=(ImageButton)findViewById(R.id.imageButton11);
        img12=(ImageButton)findViewById(R.id.imageButton12);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDeviceActivity.this,AddDevice2Activity.class);
                intent.putExtra("mdv_id","1");
                startActivity(intent);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDeviceActivity.this,AddDevice2Activity.class);
                intent.putExtra("mdv_id","2");
                startActivity(intent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDeviceActivity.this,AddDevice2Activity.class);
                intent.putExtra("mdv_id","3");
                startActivity(intent);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDeviceActivity.this,AddDevice2Activity.class);
                intent.putExtra("mdv_id","4");
                startActivity(intent);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDeviceActivity.this,AddDevice2Activity.class);
                intent.putExtra("mdv_id","5");
                startActivity(intent);
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDeviceActivity.this,AddDevice2Activity.class);
                intent.putExtra("mdv_id","6");
                startActivity(intent);
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDeviceActivity.this,AddDevice2Activity.class);
                intent.putExtra("mdv_id","7");
                startActivity(intent);
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDeviceActivity.this,AddDevice2Activity.class);
                intent.putExtra("mdv_id","8");
                startActivity(intent);
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDeviceActivity.this,AddDevice2Activity.class);
                intent.putExtra("mdv_id","9");
                startActivity(intent);
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDeviceActivity.this,AddDevice2Activity.class);
                intent.putExtra("mdv_id","10");
                startActivity(intent);
            }
        });
        img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDeviceActivity.this,AddDevice2Activity.class);
                intent.putExtra("mdv_id","11");
                startActivity(intent);
            }
        });
        img12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddDeviceActivity.this,CustomActivity.class);
                intent.putExtra("mdv_id","12");
                startActivity(intent);
            }
        });
    }




    public  void addBackButton()
    {
        backBtn=(Button)findViewById(R.id.backBtn);
        calcBtn=(Button)findViewById(R.id.calcBtn);

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentC=new Intent(AddDeviceActivity.this,DashboardActivity.class);
                startActivity(intentC);
            }
        });
  backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
         SharedPreferences sp=getApplicationContext().getSharedPreferences("compPref", 0);
          SharedPreferences.Editor editor = sp.edit();
          if(sp.contains("comp_name"))
          {
              editor.remove("comp_name");

          }
          handler.deleteFileData("raw.txt",getApplicationContext());
          handler.deleteFileData("device.txt",getApplicationContext());
         finish();
      }
  });

    }
    @Override
    public void onBackPressed()
    {
        handler.deleteFileData("device.txt",getApplicationContext());
        finish();
    }

}
