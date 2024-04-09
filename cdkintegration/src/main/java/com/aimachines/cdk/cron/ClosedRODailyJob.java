package com.aimachines.cdk.cron;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.aimachines.cdk.service.BridgeService;
import com.aimachines.cdk.utils.ServiceException;


public class ClosedRODailyJob implements Job {

	private static Logger logger = LoggerFactory.getLogger(ClosedRODailyJob.class);

	@Autowired
	private BridgeService service;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		try {
			// execute the daily cron
			service.executeDailyClosedRoExtractAndLoad();
		} catch (ServiceException e) {
			logger.error("Exception for Job execution  for daily load for  Closed RO starting on "  + e.getMessage());
		}

	}

}
