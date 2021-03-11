package com.baruch.coupons.dto.company.datapresentation;

import com.baruch.coupons.dto.dataInterfaces.ICompanyDataObject;

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
