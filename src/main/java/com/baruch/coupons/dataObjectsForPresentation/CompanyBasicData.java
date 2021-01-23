package com.baruch.coupons.dataObjectsForPresentation;

import com.baruch.coupons.dataInterfaces.ICompanyDataObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CompanyBasicData implements ICompanyDataObject{
    
    private long id;

    private String name;

}
