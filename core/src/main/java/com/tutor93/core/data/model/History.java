package com.tutor93.core.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by indraaguslesmana on 6/8/17.
 */

public class History {


    public List<Invitation> invitations = null;

    public Status status;

    public class Invitation {

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
        public Locations locations;

    }

    private class Locations {
        public String latitude;
        public String longitude;
        public String name;
        public String address;
    }

    private class Status {
        public Integer code;
        public String message;
    }
}
