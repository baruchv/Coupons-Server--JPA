package com.baruch.coupons.dto.coupon;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CouponAmountAndTime {
    
    private int amount;

    private Date startDate, endDate;
    
}
