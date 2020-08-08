package com.akshit.assignmentstoredatabaseandsharedpref;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_page extends AppCompatActivity {
    DialogInterface d;
    MyDatabase database;
    Button b1;
    EditText e1,e2,e3,e4,e5;
    int flag=0;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        database= new MyDatabase(this);
        b1 = findViewById(R.id.register_here);
        e1 = findViewById(R.id.name);
        e2 = findViewById(R.id.phone_number);
        e3 = findViewById(R.id.email);
        e4 = findViewById(R.id.username);
        e5 = findViewById(R.id.password);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=0,i;
                String name = e1.getText().toString();
                String phonenumber = e2.getText().toString();
                String email = e3.getText().toString();
                String username = e4.getText().toString();
                String password = e5.getText().toString();
                String[] arr = new String[]{name,phonenumber,email,username,password};
                for(i=0;i<5;i++)
                {
                    String get=arr[i];
                    int index=to_check(get,i);
                    switch(index)
                    {
                        case 0: e1.setError("Field Required");count=1;
                        break;
                        case 1: e2.setError("Field Required");count=1;
                        break;
                        case 2: e3.setError("Field Required");count=1;
                            break;
                        case 3: e4.setError("Field Required");count=1;
                            break;
                        case 4: e5.setError("Field Required"); count=1;
                        break;
                        case 10: if(i==4&&count==0)
                                flag=1;
                    }
                }
                if(flag==1)
                {
                    CustomerRec record=database.getSingleCustomerRecord(username);
                    String user=record.getUsername();
                    if(user==null)
                    {
                        CustomerRec rec= new CustomerRec(username,password,name,email,phonenumber);
                        long result= database.insertCustomer(rec);
                        if(result>0)
                        {
                           Toast.makeText(Register_page.this, "Record inserted Sucessfully", Toast.LENGTH_LONG).show();
                            builder= new AlertDialog.Builder(Register_page.this);
                            builder.setTitle("Registration Status");
                            builder.setMessage("Thankyou for the Registration😊.\nPlease Click 'Your Welcome' to Proceed and Wait For  Seconds \n Your May Also Click on the Screen to Dismiss the Dialog..");
                            builder.setIcon(R.mipmap.ic_launcher);
                            builder.setPositiveButton("Your Welcome", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    d=dialog;
                                    //dialog.dismiss()
                                }
                            });
                            builder.show();
                            new CountDownTimer(5000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }

                                @Override
                                public void onFinish() {
                                 d.dismiss();

                                }
                            }.start();


                        }
                        else
                        {
                            Toast.makeText(Register_page.this, "Query Problem", Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        if(record.getUsername().equals(username)) {
                            Toast.makeText(Register_page.this, " Username Already Exist...Please Choose Another Username", Toast.LENGTH_LONG).show();
                        }

                    }



                }

            }
        });

    }
    int to_check(String get,int i) {
        int val= (get.isEmpty()) ? i :10;
        return val;
    }

}
