package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.Store;

@Transactional
@Repository
public interface StoreRepo extends CrudRepository<Store, Long>{

	List<Store> findByStoreId(String storeId);
}
