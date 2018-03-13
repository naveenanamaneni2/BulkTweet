package com.anamaneni.bulk.thread;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;


public class WorkerThread implements Callable<Object>{
	private static final Logger log =  Logger.getLogger(WorkerThread.class.getClass());
	@Override
	public Object call() throws Exception {
		log.info("WorkerThread Started .... call()... Started");
		
		log.info("WorkerThread Started .... call()... Ends");
		return null;
	}

}
