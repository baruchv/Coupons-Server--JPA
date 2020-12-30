package com.baruch.coupons.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baruch.coupons.dataInterfaces.IPurchaseDataObject;
import com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForAdmin;
import com.baruch.coupons.dto.PurchaseDto;
import com.baruch.coupons.dto.UserLoginData;
import com.baruch.coupons.exceptions.ApplicationException;
import com.baruch.coupons.logic.PurchasesController;

@RestController
@RequestMapping("/purchases")
public class PurchasesApi {
	
	@Autowired
	private PurchasesController con;
	
	@PostMapping
<<<<<<< HEAD
	public long addPurchase(PurchaseDto purchaseDto, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
=======
	public long addPurchase(@RequestBody PurchaseDto purchaseDto, @RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
>>>>>>> 4ae497bc8b8df3f5c905e9e53d08c8227b914fa8
		return con.addPurchase(purchaseDto, userDetails);
	}
	
	@GetMapping
<<<<<<< HEAD
	public List<IPurchaseDataObject> getAllPurchases(@RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
=======
	public List<PurchaseDto> getAllPurchases(@RequestAttribute("UserLoginData") UserLoginData userDetails) throws ApplicationException{
>>>>>>> 4ae497bc8b8df3f5c905e9e53d08c8227b914fa8
		return con.getAllPurchases(userDetails);
	}
	
	@GetMapping("/{purchaseID}")
	public IPurchaseDataObject getPurchase(@PathVariable("purchaseID") long id) throws ApplicationException{
		return con.getPurchase(id);
	}
	
	@GetMapping("/byCompanyID")
	public List<IPurchaseDataObject> getPurchaseByCompanyID(@RequestParam("companyID") long companyID) throws ApplicationException{
		return con.getPurchasesByCompanyID(companyID);
	}
	
	@GetMapping("/byUserID")
	public List<IPurchaseDataObject> getPurchaseByUserID(@RequestParam("userID") long userID) throws ApplicationException{
		return con.getPurchasesByUserID(userID);
	}
	
	@DeleteMapping("/{purchaseID}")
	public void deletePurchase(@PathVariable("purchaseID") long id) throws ApplicationException{
		con.deletePurchase(id);
	}

}
