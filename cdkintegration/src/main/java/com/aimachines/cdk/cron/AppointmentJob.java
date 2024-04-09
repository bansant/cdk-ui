package com.aimachines.cdk.cron;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aimachines.cdk.service.BridgeService;
import com.aimachines.cdk.utils.ServiceException;


public class AppointmentJob implements Job {

	private static Logger logger = LoggerFactory.getLogger(AppointmentJob.class);

	@Autowired
	private BridgeService service;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
	
		try {
		
			// execute the  cron
			service.executeAppointmentsLoad();
		} catch (ServiceException e) {
			logger.error("Exception for Job execution  for Open Ro " + e.getMessage());
		}
	}

}
