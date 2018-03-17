package com.anamaneni.bulk.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.anamaneni.bulk.connect.AccessBean;
import com.anamaneni.bulk.connect.RetweetBean;
import com.anamaneni.bulk.connect.TweetBean;
import com.anamaneni.bulk.connect.TweetResult;
import com.opencsv.CSVReader;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Component
public class TwitterIdUtil {

	private static Logger logger = Logger.getLogger(TwitterIdUtil.class);

	public List<AccessBean> updateTwitterId(String fileName, List<AccessBean> beans)
			throws IllegalStateException, TwitterException {
		List<AccessBean> accessBeans = new ArrayList<AccessBean>();
		for (AccessBean accessBean : beans) {
			if (accessBean.getTwitterId().equalsIgnoreCase("12345")) {
				Twitter twitter = new TwitterFactory().getInstance();
				twitter.setOAuthConsumer(accessBean.getConsumerKey(), accessBean.getConsumerSecret());
				AccessToken accessToken = new AccessToken(accessBean.getAccessToken(),
						accessBean.getAccessTokenSecret());
				twitter.setOAuthAccessToken(accessToken);
				accessBean.setTwitterId(String.valueOf(twitter.getId()));
				accessBeans.add(accessBean);
			} else {
				accessBeans.add(accessBean);
			}
		}

		return accessBeans;
	}

	public ConcurrentHashMap<String, TweetBean> tweetBeanConstruction(String tweetBankFileName,
			List<AccessBean> accessBeans) throws IOException {
		logger.info("tweetBean Construction Start");
		ConcurrentHashMap<String, TweetBean> concurrentHashMap = new ConcurrentHashMap<String, TweetBean>();
		List<String[]> tweetsList = readCsvFile(tweetBankFileName);

		for (int i = 0; i < accessBeans.size(); i++) {
			AccessBean accessBean = accessBeans.get(i);
			TweetBean tweetBean = new TweetBean(accessBean.getTwitterId(), tweetsList.get(i)[0].replace("???", ","),
					new Date(), accessBean, "NO", "NO");
			concurrentHashMap.put(accessBean.getTwitterId(), tweetBean);
		}
		logger.info("tweetBean Construction Ends");
		return concurrentHashMap;
	}

	public ConcurrentHashMap<String, List<RetweetBean>> retweetBeanConstruction(String fileName,
			List<AccessBean> accessBeans) {
		ConcurrentHashMap<String, List<RetweetBean>> hashMap = new ConcurrentHashMap<String, List<RetweetBean>>();
		try {
			logger.info("retweetBeanConstruction Construction Start");

			List<String[]> tweetsList = readCsvFile(fileName);
			List<TweetResult> tweetResults = new ArrayList<>();
			for (int i = 0; i < accessBeans.size(); i++) {
				TweetResult tweetResult = new TweetResult(tweetsList.get(i)[0], tweetsList.get(i)[1],
						tweetsList.get(i)[2], tweetsList.get(i)[3], tweetsList.get(i)[4], tweetsList.get(i)[5]);
				tweetResults.add(tweetResult);
			}

			 logger.info("TweetResult Loadded Successfully.. size : " +tweetResults.size());

			for (int i = 0; i < accessBeans.size(); i++) {
				List<RetweetBean> retweetBeans = new ArrayList<RetweetBean>();
				AccessBean accessBean = accessBeans.get(i);
				String twitterId = accessBean.getTwitterId();
				
				for (int j = i; j < tweetResults.size(); j++) {
					TweetResult tweetResult = tweetResults.get(j);
					RetweetBean retweetBean = new RetweetBean();
					if (accessBean.getTwitterId().equalsIgnoreCase(tweetResult.getTwitterId())) {
						retweetBean.setAccessBean(accessBean);
						retweetBean.setTweeterID(tweetResult.getTwitterId());
						retweetBean.setReTweetMessageIds(formStringArray(tweetResults, tweetResult.getTwitterId()));
					}
					if(null != retweetBean.getTweeterID()){
					retweetBeans.add(retweetBean);
					}
				}
				
				hashMap.put(twitterId, retweetBeans);
			}
		} catch (Exception e) {
			logger.error("Exception Occured While Constructing Bean.." + e.getMessage());
			System.exit(0);
		}
		logger.info("retweetBeanConstruction Construction Ends");
		return hashMap;
	}

	private static String[] formStringArray(List<TweetResult> tweetResults, String twitterID) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		for (int i = 0; i < tweetResults.size(); i++) {
			TweetResult tweetResult = tweetResults.get(i);
			hashMap.put(tweetResult.getTwitterId(), tweetResult.getTwitterMessageId());
		}
		hashMap.remove(twitterID);
		String[] retwettList = new String[hashMap.keySet().size()];
		int i = 0;
		for (Entry<String, String> entry : hashMap.entrySet()) {
			retwettList[i] = entry.getValue();
			i++;
		}
		logger.info("retwettList.. Size : " + retwettList.length);
		return retwettList;
	}

	private static List<String[]> readCsvFile(String tweetBulkFile) throws IOException {
		CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(tweetBulkFile),"UTF-8"), ',');
		List<String[]> tweelist = reader.readAll();
		reader.close();
		return tweelist;
	}
}
