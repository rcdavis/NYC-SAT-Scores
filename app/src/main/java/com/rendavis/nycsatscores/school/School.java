package com.rendavis.nycsatscores.school;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
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
    private SATScores satScores;

    private School() {}
    public School(
        String id, String name, String overview,
        String phoneNumber, String websiteUrl,
        Address address, SATScores satScores
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
            this.websiteUrl = new URL("https", websiteUrl, "");
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }

        this.address = address;
        this.satScores = satScores;
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

    public URL getWebsiteUrl() {
        return websiteUrl;
    }

    public Address getAddress() {
        return address;
    }

    public SATScores getSatScores() {
        return satScores;
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
            school.websiteUrl = new URL("https", dto.websiteUrl, "");
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }

        school.address = new Address(dto.primaryAddressLine1, dto.city, dto.zip, dto.stateCode);

        return school;
    }

    public static School from(final SchoolDTO schoolDTO, final SchoolSATDTO satDTO) {
        final School school = from(schoolDTO);
        school.satScores = SATScores.from(satDTO);
        return school;
    }
}
