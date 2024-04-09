package com.aimachines.cdk.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aimachines.cdk.model.Appointment;

@Transactional
@Repository
public interface AppointmentRepo extends CrudRepository<Appointment, Long>{

	List<Appointment> findByAppointmentId(Long appointMentId);
	
	
	List<Appointment> findByStoreIdFkAndAppointmentDate(Long storeIdFk,String appointmentDate);
	
	List<Appointment> findByStoreIdFkAndCustNoAndVin(Long storeIdFk,String custNo,String vin);
	
	List<Appointment> findByStoreIdFk(Long storeIdFk);
}
