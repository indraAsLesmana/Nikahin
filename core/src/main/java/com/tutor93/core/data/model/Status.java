package com.tutor93.core.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by indraaguslesmana on 6/10/17.
 */

public class Status implements Parcelable {

    public Integer code;
    public String message;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.code);
        dest.writeString(this.message);
    }

    public Status() {
    }

    protected Status(Parcel in) {
        this.code = (Integer) in.readValue(Integer.class.getClassLoader());
        this.message = in.readString();
    }

    public static final Parcelable.Creator<Status> CREATOR = new Parcelable.Creator<Status>() {
        @Override
        public Status createFromParcel(Parcel source) {
            return new Status(source);
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };
}
