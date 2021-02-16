package com.baruch.coupons.datapresentation.company;

import com.baruch.coupons.datapresentation.dataInterfaces.ICompanyDataObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CompanyFullData implements ICompanyDataObject {
    
    private long id;

    private String name, address, phoneNumber;
}
