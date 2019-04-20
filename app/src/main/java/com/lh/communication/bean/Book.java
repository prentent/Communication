package com.lh.communication.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {

    public Book() {

    }

    private String name;
    private float rice;
    private String address;

    protected Book(Parcel in) {
        name = in.readString();
        rice = in.readFloat();
        address = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRice() {
        return rice;
    }

    public void setRice(float rice) {
        this.rice = rice;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(rice);
        dest.writeString(address);
    }
}
