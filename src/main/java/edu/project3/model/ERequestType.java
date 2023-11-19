package edu.project3.model;

public enum ERequestType {
    DELETE,
    GET,
    HEAD,
    OPTIONS,
    PATCH,
    POST,
    PUT,
    TRACE;

    public static ERequestType buildRequestType(String stringRequestType) {
        return switch (stringRequestType) {
            case "DELETE" -> DELETE;
            case "GET"     -> GET;
            case "HEAD"    -> HEAD;
            case "OPTIONS" -> OPTIONS;
            case "PATCH"   -> PATCH;
            case "POST"    -> POST;
            case "PUT"     -> PUT;
            case "TRACE"   -> TRACE;
            default -> throw new IllegalArgumentException("Некорректный тип запроса");
        };
    }
}
