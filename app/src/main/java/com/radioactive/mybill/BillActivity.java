package com.radioactive.mybill;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BillActivity extends AppCompatActivity {
TextView t2,t3;
    Button prem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        addButtons();

    }

    public void addButtons()
    {

        t2=(TextView)findViewById(R.id.txtBill2);
        t3=(TextView)findViewById(R.id.txtBillLarge);
        Intent iin=getIntent();
        Bundle b=iin.getExtras();

        if(b!=null) {
            //Toast.makeText(getApplicationContext(),b.get("bill").toString(),Toast.LENGTH_LONG).show();
            t2.setText(b.get("time").toString());
            t3.setText(b.get("bill").toString()+" BDT");
        }
        prem=(Button)findViewById(R.id.buttonPrm);
        prem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Premium version coming soon!", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        FileHandler handler=new FileHandler();
        handler.deleteFileData("device.txt",getApplicationContext());
        Intent intent=new Intent(BillActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
