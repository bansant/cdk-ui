package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.OpenRo;
import com.aimachines.cdk.model.OpenRoDetail;

@Transactional
@Repository
public interface OpenRoRepo extends CrudRepository<OpenRo, Long>{

	List<OpenRo> findByRoNumber(String roNumber);
	
	List<OpenRo> findByStoreIdFkAndCustNoAndVin(Long storeId,String custNo,String vin);
	
	
	List<OpenRo> findByStoreIdFkAndStatusCodeIn(Long storeId,List<String> statusCode);
	
	
	List<OpenRo> findByStoreIdFkAndStatusCodeInAndAdvisorNo(Long storeId,List<String> statusCode,String advisorNo);
	
	List<OpenRo> findByStoreIdFk(Long storeId);
}
