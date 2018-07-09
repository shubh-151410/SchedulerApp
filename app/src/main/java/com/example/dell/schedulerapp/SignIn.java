package com.example.dell.schedulerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by DELL on 07-04-2018.
 */

public class SignIn extends AppCompatActivity {

    Button b;
    EditText editText,editText1;
    FirebaseDatabase database;
    DatabaseReference reference;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        b=(Button)findViewById(R.id.button4);

        editText = (EditText)findViewById(R.id.editText);
        editText1 = (EditText)findViewById(R.id.editText2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             final String user = editText.getText().toString();
             final String passwd = editText1.getText().toString();
             if(user.isEmpty()||passwd.isEmpty())
             {
                 Toast.makeText(SignIn.this, "Empty FIelds", Toast.LENGTH_SHORT).show();
             }else
             {
                 Query q = reference.orderByChild("username").equalTo(user);

                 q.addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         String spass = "";
                         String name = "";
                         for(DataSnapshot data : dataSnapshot.getChildren())
                         {
                             User usr = data.getValue(User.class);
                             spass = usr.getPassword();
                             name = usr.getName();
                         }
                         if (spass.equals(passwd))
                         {

                             Intent intent = new Intent(getApplicationContext(),HomeNavigation.class);
                             intent.putExtra("USER_NAME",name);
                             startActivity(intent);
                           startActivity(intent);
                         }
                     }


                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });
             }

            }
        });

    }

    @Override
    protected void onPause(){

        super.onPause();
        finish();
    }
}