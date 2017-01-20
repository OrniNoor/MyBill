package com.radioactive.mybill;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class DashboardActivity extends AppCompatActivity {
    Button backBtn,calcBtn;
LinearLayout mlout;
    Spinner spinner;
    String[] name=new String[100];
    int[] rating=new int[100];
    FileHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        addViews();
        addBackButton();
    }

    public void addViews()
    {
        handler=new FileHandler();

        String[] data=handler.readFile("device.txt",getApplicationContext()).split(":");
        int newL=0;
      //  Toast.makeText(getApplicationContext(),handler.readFile("device.txt",getApplicationContext()),Toast.LENGTH_LONG).show();
        for(int k=0;k<data.length;k++)
        {

            if(data[k].length()>=1 && !data[k].isEmpty() ) {

                String temp[] = data[k].split(";");

                SharedPreferences sp11=getApplicationContext().getSharedPreferences("ratingPref", 0);
                SharedPreferences.Editor edit11 = sp11.edit();
                edit11.putInt("rating_array_"+newL, Integer.parseInt(temp[1]));
                edit11.commit();

                name[newL] = temp[0];
//              rating[newL] = Integer.parseInt(temp[1]);

                newL++;
            }

        }
        //saving ratings
        SharedPreferences sp=getApplicationContext().getSharedPreferences("ratingPref", 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("rating_array_size", newL);
        edit.commit();



        mlout=(LinearLayout)findViewById(R.id.mlout);

        if(data.length>=1 && !data[0].isEmpty()) {
            int n=0;

            for (int k = 0; k < newL; k++) {

                    TextView tv = new TextView(getApplicationContext());
                    tv.setText(name[k]);
                    tv.setId(k + 5);
                tv.setTextColor(Color.parseColor("#212121"));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 10, 0, 0);
                tv.setLayoutParams(params);
                    mlout.addView(tv,params);

                EditText etxt = new EditText(getApplicationContext());
                     etxt.setId(k + 200);
                etxt.setHint("Time( in minutes )");
                etxt.setHintTextColor(Color.parseColor("#818181"));
                etxt.setTextColor(Color.parseColor("#212121"));

                    EditText etxt2 = new EditText(getApplicationContext());
                    etxt2.setId(k + 300);
                etxt2.setHint("Number of devices");
                etxt2.setHintTextColor(Color.parseColor("#818181"));
                etxt2.setTextColor(Color.parseColor("#212121"));
                    mlout.addView(etxt);
                    mlout.addView(etxt2);

            }
              spinner=(Spinner)findViewById(R.id.spinner);

                ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerAdapter);

            //calculating
            calcBtn=(Button)findViewById(R.id.calcBtn);
            calcBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp1 = getApplicationContext().getSharedPreferences("ratingPref", 0);
                    int n = sp1.getInt("rating_array_size", 0);

                    SharedPreferences sp = getApplicationContext().getSharedPreferences("timePref", 0);
                    SharedPreferences.Editor edit2 = sp.edit();
                    edit2.putInt("time_array_size", n);
                    edit2.commit();

                    boolean flag = true;
                    for (int i = 0; i < n; i++) {
                        EditText ed1=((EditText) findViewById(i + 200));
                        EditText ed2 =((EditText) findViewById(i + 300));

                            String input1 = ed1.getText().toString();
                            String input2 = ed2.getText().toString();
                            if (input1.isEmpty() || input2.isEmpty()) {

                                Toast.makeText(getApplicationContext(), "Please fill up all fields!", Toast.LENGTH_LONG).show();
                                return;
                            }
                          else {

                                SharedPreferences spp = getApplicationContext().getSharedPreferences("timePref", 0);
                                SharedPreferences.Editor edit3 = spp.edit();
                                edit3.putInt("time_array_"+i, Integer.parseInt(input1));
                                edit3.commit();

                                SharedPreferences sp4 = getApplicationContext().getSharedPreferences("numPref", 0);
                                SharedPreferences.Editor edit4 = sp4.edit();
                                edit4.putInt("num_array_"+i, Integer.parseInt(input2));
                                edit4.commit();
                            }

                    }

                    if (flag == true) {

                        String spin = spinner.getSelectedItem().toString();
                        int num;
                        double bill;
                        if (spin.equals("1 day")) {
                            num = 1;
                            bill = calculateBill(num);

                            Intent intentC = new Intent(DashboardActivity.this, BillActivity.class);
                            intentC.putExtra("bill", bill);
                            intentC.putExtra("time", spin);
                            startActivity(intentC);
                        } else if (spin.equals("30 days")) {
                            num = 30;
                            bill = calculateBill(num);
                            Intent intentC = new Intent(DashboardActivity.this, BillActivity.class);
                            intentC.putExtra("bill", bill);
                            intentC.putExtra("time", spin);

                            startActivity(intentC);
                        } else if (spin.equals("6 months")) {
                            num = 30 * 6;
                            bill = calculateBill(num);
                            Intent intentC = new Intent(DashboardActivity.this, BillActivity.class);
                            intentC.putExtra("bill", bill);
                            intentC.putExtra("time", spin);
                            startActivity(intentC);
                        } else if (spin.equals("12 months")) {
                            num = 30 * 12;
                            bill = calculateBill(num);
                            Intent intentC = new Intent(DashboardActivity.this, BillActivity.class);
                            intentC.putExtra("bill", bill);
                            intentC.putExtra("time", spin);
                            startActivity(intentC);
                        }


                    }
                }
            });


        }
        else {
            TextView txt = new TextView(getApplicationContext());
            txt.setTextColor(Color.parseColor("#484848"));
            txt.setText("No device added!");
            spinner=(Spinner)findViewById(R.id.spinner);
            spinner.setVisibility(View.GONE);
            mlout.addView(txt);
        }

    }
    public double calculateBill(int spinnerVal)
    {
        double bill=0;
        SharedPreferences sp1=getApplicationContext().getSharedPreferences("ratingPref", 0);
        SharedPreferences sp2=getApplicationContext().getSharedPreferences("timePref", 0);
        SharedPreferences sp3=getApplicationContext().getSharedPreferences("numPref", 0);
        int size=sp1.getInt("rating_array_size",0);
        double sum=0;
        for(int i=0;i<size;i++)
        {
            sum+=  (((sp1.getInt("rating_array_"+i,0))/1000.00)*(sp3.getInt("num_array_"+i,0)))*((double)(sp2.getInt("time_array_"+i,0)/60.00));
        }
double[] descoK={50,75,200,300,400,600,700};
        double[] bpdbK={100,400,500};
        SharedPreferences spcomp=getApplicationContext().getSharedPreferences("compPref", 0);
        String comp=spcomp.getString("comp_name",null);
        double val=sum *(double)spinnerVal;
        if(comp.equals("desco"))
        {
            if(val<=50.00)
            {
                bill=(val*3.33);
               // return bill;
            }
          else if(val>50.00 && val<=75.00)
            {
                bill=(val*3.80);
               // return bill;
            }
           else if(val>75.00 && val<=200.00)
            {
                bill=(val*5.14);
              //  return bill;
            }
          else if(val>200.00 && val<=300.00)
            {
                bill=(val*5.36);
              //  return bill;
            }
            else if(val>300.00 && val<=400.00)
            {
                bill=(val*5.63);
              //  return bill;
            }
            else if(val>400.00 && val<=600.00)
            {
                bill=(val*8.70);
              //  return bill;
            }
            else if(val>600.00)
            {
                bill=(val*9.98);
               // return bill;
            }
        }
        else if(comp.equals("bpdb"))
        {
            if(val<=100.00)
            {
                bill=(val*2.50);
              //  return bill;
            }
            else if(val>100.00 && val<=400.00)
            {
                bill=(val*3.15);
              //  return bill;
            }
            else if(val>400.00)
            {
                bill=(val*5.25);
              //  return bill;
            }
        }

        DecimalFormat df2 = new DecimalFormat("####.##");
        return Double.valueOf(df2.format(bill));

    }

    public  void addBackButton()
    {
        backBtn=(Button)findViewById(R.id.backBtn);

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
