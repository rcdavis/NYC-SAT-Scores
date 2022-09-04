package com.rendavis.nycsatscores.school;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import java.net.MalformedURLException;
import java.net.URL;

public class School {
    private String id;
    private String name;
    private String overview;
    private PhoneNumber phoneNumber;
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

        try {
            final PhoneNumberUtil pnUtil = PhoneNumberUtil.getInstance();
            this.phoneNumber = pnUtil.parse(phoneNumber, "US");
        } catch (final NumberParseException e) {
            e.printStackTrace();
        }

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

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhoneNumberString() {
        return PhoneNumberUtil.getInstance().format(phoneNumber, PhoneNumberFormat.NATIONAL);
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

        try {
            final PhoneNumberUtil pnUtil = PhoneNumberUtil.getInstance();
            school.phoneNumber = pnUtil.parse(dto.phoneNumber, "US");
        } catch (final NumberParseException e) {
            e.printStackTrace();
        }

        try {
            school.websiteUrl = new URL(dto.websiteUrl);
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }

        school.address = new Address(dto.primaryAddressLine1, dto.city, dto.zip, dto.stateCode);

        return school;
    }
}
