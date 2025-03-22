package com.kioskbuddy.common.util;


public class PhoneNumberValidator {

    // 하이픈 포함된 형식 (010-1234-5678, 02-123-4567)
    private static final String PHONE_PATTERN_HYPHEN = "^(01[0-9]-\\d{3,4}-\\d{4})|(\\d{2,3}-\\d{3,4}-\\d{4})$";

    // 하이픈 없는 형식 (01012345678)
    private static final String PHONE_PATTERN_NO_HYPHEN = "^01[0-9]\\d{7,8}$";

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return false;
        }

        return PHONE_PATTERN_HYPHEN.matches(phoneNumber) || PHONE_PATTERN_NO_HYPHEN.matches(phoneNumber);
    }
}
