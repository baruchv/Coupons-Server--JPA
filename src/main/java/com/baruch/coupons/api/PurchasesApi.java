package com.baruch.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baruch.coupons.dataInterfaces.IPurchaseDataObject;
import com.baruch.coupons.dto.PurchaseDto;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.logic.PurchasesController;

@RestController
@RequestMapping("/purchases")
public class PurchasesApi {
	
	@Autowired
	private PurchasesController controller;
	
	@PostMapping
	public long addPurchase(@RequestBody PurchaseDto purchaseDto, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		return controller.addPurchase(purchaseDto, userDetails);
	}
	
	@GetMapping
	public List<IPurchaseDataObject> getAllPurchases(@RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
		return controller.getAllPurchases(userDetails);
	}
	
	@GetMapping("/{purchaseID}")
	public IPurchaseDataObject getPurchase(@PathVariable("purchaseID") long purchaseID) throws ApplicationException{
		return controller.getPurchase(purchaseID);
	}
	
	@GetMapping("/byCompanyID")
	public List<IPurchaseDataObject> getPurchaseByCompanyID(@RequestParam("companyID") long companyID) throws ApplicationException{
		return controller.getPurchasesByCompanyID(companyID);
	}
	
	@GetMapping("/byUserID")
	public List<IPurchaseDataObject> getPurchaseByUserID(@RequestParam("userID") long userID) throws ApplicationException{
		return controller.getPurchasesByUserID(userID);
	}
	
	@DeleteMapping("/{purchaseID}")
	public void deletePurchase(@PathVariable("purchaseID") long purchaseID) throws ApplicationException{
		controller.deletePurchase(purchaseID);
	}

}
