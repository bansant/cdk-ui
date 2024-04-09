package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.Boc;

@Transactional
@Repository
public interface BocRepo extends CrudRepository<Boc, Long>{

	List<Boc> findByBocId(String bocId);
}
