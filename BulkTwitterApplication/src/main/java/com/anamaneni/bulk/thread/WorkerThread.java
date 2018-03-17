package com.anamaneni.bulk.thread;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.anamaneni.bulk.connect.TweetBean;
import com.anamaneni.bulk.connect.TweetResult;
import com.anamaneni.bulk.util.UpdateStatus;


public class WorkerThread implements Callable<TweetResult> {

	public String twitterId;
	public TweetBean tweetBean;
	public UpdateStatus updateStatus;
	public WorkerThread() {
		
	}
	
/*	@Autowired
	@Qualifier("updateStatus")
	public UpdateStatus updateStatus;*/
		
	public WorkerThread(String twitterId, TweetBean tweetBean,UpdateStatus updateStatus) {
		super();
		this.twitterId = twitterId;
		this.tweetBean = tweetBean;
		this.updateStatus = updateStatus;
	}

	private static final Logger log = Logger.getLogger(WorkerThread.class);

	@Override
	public TweetResult call() throws Exception {
		log.info("WorkerThread Started .... call()... Started");
		log.info("Thread Name "+Thread.currentThread().getName()+" Thread Group : "+Thread.currentThread().getThreadGroup());
		TweetResult tweetResult = updateStatus.updateTweet(twitterId, tweetBean);
		log.info("WorkerThread Started .... call()... Ends");
		return tweetResult;
	}

}
