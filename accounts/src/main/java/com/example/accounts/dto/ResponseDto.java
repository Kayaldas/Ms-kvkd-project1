package com.example.accounts.dto;

import lombok.*;

@Data
@Getter
@Setter

@NoArgsConstructor
public class ResponseDto {

    private String statusCode;

    private String statusMsg;

    public ResponseDto(String statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    //based on the statuscode and statusmsg,the client can know the details

}
