package com.radioactive.mybill;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by Orni on 1/8/2017.
 */
public class FileHandler {

    public String readFile(String filename,Context context)
    {
    String data="";
        try {
            InputStream inputStream = context.openFileInput(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                data = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("file activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("file activity", "Can not read file: " + e.toString());
        }



        return data;

    }
    public void writeFile(String FILENAME,String data,Context context)
    {

        try {
            FileOutputStream file = context.openFileOutput(FILENAME, Context.MODE_APPEND) ;
            String newData=data+":";
            file.write(newData.getBytes());
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void deleteFileData(String FILENAME,Context context)
    {


        try {
            FileOutputStream file = context.openFileOutput(FILENAME, Context.MODE_PRIVATE) ;
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
