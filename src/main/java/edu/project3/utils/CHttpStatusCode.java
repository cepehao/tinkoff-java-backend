package edu.project3.utils;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("MagicNumber")
public final class CHttpStatusCode {
    private static final Map<Integer, String> STATUS_CODES_MAP = new HashMap<>();

    private CHttpStatusCode() {

    }

    static {
        STATUS_CODES_MAP.put(100, "Continue");
        STATUS_CODES_MAP.put(101, "Switching Protocols");
        STATUS_CODES_MAP.put(200, "OK");
        STATUS_CODES_MAP.put(201, "Created");
        STATUS_CODES_MAP.put(202, "Accepted");
        STATUS_CODES_MAP.put(204, "No Content");
        STATUS_CODES_MAP.put(206, "Partial Content");
        STATUS_CODES_MAP.put(300, "Multiple Choices");
        STATUS_CODES_MAP.put(301, "Moved Permanently");
        STATUS_CODES_MAP.put(302, "Found");
        STATUS_CODES_MAP.put(304, "Not Modified");
        STATUS_CODES_MAP.put(307, "Temporary Redirect");
        STATUS_CODES_MAP.put(400, "Bad Request");
        STATUS_CODES_MAP.put(401, "Unauthorized");
        STATUS_CODES_MAP.put(403, "Forbidden");
        STATUS_CODES_MAP.put(404, "Not Found");
        STATUS_CODES_MAP.put(405, "Method Not Allowed");
        STATUS_CODES_MAP.put(408, "Request Timeout");
        STATUS_CODES_MAP.put(500, "Internal Server Error");
        STATUS_CODES_MAP.put(501, "Not Implemented");
        STATUS_CODES_MAP.put(502, "Bad Gateway");
        STATUS_CODES_MAP.put(503, "Service Unavailable");
        STATUS_CODES_MAP.put(504, "Gateway Timeout");
    }

    public static String getStatusCodeName(int code) {
        return STATUS_CODES_MAP.getOrDefault(code, "undefined code");
    }
}
