package com.akshit.assignmentstoredatabaseandsharedpref;

public class ProductRec {
    //TABLE-2  Elements---------
    String productid, productname,productquantity,productprice;


//############################-----PARAMETERISED CONSTRUCTOR-----############################################
    public ProductRec(String productid, String productname, String productquantity, String productprice) {
        this.productid = productid;
        this.productname = productname;
        this.productquantity = productquantity;
        this.productprice = productprice;
    }


    //############################-----GETTERS AND SETTERS----############################################

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(String productquantity) {
        this.productquantity = productquantity;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }


    //############-----DEFAULT CONSTRUCTOR------########################
    public  ProductRec()
    {

    }




}
