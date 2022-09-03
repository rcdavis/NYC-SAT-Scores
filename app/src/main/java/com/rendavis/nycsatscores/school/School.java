package com.rendavis.nycsatscores.school;

import java.net.MalformedURLException;
import java.net.URL;

public class School {
    private String id;
    private String name;
    private String overview;
    private String phoneNumber;
    private URL websiteUrl;
    private Address address;

    public School() {}

    public School(
        String id, String name, String overview,
        String phoneNumber, String websiteUrl
    ) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.phoneNumber = phoneNumber;
        try {
            this.websiteUrl = new URL(websiteUrl);
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public URL getWebsiteUrl() {
        return websiteUrl;
    }

    public Address getAddress() {
        return address;
    }

    public static School from(final SchoolDTO dto) {
        final School school = new School();

        school.id = dto.id;
        school.name = dto.name;
        school.overview = dto.overview;
        school.phoneNumber = dto.phoneNumber;
        try {
            school.websiteUrl = new URL(dto.websiteUrl);
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
        school.address = new Address(dto.primaryAddressLine1, dto.city, dto.zip, dto.stateCode);

        return school;
    }
}
