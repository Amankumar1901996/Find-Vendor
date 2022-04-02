package com.example.findvendor;

public class VendorDataHolder {

   String vendorid, fullname, cityname, address, occupation, email, password;
   int iscustomer;

   public VendorDataHolder() {
   }

   public VendorDataHolder(String vendorid, String fullname, String cityname, String address, String occupation, String email, String password, int iscustomer) {
      this.vendorid = vendorid;
      this.fullname = fullname;
      this.cityname = cityname;
      this.address = address;
      this.occupation = occupation;
      this.email = email;
      this.password = password;
      this.iscustomer = iscustomer;
   }

   public int getIscustomer() {
      return iscustomer;
   }

   public void setIscustomer(int iscustomer) {
      this.iscustomer = iscustomer;
   }

   public String getVendorid() {
      return vendorid;
   }

   public void setVendorid(String vendorid) {
      this.vendorid = vendorid;
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

   public String getOccupation() {
      return occupation;
   }

   public void setOccupation(String occupation) {
      this.occupation = occupation;
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
