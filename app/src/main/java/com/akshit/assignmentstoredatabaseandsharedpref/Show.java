package com.akshit.assignmentstoredatabaseandsharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Show extends AppCompatActivity {
    EditText e1;
    Button b1;
    MyDatabase database;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        arrayList= new ArrayList<String>();
        arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        database= new MyDatabase(this);
        lv=findViewById(R.id.listview);
        lv.setVisibility(View.INVISIBLE);
        e1=findViewById(R.id.e1);
        b1=findViewById(R.id.search);
        arrayAdapter.notifyDataSetChanged();
        lv.setAdapter(arrayAdapter);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                final List<ProductRec> list= database.getProductAllRecord();
                String id = e1.getText().toString();
                if (id.isEmpty()) {
                    e1.setError("Required Field");
                }
                else {
                    int i;
                    ProductRec record = database.getSingleProductRecord(id);
                    String productid = record.getProductid();
                    if (productid != null) {
                       /* if(arrayList.size()!=0) {
                            for (i = 0; i < arrayList.size(); i++) {
                                arrayList.remove(i);
                            }
                        }*/

                        for (ProductRec rec : list) {
                            String id_generated = rec.getProductid();
                            if (id_generated.equals(id)) {
                             //  Toast.makeText(Show.this, "id matched and is "+id_generated, Toast.LENGTH_SHORT).show();
                                arrayList.add("Product ID :" + rec.getProductid());
                                arrayList.add("Product Name :" + rec.getProductname());
                                arrayList.add("Product Price :" + rec.getProductprice() + "\t" + "(per piece)");
                                arrayList.add("Product Quantity :" + rec.getProductquantity());
                                arrayAdapter.notifyDataSetChanged();
                                lv.setAdapter(arrayAdapter);
                                lv.setVisibility(View.VISIBLE);

                            }

                        }
                    } else {
                        e1.setError("INVALID PRODUCT ID");
                        lv.setVisibility(View.INVISIBLE);
                    }

                }
            }
            });
    }
}
