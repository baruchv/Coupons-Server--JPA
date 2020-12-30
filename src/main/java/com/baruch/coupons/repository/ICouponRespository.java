package com.baruch.coupons.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baruch.coupons.dataInterfaces.ICouponDataObject;
import com.baruch.coupons.dto.CouponDto;
import com.baruch.coupons.entities.Company;
import com.baruch.coupons.entities.Coupon;
import com.baruch.coupons.enums.Category;

@Repository
public interface ICouponRespository extends CrudRepository<Coupon, Long> {
	
	@Modifying
	@Query("update Coupon c set c.amount = ?1, c.price = ?2, c.image = ?3 where c.id = ?4")
	public void updateCoupon(int amount, float price, String image, long id);
	
	@Query("select new com.baruch.coupons.dataObjectsForPresentation.CouponDataBasic(c) from Coupon c")
	public List<ICouponDataObject> getAllCoupons();

	@Query("select new com.baruch.coupons.dataObjectsForPresentation.CouponData(c) from Coupon c where c.id = ?1")
	public ICouponDataObject getCoupon(long id);

	@Query("select new com.baruch.coupons.dataObjectsForPresentation.CouponDataBasic(c) from Coupon c where c in (select p.coupon from Purchase p where p.user.id = ?1) and c.price <= ?2")
	public List<ICouponDataObject> getPurchasedCouponsByMaxPrice(long userID, float price);
	
	@Query("select new com.baruch.coupons.dataObjectsForPresentation.CouponDataBasic(c) from Coupon c where c.company.id = ?1")
	public List<ICouponDataObject> getCouponsByCompany(long id);
	
	@Query("select new com.baruch.coupons.dataObjectsForPresentation.CouponDataBasic(c) from Coupon c where c.category= ?1")
	public List<ICouponDataObject> getCouponsByCategory(Category category);
	
	@Query("select c.amount from Coupon c where c.id = ?1")
	public int getCouponsAmount(long id);
	
	@Query("delete Coupon c where c.endDate <= ?1")
	public void deleteExpiredCoupons(Date now);
	
	public boolean existsByCompanyAndTitle(Company company, String title);
	
	
	
	
	
}
