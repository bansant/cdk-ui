package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.Service;

@Transactional
@Repository
public interface ServiceRepo extends CrudRepository<Service, Long>{

	List<Service> findByRoNumber(String roNumber);
	

	@Query(value="SELECT * FROM yac_service service  WHERE service.store_id_fk = :storeId and service.close_date = :closedDate", nativeQuery=true)
	List<Service> findByStoreIdFkAndCloseDate(@Param("storeId") Long storeId,@Param("closedDate") String closedDate);
	
	
	@Query(value="SELECT * FROM yac_service service  WHERE service.store_id_fk = :storeId and service.close_date = :closedDate and service.advisor_no = :advisorNo", nativeQuery=true)
	List<Service> findByStoreIdFkAndCloseDateAndAdvisorNo(@Param("storeId") Long storeId,@Param("closedDate") String closedDate ,@Param("advisorNo") String advisorNo);
	
	

}
