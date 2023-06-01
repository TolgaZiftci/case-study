package com.tolgaziftci.casestudy.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse<T> {
    private Integer status;
    private String message;
    private T payload;
}
