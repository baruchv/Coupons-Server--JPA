package com.baruch.coupons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baruch.coupons.dataInterfaces.IPurchaseDataObject;
import com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForAdmin;
import com.baruch.coupons.dto.PurchaseDto;
import com.baruch.coupons.entities.Purchase;

@Repository
public interface IPurchasesRepository extends CrudRepository<Purchase, Long> {
	
	@Query("select new com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForCustomer(p) from Purchase p where p.user.id = ?1")
	public List<IPurchaseDataObject> getAllPurchasesForCustomer(long userID);
	
	@Query("select new com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForCompanyUser(p) from Purchase p where p.coupon.company.id = ?1")
	public List<IPurchaseDataObject> getAllPurchasesForCompany(long companyId);
	
	@Query("select new com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForAdmin(p) from Purchase p")
	public List<IPurchaseDataObject> getAllPurchases();
	
	@Query("select new com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForAdmin(p) from Purchase p where p.user.id = ?1")
	public List<IPurchaseDataObject> getPurchasesByUserID(long userID);
	
	@Query("select new com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForAdmin(p) from Purchase p where p.coupon.company.id = ?1")
	public List<IPurchaseDataObject> getPurchasesByCopmany(long companyID);

	@Query("select new com.baruch.coupons.dataObjectsForPresentation.PurchaseDataForAdmin(p) from Purchase p where p.id = ?1")
	public IPurchaseDataObject getPurchase(long id);
}
