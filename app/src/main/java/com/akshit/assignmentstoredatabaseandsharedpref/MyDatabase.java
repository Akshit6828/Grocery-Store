package com.akshit.assignmentstoredatabaseandsharedpref;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper  {
    //---VERSION-------------------------------------------------------------
    public static final int VERSION=1;
    //---DATABASE NAME-------------------------------------------------------
    public static final String DB_NAME = "grocery_db";

    //Table Details
    public static final String DB_TABLE1 = "login_record";
    public static final String DB_TABLE2 = "products_record";

    //---COLUMNS-------------------------------------------------------------
        //--TABLE 1---****************
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PHONENUMBER ="phonenumber";
       //---TABLE 2---****************
       public static final String PRODUCTID = "id";
       public static final String PRODUCTNAME = "productname";
       public static final String PRODUCTQUANTITY = "productquantity";
       public static final String PRODUCTPRICE = "productprice";

    //********************-----onCreate() and onUpgrade() are the two methods  overrides from SQLiteOpenHelper class.**********************
    //-----------onCreate() is used to execute a query while onUpgrade() is used to check when a new Version Upgrades what changes to does with previous Data in DataBase ----
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE "+ DB_TABLE1 + "(" + USERNAME + " TEXT PRIMARY KEY," + PASSWORD + " TEXT," + NAME + " TEXT," + EMAIL +" TEXT," + PHONENUMBER +" TEXT);";
        String query1="CREATE TABLE "+ DB_TABLE2 + "(" + PRODUCTID + " NUMBER PRIMARY KEY," + PRODUCTNAME + " TEXT," + PRODUCTQUANTITY + " TEXT," + PRODUCTPRICE + " TEXT);";
        db.execSQL(query);
        db.execSQL(query1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE1);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE2);
        onCreate(db);
    }

    //Creating a Default Constructor of SQLiteOpenHelper Class for Creating Database as SQLiteOpenHelper doesn't have a default constructor.
    public MyDatabase(Context context)
    {
        super(context,DB_NAME,null,VERSION);
    }
    //############################---INSERT PRODUCTS RECORD---############################
    public long insertProduct(ProductRec rec)
    {
        SQLiteDatabase db = getWritableDatabase();//-----For writing the data....
        //Store data in object------------
        ContentValues values = new ContentValues();
        values.put(PRODUCTID, rec.getProductid());
        values.put(PRODUCTNAME, rec.getProductname());
        values.put(PRODUCTQUANTITY, rec.getProductquantity());
        values.put(PRODUCTPRICE,rec.getProductprice());
        long result = db.insert(DB_TABLE2, null, values);
        return result;
    }

    //############################---INSERT CUSTOMER RECORD---############################
    public long insertCustomer(CustomerRec rec)
    {
        SQLiteDatabase db = getWritableDatabase();//-----For writing the data....
        //Store data in object------------
        ContentValues values = new ContentValues();
        values.put(USERNAME, rec.getUsername());
        values.put(PASSWORD, rec.getPassword());
        values.put(NAME, rec.getName());
        values.put(EMAIL,rec.getEmail());
        values.put(PHONENUMBER,rec.getPhonenumber());
        long result = db.insert(DB_TABLE1, null, values);
        return result;
    }

    //############################--GET PRODUCT RECORD--#################################3
    public List<ProductRec> getProductAllRecord() {
        SQLiteDatabase db = getReadableDatabase();

        //Creating a container-- So we make Dynamic Array List
        List<ProductRec> list = new ArrayList<ProductRec>();
        String query = "SELECT * FROM " + DB_TABLE2;
        Cursor cur = db.rawQuery(query, null);
        if (cur.moveToFirst()) {
            do {
                ProductRec record = new ProductRec();
                record.setProductid(cur.getString(0));
                record.setProductname(cur.getString(1));
                record.setProductquantity(cur.getString(2));
                record.setProductprice(cur.getString(3));
                //ADDING DATA TO CONTAINER------------------
                list.add(record);

            }
            while (cur.moveToNext());

        }
        return list;
    }


    //############################--GET CUSTOMER RECORD--#################################3
    public List<CustomerRec> getCustomerAllRecord() {
        SQLiteDatabase db = getReadableDatabase();

        //Creating a container-- So we make Dynamic Array List
        List<CustomerRec> list = new ArrayList<CustomerRec>();
        String query = "SELECT * FROM " + DB_TABLE1;
        Cursor cur = db.rawQuery(query, null);
        if (cur.moveToFirst()) {
            do {
                CustomerRec record = new CustomerRec();
                record.setUsername(cur.getString(0));
                record.setPassword(cur.getString(1));
                record.setName(cur.getString(2));
                record.setEmail(cur.getString(3));
                record.setPhonenumber(cur.getString(4));
                //ADDING DATA TO CONTAINER------------------
                list.add(record);

            }
            while (cur.moveToNext());

        }
        return list;
    }
    //######################################---GET SINGLE CUSTOMER RECORD--#####################################
    public CustomerRec getSingleCustomerRecord(String username) {
        SQLiteDatabase db = getReadableDatabase();//-----For reading the data....
        try {
            Cursor cur = db.query(DB_TABLE1, new String[]{USERNAME, PASSWORD, NAME, EMAIL, PHONENUMBER}, USERNAME + "=?", new String[]{String.valueOf(username)}, null, null, null);
            if (cur != null) {
                cur.moveToFirst();
                return new CustomerRec(cur.getString(0), cur.getString(1), cur.getString(2), cur.getString(3), cur.getString(4));// Calls parameterised Const of Customer Record
            }
            else
            {
                return new CustomerRec(null,null,null,null,null);
            }
        }
        catch (Exception e)
        {
            return new CustomerRec(null,null,null,null,null);
        }

    }

    //######################################---GET SINGLE PRODUCT RECORD--#####################################
    public ProductRec getSingleProductRecord(String username) {
        SQLiteDatabase db = getReadableDatabase();//-----For reading the data....
        try {
            Cursor cur = db.query(DB_TABLE2, new String[]{PRODUCTID, PRODUCTNAME, PRODUCTPRICE, PRODUCTQUANTITY}, PRODUCTID + "=?", new String[]{String.valueOf(username)}, null, null, null);
            if (cur != null) {
                cur.moveToFirst();
                return new ProductRec(cur.getString(0), cur.getString(1), cur.getString(2), cur.getString(3));// Calls parameterised Const of Customer Record
            }
            else
            {
                return new ProductRec(null,null,null,null);
            }
        }
        catch (Exception e)
        {
            return new ProductRec(null,null,null,null);
        }

    }


}
