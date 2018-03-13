package com.anamaneni.bulk.util;

import org.springframework.stereotype.Component;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;


@Component("rsetweetUpdate")
public class RetweetUpdate {

	
	private final static String consumerKey1 = "KoXo0wgYHoGHEMsha1I4yx0xo";
	private final static String consumerSecret1 = "ytlTyBWUdQraud3GtRJ9b2Uo1ly5s5u5TV2I0lvbSseswk0AWU";
	private final static String accessTokenTest1 = "892816266-CCPy5HmYWkcXgcH3eXowtYXOLZ3jnI6NYyZG3Ygm";
	private final static String accessTokenSecret1 = "LxuhbkplhZr68lseHf1wRywNhZXqFVf33yr8IxqRa01rq";
	
	public void reTweet(long twitterId) throws TwitterException{
		Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey1, consumerSecret1);
        AccessToken accessToken = new AccessToken(accessTokenTest1, accessTokenSecret1);
        twitter.setOAuthAccessToken(accessToken);
		twitter.retweetStatus(twitterId).getRetweetedStatus();
		
		System.out.println("Twitter Id :"+twitterId);
	}
}
