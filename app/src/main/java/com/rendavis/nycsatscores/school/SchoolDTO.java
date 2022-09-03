package com.rendavis.nycsatscores.school;

import com.google.gson.annotations.SerializedName;

public class SchoolDTO {
    @SerializedName("dbn")
    public String id;

    @SerializedName("school_name")
    public String name;

    @SerializedName("overview_paragraph")
    public String overview;

    @SerializedName("phone_number")
    public String phoneNumber;

    @SerializedName("website")
    public String websiteUrl;

    @SerializedName("primary_address_line_1")
    public String primaryAddressLine1;

    public String city;

    public int zip;

    @SerializedName("state_code")
    public String stateCode;

    public double latitude;
    public double longitude;
}
