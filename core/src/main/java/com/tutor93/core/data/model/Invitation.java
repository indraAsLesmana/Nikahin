package com.tutor93.core.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by indraaguslesmana on 6/10/17.
 */

public class Invitation implements Parcelable {

    public Integer id;
    @SerializedName("invitation_title")
    @Expose
    public String invitationTitle;
    @SerializedName("invitation_image")
    @Expose
    public String invitationImage;
    @SerializedName("invitation_date")
    @Expose
    public String invitationDate;
    @SerializedName("image_gallery")
    @Expose
    public List<String> imageGallery = null;
    @SerializedName("locations")
    @Expose
    public Location locations;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.invitationTitle);
        dest.writeString(this.invitationImage);
        dest.writeString(this.invitationDate);
        dest.writeStringList(this.imageGallery);
        dest.writeParcelable(this.locations, flags);
    }

    public Invitation() {
    }

    protected Invitation(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.invitationTitle = in.readString();
        this.invitationImage = in.readString();
        this.invitationDate = in.readString();
        this.imageGallery = in.createStringArrayList();
        this.locations = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Parcelable.Creator<Invitation> CREATOR = new Parcelable.Creator<Invitation>() {
        @Override
        public Invitation createFromParcel(Parcel source) {
            return new Invitation(source);
        }

        @Override
        public Invitation[] newArray(int size) {
            return new Invitation[size];
        }
    };
}
