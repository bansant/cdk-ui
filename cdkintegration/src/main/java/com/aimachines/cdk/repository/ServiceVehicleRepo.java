package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.ServiceVehicle;

@Transactional
@Repository
public interface ServiceVehicleRepo extends CrudRepository<ServiceVehicle, Long>{

	List<ServiceVehicle> findByVinAndCustNo(String vin ,String custNo);
}
