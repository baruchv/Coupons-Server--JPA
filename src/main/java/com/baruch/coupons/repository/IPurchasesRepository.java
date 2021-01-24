package com.baruch.coupons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baruch.coupons.dataInterfaces.IPurchaseDataObject;
import com.baruch.coupons.entities.Purchase;

@Repository
public interface IPurchasesRepository extends CrudRepository<Purchase, Long> {
	
	//This method serves Customer users only.
	
	@Query("select new com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForCustomer" + 
	"(p.coupon.amount, p.timeStamp, p.coupon.price, p.coupon.title, p.coupon.company.name) from Purchase p where p.user.id = ?1")
	public List<IPurchaseDataObject> getAllPurchasesForCustomer(long userID);
	
	//This method serves Company users only.

	@Query("select new com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForCompanyUser"+
	"(p.coupon.amount, p.timeStamp, p.coupon.price, p.coupon.title) from Purchase p where p.coupon.company.id = ?1")
	public List<IPurchaseDataObject> getAllPurchasesForCompany(long companyId);
	
	//This methods serve Admin users only.

	@Query("select new com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForAdmin"+
	"(p.id, p.coupon.amount, p.coupon.price, p.timeStamp, p.coupon.title, p.coupon.company.name, p.user.userName) from Purchase p where p.coupon.company.id = ?1")
	public List<IPurchaseDataObject> getPurchasesByCopmany(long companyID);

	@Query("select new com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForAdmin"+
	"(p.id, p.coupon.amount, p.coupon.price, p.timeStamp, p.coupon.title, p.coupon.company.name, p.user.userName) from Purchase p where p.id = ?1")
	public IPurchaseDataObject getPurchase(long purchaseID);

	@Query("select new com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForAdmin"+ 
	"(p.id, p.coupon.amount, p.coupon.price, p.timeStamp, p.coupon.title, p.coupon.company.name, p.user.userName) from Purchase p")
	public List<IPurchaseDataObject> getAllPurchases();

	@Query("select new com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForAdmin"+ 
	"(p.id, p.coupon.amount, p.coupon.price, p.timeStamp, p.coupon.title, p.coupon.company.name, p.user.userName) from Purchase p where p.user.id = ?1")
	public List<IPurchaseDataObject> getPurchasesByUserID(long userID);
}
