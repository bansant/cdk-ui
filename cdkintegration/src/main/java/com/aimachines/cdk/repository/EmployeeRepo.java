package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.Employee;

@Transactional
@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long>{

	List<Employee> findByEmpId(String employeeId);
	
	List<Employee> findByStoreIdFk(Long storeId);
	
	List<Employee> findByStoreIdFkAndEmpId(Long storeId,String employeeId);
}
