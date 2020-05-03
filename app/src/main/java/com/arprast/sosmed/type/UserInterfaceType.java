package com.arprast.sosmed.type;

public enum UserInterfaceType {

    SHOW_CREDENTIAL("show credential"),
    UNKNOWN("unknown");

    public String stringValue;

    UserInterfaceType(String stringValue) {
        this.stringValue = stringValue;
    }

    public static UserInterfaceType valueOfString(String stringValue) {

        if (stringValue == null) {
            return UNKNOWN;
        }

        switch (stringValue.toLowerCase()) {
            case "show credential":
                return SHOW_CREDENTIAL;
            default:
                return UNKNOWN;
        }
    }
}
