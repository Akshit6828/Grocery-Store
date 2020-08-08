package com.akshit.assignmentstoredatabaseandsharedpref;

public class CustomerRec {

    String username,password,name,email,phonenumber; //TABLE 1 Elements---------


    //############################-----PARAMETERISED CONSTRUCTOR-----############################################

    public CustomerRec(String username, String password, String name, String email, String phonenumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    //############################-----GETTERS AND SETTERS----############################################

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }





    //############-----DEFAULT CONSTRUCTOR------########################
   public CustomerRec()
    {

    }


}
