package com.anamaneni.bulk.connect;

import java.io.Serializable;

/**
 * @author Naveen Anamaneni
 *
 */
public class TweetResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String twitterId;
	private String twitterMessageId;
	private String tweetStatus;
	private String authStatus;
	private String tweetMessageError;
	private String authStatusError;

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getTwitterMessageId() {
		return twitterMessageId;
	}

	public void setTwitterMessageId(String twitterMessageId) {
		this.twitterMessageId = twitterMessageId;
	}

	public String getTweetStatus() {
		return tweetStatus;
	}

	public void setTweetStatus(String tweetStatus) {
		this.tweetStatus = tweetStatus;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public String getTweetMessageError() {
		return tweetMessageError;
	}

	public void setTweetMessageError(String tweetMessageError) {
		this.tweetMessageError = tweetMessageError;
	}

	public String getAuthStatusError() {
		return authStatusError;
	}

	public void setAuthStatusError(String authStatusError) {
		this.authStatusError = authStatusError;
	}

	public TweetResult(String twitterId, String twitterMessageId, String tweetStatus, String authStatus,
			String tweetMessageError, String authStatusError) {
		super();
		this.twitterId = twitterId;
		this.twitterMessageId = twitterMessageId;
		this.tweetStatus = tweetStatus;
		this.authStatus = authStatus;
		this.tweetMessageError = tweetMessageError;
		this.authStatusError = authStatusError;
	}

	@Override
	public String toString() {
		return "TweetResult [twitterId=" + twitterId + ", twitterMessageId=" + twitterMessageId + ", tweetStatus="
				+ tweetStatus + ", authStatus=" + authStatus + ", tweetMessageError=" + tweetMessageError
				+ ", authStatusError=" + authStatusError + "]";
	}
	public TweetResult() {

	}
}
