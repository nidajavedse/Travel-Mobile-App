package com.example.traveltrunk;

import android.graphics.Bitmap;

public class ModelClass {

    private String userName,firstName, lastName ,dob , city ;
    private Bitmap profileImage;

    public ModelClass(String userName, String firstName, String lastName, String dob, String city, Bitmap profileImage) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.city = city;
        this.profileImage = profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Bitmap profileImage) {
        this.profileImage = profileImage;
    }
}