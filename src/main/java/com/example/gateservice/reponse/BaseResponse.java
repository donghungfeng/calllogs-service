package com.example.gateservice.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private int statusCode;
    private String status;
    private Object result;

    public BaseResponse success(Object result){
        return new BaseResponse(200,"OK",result);
    }

    public BaseResponse fail(Object result){
        return new BaseResponse(500,"FAIL",result);
    }
}
