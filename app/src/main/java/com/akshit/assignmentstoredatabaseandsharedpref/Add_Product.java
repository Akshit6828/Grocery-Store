package com.akshit.assignmentstoredatabaseandsharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Product extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button b1;
MyDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product);
        database= new MyDatabase(this);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        e4=findViewById(R.id.e4);
        b1=findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productid=e1.getText().toString();
                String productname=e2.getText().toString();
                String productprice=e3.getText().toString();
                String productquantity=e4.getText().toString();
                String id=e1.getText().toString();
             ProductRec rec= new ProductRec(productid,productname,productprice,productquantity);
                long result= database.insertProduct(rec);
                if(result>0)
                {
                    Toast.makeText(Add_Product.this, "Product Added Successfully.", Toast.LENGTH_LONG).show();
                    Intent i= new Intent(Add_Product.this,home_page.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Add_Product.this, "Query Problem", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
