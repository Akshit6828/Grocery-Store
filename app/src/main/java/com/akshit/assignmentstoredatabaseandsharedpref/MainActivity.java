package com.akshit.assignmentstoredatabaseandsharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDatabase database;
    Button b1,b2;
    EditText e1,e2;
    TextView tv1,tv2;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database= new MyDatabase(this);
        pref=getSharedPreferences("login_status", Context.MODE_PRIVATE);
        editor=pref.edit();
        String status=pref.getString("login_key",null);
        if(status==null)
        {
            setContentView(R.layout.activity_main);
          //  Toast.makeText(this, "Status null", Toast.LENGTH_SHORT).show();
            b1=findViewById(R.id.login);
            b2=findViewById(R.id.register_here);
            e1=findViewById(R.id.username);
            e2=findViewById(R.id.password);
            tv1=findViewById(R.id.invalid);
            tv2=findViewById(R.id.valid);



            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     String username=e1.getText().toString();
                     String password=e2.getText().toString();
                    CustomerRec record=database.getSingleCustomerRecord(username);
                    String user=record.getUsername();
                    e1.setText(record.getUsername());
                  /* final List<CustomerRec> login_details = database.getCustomerAllRecord();
                   int t= MyDatabase.login_details.indexOf(username);
                   CustomerRec username_contains=login_details.get(t);
                 //  boolean username_contains=login_details.contains(username);*/
                  //---------------------------------------------------------------------------------------------------------------------------------------------
               /*    String x;
                  try {
                      int s = login_details.indexOf(username);
                      CustomerRec u = login_details.get(s);
                    x  = u.getUsername();
                  }
                  catch (Exception e)
                  {
                      x="";
                  }*/
                    if(user==null)
                    {
                        if(username.isEmpty()&&password.isEmpty())
                        {
                            tv1.setVisibility(View.INVISIBLE);
                            e1.setError("Field Required");
                            e2.setError("Field Required");
                        }
                        else if(username.isEmpty()) {
                            e1.setError("Field Required");
                            tv1.setVisibility(View.INVISIBLE);
                        }
                        else if(password.isEmpty()) {
                            tv1.setVisibility(View.INVISIBLE);
                            e2.setError("Field Required");
                        }
                        else
                        {
                           // Toast.makeText(MainActivity.this, "Database List is empty", Toast.LENGTH_SHORT).show();
                            tv1.setVisibility(View.VISIBLE);
                            e1.setText("");
                            e2.setText("");
                        }
                    }
                   else
                   {
                       if(password.isEmpty())
                       {
                           e2.setError("Filed Required");
                       }
                       else if(! record.getPassword().equals(password))
                       {
                          e2.setError("Wrong Password");
                       }
                       else {
                         //  Toast.makeText(MainActivity.this, "Database List Contains this user which is"+user, Toast.LENGTH_SHORT).show();
                           tv1.setVisibility(View.INVISIBLE);
                           Intent i1 = new Intent(MainActivity.this, home_page.class);
                           startActivity(i1);
                           finish();
                       }


                   }

                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i= new Intent(MainActivity.this,Register_page.class);
                    startActivity(i);
                }
            });
        }
        else
        {
            Toast.makeText(this, "status not null", Toast.LENGTH_SHORT).show();
            Intent i= new Intent(MainActivity.this,home_page.class);
            startActivity(i);
            finish();
        }

    }
}