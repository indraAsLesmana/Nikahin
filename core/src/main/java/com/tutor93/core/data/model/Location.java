package com.tutor93.core.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by indraaguslesmana on 6/10/17.
 */

public class Location implements Parcelable {

    public String latitude;
    public String longitude;
    public String name;
    public String address;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeString(this.name);
        dest.writeString(this.address);
    }

    public Location() {
    }

    protected Location(Parcel in) {
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.name = in.readString();
        this.address = in.readString();
    }

    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel source) {
            return new Location(source);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };
}
