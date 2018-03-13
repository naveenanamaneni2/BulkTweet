package com.anamaneni.bulk.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Component;

import com.anamaneni.bulk.connect.AccessBean;

import twitter4j.TwitterException;

@Component("tweetApp")
public class TweetApp {
	private final static String consumerKey = "e5gvi6tjrVgdYNjmIHwiKHkZ7";
	private final static String consumerSecret = "uLLG47wk6LI1q1RZhgiOqhlPyVtu1yxHuq0VumVJWakJrRY17Z";
	private final static String accessToken = "708159628341686272-st0wpsIbFzxELAeWdS4bCCk8cwmTzv2";
	private final static String accessTokenSecret = "pwQsktve7fXq93KWhtIE2M1G3VKxypQYpyS1CNGTxGWjh";
	Logger logger = LoggerFactory.getLogger(TweetApp.class);

	@Autowired
	public UpdateStatus updateStatus;
	@Autowired
	public RetweetUpdate retweetUpdate;

	public void test(List<AccessBean> accessBeans) throws TwitterException {
	
			logger.info("Fuck You");
			long twitterId = updateStatus.tweet(accessBeans);
			retweetUpdate.reTweet(twitterId);
			logger.info("Thanks for Fucking Tweet");
		
	}
}
