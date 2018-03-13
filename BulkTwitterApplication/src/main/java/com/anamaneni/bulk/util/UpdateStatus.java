package com.anamaneni.bulk.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.anamaneni.bulk.connect.AccessBean;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Component("updateStatus")
public class UpdateStatus {
	private final static String consumerKey = "e5gvi6tjrVgdYNjmIHwiKHkZ7";
	private final static String consumerSecret = "uLLG47wk6LI1q1RZhgiOqhlPyVtu1yxHuq0VumVJWakJrRY17Z";
	private final static String accessTokenTest = "708159628341686272-st0wpsIbFzxELAeWdS4bCCk8cwmTzv2";
	private final static String accessTokenSecret = "pwQsktve7fXq93KWhtIE2M1G3VKxypQYpyS1CNGTxGWjh";
	private static Long twitterId = 0l;
	
	public long tweet(List<AccessBean> accessBeans) throws TwitterException{
		Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(accessBeans.get(1).getConsumerKey(), accessBeans.get(1).getConsumerSecret());
        AccessToken accessToken = new AccessToken(accessBeans.get(1).getAccessToken(),accessBeans.get(1).getAccessTokenSecret());
        twitter.setOAuthAccessToken(accessToken);
//		twitter.updateStatus("#YSRCP #filledwithCorrupted");
		twitterId = twitter.getId(); //708159628341686272
		Status status = twitter.updateStatus("#YSRCP is indian courrupted party");
		twitterId = status.getId();
		//twitter.invalidateOAuth2Token();
		
		System.out.println("Twitter Id :"+twitterId);
		return twitterId;
	}
	
	
}
