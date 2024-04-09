package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.ServiceStatusTrail;

@Transactional
@Repository
public interface ServiceStatusTrailRepo extends CrudRepository<ServiceStatusTrail, Long>{

	List<ServiceStatusTrail> findByRoNumberOrderByCreatedOnDesc(String roNumber);
}
