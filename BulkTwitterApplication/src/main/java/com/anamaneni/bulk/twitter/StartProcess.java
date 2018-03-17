package com.anamaneni.bulk.twitter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anamaneni.bulk.connect.AccessBean;
import com.anamaneni.bulk.connect.ApplicationConstants;
import com.anamaneni.bulk.connect.RetweetBean;
import com.anamaneni.bulk.connect.RetweetResult;
import com.anamaneni.bulk.connect.TweetBean;
import com.anamaneni.bulk.connect.TweetResult;
import com.anamaneni.bulk.thread.RetweetWorkerThread;
import com.anamaneni.bulk.thread.WorkerThread;
import com.anamaneni.bulk.util.CSVUtil;
import com.anamaneni.bulk.util.PropertyLoader;
import com.anamaneni.bulk.util.TwitterIdUtil;
import com.anamaneni.bulk.util.UpdateStatus;

@Component
public class StartProcess {

	@Autowired
	CSVUtil csvUtil;

	@Autowired
	TwitterIdUtil twitterIdUtil;

	@Autowired
	UpdateStatus updateStatus;

	private static final Logger log = LoggerFactory.getLogger(StartProcess.class);

	@SuppressWarnings({"unchecked"})
	public void startProcessingRequest() throws IOException, InterruptedException, ExecutionException {
		log.info("startProcessingRequest method Started");
		AccessBean accessBean = new AccessBean();
		List<?> objects = (List<AccessBean>) csvUtil.readCsvFile(accessBean, "D:/authentication.csv");
		String fileName = null;
		String date = ApplicationConstants.FORMAT.format(new Date());
		fileName = "D:/TweetResult" + date + ".csv";
		

		if (PropertyLoader.propertyValue("TW_FLAG").equalsIgnoreCase("YES") || PropertyLoader.propertyValue("T_R_FFLAG").equalsIgnoreCase("YES")) {
			ExecutorService executor = Executors.newFixedThreadPool(2);
			ConcurrentHashMap<String, TweetBean> tweetsBankMap = twitterIdUtil.tweetBeanConstruction("D:/tweetBank.csv",
					(List<AccessBean>) objects);
			List<Future<TweetResult>> tweetResults = new ArrayList<Future<TweetResult>>();
			Future<TweetResult> resultsBean = null;

			for (String string : tweetsBankMap.keySet()) {
				TweetBean tweetBean = tweetsBankMap.get(string);
				String twitterId = string;
				Callable<TweetResult> callable = new WorkerThread(twitterId, tweetBean, updateStatus);
				resultsBean = executor.submit(callable);
				tweetResults.add(resultsBean);
			}
			List<TweetResult> results = new ArrayList<TweetResult>();
			for (Future<TweetResult> future : tweetResults) {
				TweetResult tweetResult = future.get();
				log.info("Tweet Results : " + tweetResult.toString());
				results.add(tweetResult);
			}
			executor.shutdown();
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			csvUtil.writeCsvFile(results, fileName);
		}
		if (PropertyLoader.propertyValue("RT_FLAG").equalsIgnoreCase("YES")) {
			ExecutorService executor = Executors.newFixedThreadPool(2);
			log.info("Retweet Process Started.");
			ConcurrentHashMap<String, List<RetweetBean>> hashMap = twitterIdUtil.retweetBeanConstruction(fileName, (List<AccessBean>) objects);
			log.info("hashMap .. Size :"+hashMap);
			List<Future<RetweetResult>> reTweetResults = new ArrayList<Future<RetweetResult>>();
			Future<RetweetResult> resultsBean = null;
			
			for (String string : hashMap.keySet()) {
				List<RetweetBean> retweetBean = (List<RetweetBean>) hashMap.get(string);
				String twitterId = string;
				Callable<RetweetResult> callable = new RetweetWorkerThread(twitterId,retweetBean,updateStatus);
				resultsBean = executor.submit(callable);
				reTweetResults.add(resultsBean);
			}
			List<RetweetResult> results = new ArrayList<RetweetResult>();
			for (Future<RetweetResult> future : reTweetResults) {
				RetweetResult tweetResult = future.get();
				log.info("Re - Tweet Results : " + tweetResult.toString());
				results.add(tweetResult);
			}
			executor.shutdown();
			String reTweetResultsFile = "D:/Re-TweetResult" + date + ".csv";
			File file = new File(reTweetResultsFile);
			if (!file.exists()) {
				file.createNewFile();
			}
			csvUtil.writeReTweetCsvFile(results, reTweetResultsFile);
			log.info("Retweet Process Ends.");
		}
		System.exit(0);
	}

}
