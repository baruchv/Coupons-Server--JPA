package com.baruch.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baruch.coupons.dto.CouponDto;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.enums.Category;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.logic.CouponsController;

@RestController
@RequestMapping("/coupons")
public class CouponsApi {
	
	@Autowired
	private CouponsController con;
	
	@PostMapping
	public long createCoupon(@RequestBody CouponDto couponDto, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		return con.createCoupon(couponDto, userDetails);
	}
	
	@PutMapping
	public void updateCoupon(@RequestBody CouponDto couponDto, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		con.updateCoupon(couponDto, userDetails);;
	}
	
	@GetMapping("/{couponID}")
	public CouponDto getCoupon(@PathVariable("couponID") long id) throws ApplicationException{
		return con.getCoupon(id);
	}
	
	@GetMapping
	public List<CouponDto> getAllCoupons() throws ApplicationException{
		return con.getAllCoupons();
	}
	
	@GetMapping("/byCompany")
	public List<CouponDto> getCouponsByCompany(@ReqestParam("companyID") long companyID) throws ApplicationException{
		return con.getCouponsByCompany(companyID);
	}
	
	@GetMapping("/byCategory")
	public List<CouponDto> getCouponsByCategory(@RequestParam("category") Category category) throws ApplicationException{
		return con.getCouponsByCategory(category);
	}
	
	@GetMapping("/byMAxPrice")
	public List<CouponDto> getCouponsByMaxPrice(@RequestParam("maxPrice") float maxPrice, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		return con.getCouponsByMaxPrice(maxPrice, userDetails);
	}
	
	@DeleteMapping("/{couponID}")
	public void deleteCoupon(@PathVariable("couponID") long id) throws ApplicationException{
		con.deleteCoupon(id);
	}
}
