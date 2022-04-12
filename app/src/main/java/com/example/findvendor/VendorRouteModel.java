package com.example.findvendor;

public class VendorRouteModel {
    String location;
    String time;

    public VendorRouteModel() {
    }

    public VendorRouteModel(String location, String time) {
        this.location = location;
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
