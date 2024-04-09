package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.Sales;
import com.aimachines.cdk.model.Service;

@Transactional
@Repository
public interface SaleRepo extends CrudRepository<Sales, Long>{

	List<Sales> findByDealNumber(String dealNumber);
	
	List<Sales> findByStoreIdFkAndDealNumber(Long storeId,String dealNumber);
	
	
//	@Query(value="SELECT * FROM yac_sales ys   WHERE ys.store_id_fk = :storeId and ys.sold_date = :soldDate", nativeQuery=true)
//	List<Sales> findByStoreIdFkAndSoldDate(@Param("storeId") Long storeId,@Param("soldDate") String soldDate);
	
	
//	List<Sales> findByStoreIdFkAndSoldDate(Long storeId,String soldDate);
	
	@Query(value="SELECT * FROM yac_sales service  WHERE service.store_id_fk = :storeId and service.sold_date = :closedDate", nativeQuery=true)
	List<Sales> findByStoreIdFkAndSoldDate(@Param("storeId") Long storeId,@Param("closedDate") String closedDate);
}
