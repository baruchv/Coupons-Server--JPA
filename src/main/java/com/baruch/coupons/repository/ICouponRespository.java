package com.baruch.coupons.repository;

import java.sql.Date;
import java.util.List;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.baruch.coupons.dto.dataInterfaces.ICouponDataObject;
import com.baruch.coupons.dto.coupon.CouponAmountAndTime;
import com.baruch.coupons.entities.Company;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.enums.Categories;

@Repository
public interface ICouponRespository extends CrudRepository<Coupon, Long> {

	@Modifying
	@Query("update Coupon c set c.amount = ?1 where c.id = ?2")
	public void updateCouponAmount(int amount, long id);
	
	@Modifying
	@Query("update Coupon c set c.price = ?1 where c.id = ?2")
	public void updateCouponPrice(float price, long id);

	@Modifying
	@Query("update Coupon c set c.image = ?1 where c.id = ?2")
	public void updateCouponImage(String image, long id);

	@Modifying
	@Query("update Coupon c set c.amount = (c.amount - ?1) where c.id = ?2")
	public void decreaseFromCouponAmount(int amount, long couponID);

	@Query("select new com.baruch.coupons.datapresentation.coupon.CouponBasicData(c.title, c.company.name, c.endDate, c.price, c.id) from Coupon c")
	public List<ICouponDataObject> getAllCoupons();

	@Query("select new com.baruch.coupons.datapresentation.coupon.CouponBasicData(c.title, c.company.name, c.endDate, c.price, c.id) from Coupon c where c.startDate <= ?1 and c.endDate > ?1")
	public List<ICouponDataObject> getAllCouponsForCustomer(Date now);

	@Query("select new com.baruch.coupons.datapresentation.coupon.CouponFullDataForAdmin(c.amount, c.id, c.company.id, c.price, c.title, c.description, c.image, c.company.name, c.category, c.startDate, c.endDate) from Coupon c where c.id = ?1")
	public ICouponDataObject getCouponForAdmin(long couponID);
	
	@Query("select new com.baruch.coupons.datapresentation.coupon.CouponFullDataDefault(c.amount, c.id, c.price, c.title, c.description, c.image, c.company.name, c.category, c.startDate, c.endDate) from Coupon c where c.id = ?1")
	public ICouponDataObject getCouponDefault(long couponID);

	@Query("select new com.baruch.coupons.datapresentation.coupon.CouponBasicData(c.title, c.company.name, c.endDate, c.price, c.id) from Coupon c where c in (select p.coupon from Purchase p where p.user.id = ?1) and c.price <= ?2")
	public List<ICouponDataObject> getPurchasedCouponsByMaxPrice(long userID, float price);
	
	@Query("select new com.baruch.coupons.datapresentation.coupon.CouponBasicData(c.title, c.company.name, c.endDate, c.price, c.id) from Coupon c where c.company.id = ?1")
	public List<ICouponDataObject> getCouponsByCompany(long companyID);
	
	@Query("select new com.baruch.coupons.datapresentation.coupon.CouponBasicData(c.title, c.company.name, c.endDate, c.price, c.id) from Coupon c where c.category= ?1")
	public List<ICouponDataObject> getCouponsByCategory(Categories category);
	
	@Query("select new com.baruch.coupons.dto.CouponAmountAndTime(c.amount, c.startDate, c.endDate) from Coupon c where c.id = ?1")
	public CouponAmountAndTime getCouponAmountAndTime(long couponID);
	
	@Transactional
	@Modifying
	@Query("delete Coupon c where c.endDate <= ?1")
	public void deleteExpiredCoupons(Date now);

	public boolean existsByCompanyAndId(Company company, long couponID);
	
	public boolean existsByCompanyAndTitle(Company company, String title);
	
}
