package com.example.dell.schedulerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by DELL on 07-04-2018.
 */

public class Time extends AppCompatActivity {

    Button b6;
    EditText txt1, txt2, txt3, txt4;
    int th1, th2, tm1, tm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time);

        b6 = (Button) findViewById(R.id.button6);
        txt1 = (EditText) findViewById(R.id.editText2);
        txt2 = (EditText) findViewById(R.id.editText3);
        txt3 = (EditText) findViewById(R.id.editText4);
        txt4 = (EditText) findViewById(R.id.editText5);
        //th1 = Integer.parseInt(txt1.getText().toString());
        //th2 = Integer.parseInt(txt3.getText().toString());
        //tm1 = Integer.parseInt(txt2.getText().toString());
        //tm2 = Integer.parseInt(txt4.getText().toString());

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txt1.getText().toString().equals("") || txt2.getText().toString().equals("") || txt3.getText().toString().equals("") ||
                        txt4.getText().toString().equals("")) {
                    Toast.makeText(Time.this, "Enter details for all the fields", Toast.LENGTH_LONG).show();
                } else {
                    th1 = Integer.parseInt(txt1.getText().toString());
                    th2 = Integer.parseInt(txt3.getText().toString());
                    tm1 = Integer.parseInt(txt2.getText().toString());
                    tm2 = Integer.parseInt(txt4.getText().toString());

                    if (th1 >= 0 && th2 >= 0 && th1 <= 24 && th2 <= 24) {
                        if ( tm1 >= 0 && tm2 >= 0 && tm1 <= 59 && tm2 <= 59) {

                            //Toast.makeText(Time.this,"Time Retrieve",Toast.LENGTH_LONG).show();
                            //else
                            //Toast.makeText(Time.this,"Time value not recovered",Toast.LENGTH_LONG).show();
                            Intent i8 = new Intent("com.example.dell.PLACEPREFERENCE");
                            startActivity(i8);
                        } else {
                            Toast.makeText(Time.this, "Enter time in minutes is invalid", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                            Toast.makeText(Time.this,"Enter time in hours according to 24 hour clock",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}