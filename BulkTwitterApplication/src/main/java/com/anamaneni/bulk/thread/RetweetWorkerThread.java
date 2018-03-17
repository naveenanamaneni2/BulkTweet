package com.anamaneni.bulk.thread;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.anamaneni.bulk.connect.RetweetBean;
import com.anamaneni.bulk.connect.RetweetResult;
import com.anamaneni.bulk.util.UpdateStatus;

/**
 * @author Naveen Anamaneni
 *
 */
public class RetweetWorkerThread implements Callable<RetweetResult>{

	public RetweetWorkerThread(String twitterId, List<RetweetBean> retweetBeans, UpdateStatus updateStatus) {
		super();
		this.twitterId = twitterId;
		this.retweetBeans = retweetBeans;
		this.updateStatus = updateStatus;
	}
	public RetweetWorkerThread() {
		
	}

	public String twitterId;
	public List<RetweetBean> retweetBeans;
	public UpdateStatus updateStatus;
	private static final Logger log = Logger.getLogger(RetweetWorkerThread.class);
	
	@Override
	public RetweetResult call() throws Exception {
		log.info("RetweetWorkerThread Started .... call()... Started");
		log.info("Thread Name "+Thread.currentThread().getName()+" Thread Group : "+Thread.currentThread().getThreadGroup());
		RetweetResult tweetResult = updateStatus.reTweet(twitterId, retweetBeans);
		log.info("RetweetWorkerThread Started .... call()... Ends");
		return tweetResult;
	}

}
