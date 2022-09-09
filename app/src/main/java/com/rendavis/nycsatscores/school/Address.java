package com.rendavis.nycsatscores.school;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Address {
    private final String primaryAddressLine1;
    private final String city;
    private final int zip;
    private final String stateCode;

    public Address(String primaryAddressLine1, String city, int zip, String stateCode) {
        this.primaryAddressLine1 = primaryAddressLine1;
        this.city = city;
        this.zip = zip;
        this.stateCode = stateCode;
    }

    public String getPrimaryAddressLine1() {
        return primaryAddressLine1;
    }

    public String getCity() {
        return city;
    }

    public int getZip() {
        return zip;
    }

    public String getStateCode() {
        return stateCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        final Address address = (Address) o;
        return zip == address.zip &&
                Objects.equals(primaryAddressLine1, address.primaryAddressLine1) &&
                Objects.equals(city, address.city) && Objects.equals(stateCode, address.stateCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryAddressLine1, city, zip, stateCode);
    }

    @NonNull
    @Override
    public String toString() {
        return new StringBuilder(64)
                .append(primaryAddressLine1).append(",\n")
                .append(city).append(", ")
                .append(stateCode).append(" ")
                .append(zip)
                .toString();
    }
}
