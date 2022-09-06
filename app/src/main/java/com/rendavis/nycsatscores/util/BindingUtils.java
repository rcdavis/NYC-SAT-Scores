package com.rendavis.nycsatscores.util;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.rendavis.nycsatscores.school.Address;

public class BindingUtils {
    @BindingAdapter("text")
    public static void setText(final TextView textView, final int val) {
        textView.setText(String.valueOf(val));
    }

    @BindingAdapter("text")
    public static void setText(final TextView textView, final Address address) {
        if (address != null)
            textView.setText(address.toString());
    }

    @BindingAdapter("text")
    public static void setText(final TextView textView, final PhoneNumber phoneNumber) {
        if (phoneNumber != null)
            textView.setText(PhoneNumberUtil.getInstance()
                .format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL));
    }
}
