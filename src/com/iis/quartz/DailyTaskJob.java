package com.iis.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DailyTaskJob {

	private Logger log = LoggerFactory.getLogger(DailyTaskJob.class);
	
	public void execute(){
		System.out.println("----------------执行任务----------------");
	}
}
