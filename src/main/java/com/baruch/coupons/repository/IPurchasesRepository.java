package com.baruch.coupons.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baruch.coupons.dto.PurchaseDto;
import com.baruch.coupons.entities.Purchase;

@Repository
public interface IPurchasesRepository extends CrudRepository<Purchase, Long> {
	
	@Query("select new com.baruch.coupons.dto.PurchaseDto(p) from Purchase p where p.user.id = ?1")
	public List<PurchaseDto> getAllPurchasesByCustomer(long userID);
	
	@Query("select new com.baruch.coupons.dto.PurchaseDto(p) from Purchase p where p.coupon.company.id = ?1")
	public List<PurchaseDto> getAllPurchasesByCompany(long companyId);
	
	@Query("select new com.baruch.coupons.dto.PurchaseDto(p) from Purchase p")
	public List<PurchaseDto> getAllPurchases();
	
	@Query("select new com.baruch.coupons.dto.PurchaseDto(p) from Purchase p where p.user.id = ?1")
	public List<PurchaseDto> getPurchasesByUserID(long userID);

	@Query("select new com.baruch.coupons.dto.PurchaseDto(p) from Purchase p where p.id = ?1")
	public PurchaseDto getPurchase(long id);
}
