package com.anamaneni.bulk.connect;

import java.io.Serializable;

/**
 * @author Naveen Anamaneni
 *
 */
public class AccessBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String twitterId = "";
	private String consumerKey = "";
	private String consumerSecret = "";
	private String accessToken = "";
	private String accessTokenSecret = "";

	public AccessBean(String twitterId, String consumerKey, String consumerSecret, String accessTokenTest,
			String accessTokenSecret) {
		super();
		this.twitterId = twitterId;
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		this.accessToken = accessTokenTest;
		this.accessTokenSecret = accessTokenSecret;
	}
	public AccessBean() {
	}
	
	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = (null != twitterId) ? twitterId : "";
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = (null != consumerKey) ? consumerKey : "";
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = (null != consumerSecret) ? consumerSecret : "";
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessTokenTest(String accessTokenTest) {
		this.accessToken = (null != accessTokenTest) ? accessTokenTest : "";
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = (null != accessTokenSecret) ? accessTokenSecret : "";
	}

	@Override
	public String toString() {
		return "AccessBean [twitterId=" + twitterId + ", consumerKey=" + consumerKey + ", consumerSecret="
				+ consumerSecret + ", accessToken=" + accessToken + ", accessTokenSecret=" + accessTokenSecret
				+ "]";
	}

	

}
