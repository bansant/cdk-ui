package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.Customer;

@Transactional
@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long>{

	List<Customer> findByCustNo(String customerNo);
}
