package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.ServiceDetail;
import com.aimachines.cdk.model.ServiceStatusTrail;

@Transactional
@Repository
public interface ServiceDetailRepo extends CrudRepository<ServiceDetail, Long>{

	List<ServiceDetail> findByRoNumber(String roNumber);
}