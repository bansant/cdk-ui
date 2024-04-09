package com.aimachines.cdk.cron;

import static org.quartz.JobBuilder.newJob;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aimachines.cdk.cron.config.CronConfiguration;

@Service
public class ScheduleAppJobService {

	private static Logger logger = LoggerFactory.getLogger(ScheduleAppJobService.class);
	
	@Autowired
	private CronConfiguration cronConfiguration;
	
	private static final String JOB_GROUP = "jobgroup";

	public void schedule(LocalDateTime scheduleDateTime,String queryId) {
		Scheduler scheduler = null;
		String jobName = "";
		String triggerName = "";
		Date scheduleStartDateTime =  null;
		JobDetail job = null;
		Date ft = null;
		try {
			scheduleStartDateTime = Date.from(scheduleDateTime.atZone(ZoneId.systemDefault()).toInstant());
			scheduler = cronConfiguration.schedulerFactoryBean().getScheduler();
			jobName = "apptBulkExtractJob_" + System.currentTimeMillis();
			triggerName = "apptBulkExtractTrigger_" + System.currentTimeMillis();

			job = newJob(AppointmentJob.class).withIdentity(jobName, JOB_GROUP).requestRecovery(true).build();

			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, JOB_GROUP)
					.startAt(scheduleStartDateTime)
					.forJob(jobName, JOB_GROUP)
					.withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression("0 0 6 * * ?")).withMisfireHandlingInstructionFireAndProceed()).build();
			
			ft = scheduler.scheduleJob(job, trigger);
			
			scheduler.start();
			
			logger.info(job.getKey() + " is scheduled at: " + ft + " for daily load of query id "+ queryId);
			
		} catch (SchedulerException e) {
			logger.error("Exception occurred while scheduling job "+jobName + " for daily load of query id "+ queryId);
		} catch (IOException e) {
			logger.error("Exception occurred while scheduling job "+jobName + " for daily load of query id "+ queryId);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
