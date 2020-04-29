package com.arprast.sosmed.type;

public enum AccountType {

    TWITTER("twitter"),
    FACEBOOK("facebook"),
    YOUTUBE("youtube"),
    INSTAGRAM("instagram"),
    TIKTOK("tiktok"),
    NETZME("netzme"),
    UNKNOWN("unknown");

    public String stringValue;

    AccountType(String stringValue) {
        this.stringValue = stringValue;
    }

    public static AccountType valueOfString(String stringValue) {

        if (stringValue == null) {
            return UNKNOWN;
        }

        switch (stringValue.toLowerCase()) {
            case "twitter":
                return TWITTER;
            case "facebook":
                return FACEBOOK;
            case "youtube":
                return YOUTUBE;
            case "instagram":
                return INSTAGRAM;
            case "tiktok":
                return TIKTOK;
            case "netzme":
                return NETZME;
            default:
                return UNKNOWN;
        }
    }
}
