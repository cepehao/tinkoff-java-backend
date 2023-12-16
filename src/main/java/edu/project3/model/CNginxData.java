package edu.project3.model;

import java.time.LocalDateTime;

public record CNginxData(
    String remoteAddr,
    LocalDateTime timeLocal,
    ERequestType requestType,
    String uri,
    String httpVersion,
    int status,
    int bodyBytesSent,
    String httpUserAgent
) {

}
