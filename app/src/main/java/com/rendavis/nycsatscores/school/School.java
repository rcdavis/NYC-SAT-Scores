package com.rendavis.nycsatscores.school;

import com.google.gson.annotations.SerializedName;

public class School {
    @SerializedName("dbn")
    final private String id;

    @SerializedName("school_name")
    final private String name;

    @SerializedName("overview_paragraph")
    final private String overview;

    @SerializedName("location")
    final private String address;

    @SerializedName("phone_number")
    final private String phoneNumber;

    @SerializedName("website")
    final private String websiteUrl;

    public School(
        String id, String name, String overview, String address,
        String phoneNumber, String websiteUrl
    ) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.websiteUrl = websiteUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }
}
