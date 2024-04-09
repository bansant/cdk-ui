package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.InventoryVehicle;

@Transactional
@Repository
public interface VehicleRepo extends CrudRepository<InventoryVehicle, Long>{

	List<InventoryVehicle> findByVin(String vin);
}
