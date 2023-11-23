package edu.project3.model;

public enum EPrintFormat {
    MD,
    ADOC;

    public static EPrintFormat buildFormatType(String stringFormatType) {
        if (stringFormatType == null) {
            return MD;
        }

        return switch (stringFormatType) {
            case "md" -> MD;
            case "adoc" -> ADOC;
            default -> MD;
        };
    }
}
