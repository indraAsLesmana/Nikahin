package com.tutor93.core.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by indraaguslesmana on 6/8/17.
 */

public class History implements Parcelable {

    public List<Invitation> invitations = null;

    public Status status;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.invitations);
        dest.writeParcelable(this.status, flags);
    }

    public History() {
    }

    protected History(Parcel in) {
        this.invitations = in.createTypedArrayList(Invitation.CREATOR);
        this.status = in.readParcelable(Status.class.getClassLoader());
    }

    public static final Parcelable.Creator<History> CREATOR = new Parcelable.Creator<History>() {
        @Override
        public History createFromParcel(Parcel source) {
            return new History(source);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };
}
