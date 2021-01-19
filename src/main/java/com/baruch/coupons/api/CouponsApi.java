package com.baruch.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baruch.coupons.dataInterfaces.ICouponDataObject;
import com.baruch.coupons.dto.CouponDto;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.enums.Category;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.logic.CouponsController;

@RestController
@RequestMapping("/coupons")
public class CouponsApi {
	
	@Autowired
	private CouponsController controller;
	
	@PostMapping
	public long createCoupon(@RequestBody CouponDto couponDto, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		return controller.createCoupon(couponDto, userDetails);
	}
	
	@PutMapping
	public void updateCoupon(@RequestBody CouponDto couponDto, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		controller.updateCoupon(couponDto, userDetails);;
	}
	
	@GetMapping("/{couponID}")
	public ICouponDataObject getCoupon(@PathVariable("couponID") long couponID, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		return controller.getCoupon(couponID,userDetails);
	}
	
	@GetMapping
	public List<ICouponDataObject> getAllCoupons() throws ApplicationException{
		return controller.getAllCoupons();
	}
	
	@GetMapping("/byCompany")
	public List<ICouponDataObject> getCouponsByCompany(@RequestParam("companyID") long companyID) throws ApplicationException{
		return controller.getCouponsByCompany(companyID);
	}
	
	@GetMapping("/byCategory")
	public List<ICouponDataObject> getCouponsByCategory(@RequestParam("category") Category category) throws ApplicationException{
		return controller.getCouponsByCategory(category);
	}
	
	@GetMapping("/byMaxPrice")
	public List<ICouponDataObject> getCouponsByMaxPrice(@RequestParam("maxPrice") float maxPrice, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		return controller.getCouponsByMaxPrice(maxPrice, userDetails);
	}
	
	@DeleteMapping("/{couponID}")
	public void deleteCoupon(@PathVariable("couponID") long couponID) throws ApplicationException{
		controller.deleteCoupon(couponID);
	}
	
	@PostMapping("/favorates/{couponID}")
	public void markAsFavorate(@PathVariable("couponID") long couponID, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		controller.markAsFavorate(couponID, userDetails);
	}
	
	@GetMapping("/favorates")
	public List<ICouponDataObject> getAllFavorates(@RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		return controller.getAllFavorates(userDetails);
	}
	
	@DeleteMapping("/favorates/{couponID}")
	public void deleteFromFavorates(@PathVariable("couponID") long couponID, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		controller.deleteFromFavorates(couponID, userDetails);
	}
}
