package com.example.dell.schedulerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by DELL on 07-04-2018.
 */

public class SignUp extends AppCompatActivity {

    Button bb;
    EditText edttxt1,edttxt2,edttxt3,edttxt4,edttxt5,edttxt6;
    DatabaseReference reference;
    FirebaseDatabase database;
    SharedPreferences prefs;
    TextView txt1;
    TextView txt2;
    Boolean numeric;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        txt1 = (TextView)findViewById(R.id.txtWarning);
        txt2 = (TextView)findViewById(R.id.textWarning2) ;
        bb=(Button)findViewById(R.id.button3);
        edttxt1 = (EditText)findViewById(R.id.editText3);
        edttxt2 =(EditText)findViewById(R.id.editText4);
        edttxt3  =(EditText)findViewById(R.id.editText5);
        edttxt4 = (EditText)findViewById(R.id.editText6);
        edttxt5 = (EditText)findViewById(R.id.editText7);
        edttxt6 = (EditText)findViewById(R.id.editText8);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        database = FirebaseDatabase.getInstance().getInstance();
        reference = database.getReference("users");

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //txt1.setVisibility(View.INVISIBLE);
                //txt2.setVisibility(View.INVISIBLE);
               final String et1 = edttxt1.getText().toString();
                final String et2 = edttxt2.getText().toString();
                final String et3 = edttxt3.getText().toString();
                final String et4 = edttxt4.getText().toString();
                final String et5 = edttxt5.getText().toString();
                final String et6 = edttxt6.getText().toString();
                final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                Query q = reference.orderByChild("username").equalTo(et2);
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int flag = 0;
                        String username = "";
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            User usr = data.getValue(User.class);
                            username = usr.getUsername();
                        }  if (username.equals(et2)){
                            Toast.makeText(SignUp.this, "please chose another username", Toast.LENGTH_SHORT).show();
                        }else{
                            if(et1.equals("") || et2.equals("")|| et3.equals("")|| et4.equals("")|| et5.equals("")|| et6.equals(""))
                            {
                                flag=1;
                            }
                            if(flag == 0) {
                                if (et3.matches(emailPattern) && et4.matches("\\d+(?:\\.\\d+)?") && et4.length() == 10)//checking email validation
                                {
                                    if (et5.equals(et6))//checking password
                                    {
                                        String userid = reference.push().getKey();
                                        reference.child(userid).setValue(new User(et1, et2, et3, et4, et5, et6, prefs.getString("KEY_FCMTOKEN", "")));
                                        Toast.makeText(SignUp.this, "Connection Successful", Toast.LENGTH_LONG).show();
                                        Intent tt = new Intent(getApplicationContext(), SignIn.class);
                                        startActivity(tt);
                                    } else {
                                        txt1.setVisibility(View.VISIBLE);
                                    }
                                }else
                                {
                                    txt2.setVisibility(View.VISIBLE);
                                }
                            }else
                            {
                                Toast.makeText(SignUp.this,"Please fill all the entries.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

               /* if(et1.equals("") || et2.equals("")|| et3.equals("")|| et4.equals("")|| et5.equals("")|| et6.equals(""))
                {
                    flag=1;
                }
                if(flag == 0) {
                    if (et3.matches(emailPattern) && et4.matches("\\d+(?:\\.\\d+)?") && et4.length() == 10)//checking email validation
                    {
                        if (et5.equals(et6))//checking password
                            {
                            String userid = reference.push().getKey();
                            reference.child(userid).setValue(new User(et1, et2, et3, et4, et5, et6, prefs.getString("KEY_FCMTOKEN", "")));
                            Toast.makeText(SignUp.this, "Connection Successful", Toast.LENGTH_LONG).show();
                            Intent tt = new Intent(getApplicationContext(), SignIn.class);
                            startActivity(tt);
                        } else {
                            txt1.setVisibility(View.VISIBLE);
                        }
                }else
                    {
                        txt2.setVisibility(View.VISIBLE);
                    }
                }else
                {
                    Toast.makeText(SignUp.this,"Please fill all the entries.",Toast.LENGTH_SHORT).show();
                } */
                }});
}

    @Override
    protected void onPause(){

        super.onPause();
        finish();
    }

}