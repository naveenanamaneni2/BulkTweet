package com.anamaneni.bulk.util;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.anamaneni.bulk.connect.AccessBean;
import com.anamaneni.bulk.connect.ApplicationConstants;
import com.anamaneni.bulk.connect.RetweetBean;
import com.anamaneni.bulk.connect.RetweetResult;
import com.anamaneni.bulk.connect.TweetBean;
import com.anamaneni.bulk.connect.TweetResult;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Component("updateStatus")
@Scope("prototype")
public class UpdateStatus {

	private static final Logger log = Logger.getLogger(UpdateStatus.class.getClass());
	// private final static String consumerKey = "e5gvi6tjrVgdYNjmIHwiKHkZ7";
	// private final static String consumerSecret =
	// "uLLG47wk6LI1q1RZhgiOqhlPyVtu1yxHuq0VumVJWakJrRY17Z";
	// private final static String accessTokenTest =
	// "708159628341686272-st0wpsIbFzxELAeWdS4bCCk8cwmTzv2";
	// private final static String accessTokenSecret =
	// "pwQsktve7fXq93KWhtIE2M1G3VKxypQYpyS1CNGTxGWjh";
	// private static Long twitterId = 0l;

	public long tweet(List<AccessBean> accessBeans) throws TwitterException {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(accessBeans.get(1).getConsumerKey(), accessBeans.get(1).getConsumerSecret());
		AccessToken accessToken = new AccessToken(accessBeans.get(1).getAccessToken(),
				accessBeans.get(1).getAccessTokenSecret());
		twitter.setOAuthAccessToken(accessToken);
		// twitter.updateStatus("#YSRCP #filledwithCorrupted");
		// twitterId = twitter.getId(); //708159628341686272
		Status status = twitter.updateStatus("#YSRCP is indian courrupted party");
		long twitterId = status.getId();
		// twitter.invalidateOAuth2Token();
		// System.out.println("Twitter Id :"+twitterId);
		return twitterId;
	}

	public TweetResult updateTweet(String twitterId, TweetBean tweetBean) {
		log.info("updateTweet Starts...");
		TweetResult result = new TweetResult();
		AccessBean accessBeans = tweetBean.getAccessBean();
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(accessBeans.getConsumerKey(), accessBeans.getConsumerSecret());
		AccessToken accessToken = new AccessToken(accessBeans.getAccessToken(), accessBeans.getAccessTokenSecret());
		twitter.setOAuthAccessToken(accessToken);

		try {
			if (twitter.getAuthorization().isEnabled()) {
				result.setAuthStatus(ApplicationConstants.SUCCESS);
				result.setAuthStatusError("");
			} else {
				result.setAuthStatus(ApplicationConstants.ERROR);
				result.setAuthStatusError("Invalid Access Token");
			}
			if (PropertyLoader.propertyValue("IMG").equalsIgnoreCase("YES")) {
//				StatusUpdate imageStatus = new StatusUpdate(tweetBean.getTweetName());
//				imageStatus.setMedia(new File(PropertyLoader.getImageName()));
				Status status = twitter.updateStatus(new StatusUpdate(tweetBean.getTweetName()).media(PropertyLoader.getImageName()));
				twitter.createFavorite(status.getId());
				result.setTwitterMessageId(String.valueOf(status.getId()));
				result.setTweetStatus(ApplicationConstants.SUCCESS);
				result.setTwitterId(String.valueOf(twitter.getId()));
			} else {
				Status status = twitter.updateStatus(tweetBean.getTweetName());
				twitter.createFavorite(status.getId());
				result.setTwitterMessageId(String.valueOf(status.getId()));
				result.setTweetStatus(ApplicationConstants.SUCCESS);
				result.setTwitterId(String.valueOf(twitter.getId()));
			}

		} catch (TwitterException e) {
			log.error("Exception Occured While Authenticating User");
			result.setAuthStatusError("Invalid Access Token : " + e.getErrorMessage());
			result.setTweetMessageError("Status Update Fail : " + e.getErrorMessage());
		} catch (IOException e) {
			log.error("Exception Occured While Reading Properties File");
		}

		log.info("updateTweet Ends...");
		return result;
	}

	public RetweetResult reTweet(String twitterId, List<RetweetBean> tweetBean) {
		log.info("reTweet Satrts...");
		RetweetResult retweetResult = null;
		for (int i = 0; i < tweetBean.size(); i++) {
			RetweetBean retweetBean = tweetBean.get(i);
			retweetResult = new RetweetResult();
			AccessBean accessBeans = retweetBean.getAccessBean();
			Twitter twitter = new TwitterFactory().getInstance();
			twitter.setOAuthConsumer(accessBeans.getConsumerKey(), accessBeans.getConsumerSecret());
			AccessToken accessToken = new AccessToken(accessBeans.getAccessToken(), accessBeans.getAccessTokenSecret());
			twitter.setOAuthAccessToken(accessToken);
			String[] reTweetIds = retweetBean.getReTweetMessageIds();
			for (int j = 0; j < reTweetIds.length; j++) {
				try {
					twitter.retweetStatus(Long.valueOf(reTweetIds[j]));
					twitter.createFavorite(Long.valueOf(reTweetIds[j]));
					retweetResult.setRetweetId(twitterId);
					retweetResult.setRetweetStatus(ApplicationConstants.SUCCESS);
					retweetResult.setRetweetId(reTweetIds[j]);
				} catch (TwitterException e) {
					log.error("Retweet Status Failed : " + e.getMessage());
					retweetResult.setRetweetStatus(ApplicationConstants.ERROR);
					retweetResult.setRetweetErrorMessage(e.getErrorMessage());
				}
			}
		}
		log.info("reTweet Ends...");
		return retweetResult;
	}

}
