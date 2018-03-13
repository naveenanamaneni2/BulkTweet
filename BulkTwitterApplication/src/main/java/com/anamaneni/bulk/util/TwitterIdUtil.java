package com.anamaneni.bulk.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.anamaneni.bulk.connect.AccessBean;
import com.anamaneni.bulk.connect.TweetBean;
import com.opencsv.CSVReader;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Component
public class TwitterIdUtil {

	private Logger logger = Logger.getLogger(TwitterIdUtil.class.getClass());

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
			TweetBean tweetBean = new TweetBean(accessBean.getTwitterId(), tweetsList.get(i)[0], new Date(), accessBean, "NO", "NO");
			concurrentHashMap.put(accessBean.getTwitterId(), tweetBean);
		}

		logger.info("tweetBean Construction Ends");
		return concurrentHashMap;

	}

	private static List<String[]> readCsvFile(String tweetBulkFile) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(tweetBulkFile), ',');
		List<String[]> tweelist = reader.readAll();
		reader.close();
		return tweelist;
	}
}
