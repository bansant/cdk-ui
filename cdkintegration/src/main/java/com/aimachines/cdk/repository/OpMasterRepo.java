package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.OpCodeMaster;

@Transactional
@Repository
public interface OpMasterRepo extends CrudRepository<OpCodeMaster, Long>{

	List<OpCodeMaster> findByOpCode(String opCode);
}
