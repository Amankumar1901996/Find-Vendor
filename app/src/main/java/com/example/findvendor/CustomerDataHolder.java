package com.example.findvendor;

public class CustomerDataHolder {
    String username, fullname, cityname, address, email, password;
    int iscustomer;

    public CustomerDataHolder(String username, String fullname, String cityname, String address, String email, String password, int iscustomer) {
        this.username = username;
        this.fullname = fullname;
        this.cityname = cityname;
        this.address = address;
        this.email = email;
        this.password = password;
        this.iscustomer = iscustomer;
    }

    public CustomerDataHolder() {
    }

    public int getIscustomer() {
        return iscustomer;
    }

    public void setIscustomer(int iscustomer) {
        this.iscustomer = iscustomer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}