package com.cisco.phonexercise.utility;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PhoneModelEnum {

    @JsonProperty("IPHONE")
    IPHONE,

    @JsonProperty("ANDROID")
    ANDROID_PHONE,

    @JsonProperty("DESK_PHONE")
    DESK_PHONE,

    @JsonProperty("SOFT_PHONE")
    SOFT_PHONE
}
