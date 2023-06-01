package com.tolgaziftci.casestudy.responses;

public class ResponseBuilder {
    private ResponseBuilder() {
    }

    public static <T> RestResponse send(Integer status, String message, T payload){
        return RestResponse.builder()
                .status(status)
                .message(message)
                .payload(payload)
                .build();
    }

    public static <T> RestResponse send(Integer status, T payload){
        return RestResponse.builder()
                .status(status)
                .payload(payload)
                .build();
    }
}
