package com.rendavis.nycsatscores.school;

import com.google.gson.annotations.SerializedName;

public class School {
    @SerializedName("dbn")
    private final String id;

    @SerializedName("school_name")
    private final String name;

    @SerializedName("overview_paragraph")
    private final String overview;

    @SerializedName("location")
    private final String address;

    @SerializedName("phone_number")
    private final String phoneNumber;

    @SerializedName("website")
    private final String websiteUrl;

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
