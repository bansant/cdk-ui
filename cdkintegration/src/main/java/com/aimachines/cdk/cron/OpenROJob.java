package com.aimachines.cdk.cron;

import java.io.IOException;
import java.text.ParseException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aimachines.cdk.service.BridgeService;
import com.aimachines.cdk.utils.ServiceException;


public class OpenROJob implements Job {

	private static Logger logger = LoggerFactory.getLogger(OpenROJob.class);

	@Autowired
	private BridgeService service;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
	
		try {
		
			// execute the  cron
			service.executeOpenRoExtractAndLoad();
		} catch (ServiceException e) {
			logger.error("Exception for Job execution  for Open Ro " + e.getMessage());
		} catch (ParseException e) {
			logger.error("Exception for Job execution  for Open Ro " + e.getMessage());
		} catch (IOException e) {
			logger.error("Exception for Job execution  for Open Ro " + e.getMessage());
		}

	}

}
