package com.kioskbuddy.common.util;


import java.util.regex.Pattern;

public class PhoneNumberValidator {

    // 하이픈 포함된 형식 (010-1234-5678, 02-123-4567)
    private static final String PHONE_PATTERN_HYPHEN = "^(01[0-9]-\\d{3,4}-\\d{4})|(\\d{2,3}-\\d{3,4}-\\d{4})$";

    // 하이픈 없는 형식 (01012345678)
    private static final String PHONE_PATTERN_NO_HYPHEN = "^01[0-9]\\d{7,8}$";

    private static final Pattern PATTERN_HYPHEN = Pattern.compile(PHONE_PATTERN_HYPHEN);
    private static final Pattern PATTERN_NO_HYPHEN = Pattern.compile(PHONE_PATTERN_NO_HYPHEN);

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return false;
        }

        return PATTERN_HYPHEN.matcher(phoneNumber).matches() || PATTERN_NO_HYPHEN.matcher(phoneNumber).matches();
    }
}
