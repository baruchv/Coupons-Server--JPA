package com.baruch.coupons.timertasks;

import java.sql.Date;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

import com.baruch.coupons.repository.ICouponRespository;

@Component
@NoArgsConstructor
public class CouponsValidator extends TimerTask{
	
	@Autowired
	private ICouponRespository repository;

	public void run() {
		try{
			Date now = new Date(System.currentTimeMillis());
			repository.deleteExpiredCoupons(now);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
