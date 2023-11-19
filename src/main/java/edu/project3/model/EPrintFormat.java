package edu.project3.model;

public enum EPrintFormat {
    MD,
    ADOC;

    public static EPrintFormat buildFormatType(String stringFormatType) {
        return switch (stringFormatType) {
            case "md" -> MD;
            case "adoc" -> ADOC;
            default -> null;
        };
    }
}
