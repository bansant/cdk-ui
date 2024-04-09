package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.OpenRoDetail;
import com.aimachines.cdk.model.ServiceDetail;

@Transactional
@Repository
public interface OpenRoDetailRepo extends CrudRepository<OpenRoDetail, Long>{

	List<OpenRoDetail> findByRoNumber(String roNumber);

}
