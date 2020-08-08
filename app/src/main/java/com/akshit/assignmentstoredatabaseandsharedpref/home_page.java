package com.akshit.assignmentstoredatabaseandsharedpref;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class home_page extends AppCompatActivity {
    ImageButton b1,b2,b3,b4;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_home_page);
        pref=getSharedPreferences("login_status", Context.MODE_PRIVATE);
        editor=pref.edit();
       b1=findViewById(R.id.ib1);
       b2=findViewById(R.id.ib2);
       b3=findViewById(R.id.ib3);
       b4=findViewById(R.id.ib4);
       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(home_page.this,Add_Product.class);
               startActivity(i);
           }
       });
       b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               editor.putString("login_key",null);
               editor.commit();
               Intent i = new Intent(home_page.this,Show.class);
               startActivity(i);
           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i= new Intent(home_page.this,MainActivity.class);
        startActivity(i);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
