package com.baruch.coupons.datapresentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ErrorDataObject {
    
    private int errorNumber;

    private String errorMessage;
    
    private String errorType;

}
