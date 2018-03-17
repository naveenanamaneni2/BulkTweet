package com.anamaneni.bulk.connect;

import java.io.Serializable;

/**
 * @author Naveen Anamaneni
 *
 */
public class RetweetResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String twitterId = "";
	private String retweetId = "";
	private String retweetStatus = "";
	private String retweetErrorMessage = "";
	
	public RetweetResult() {
		
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getRetweetId() {
		return retweetId;
	}

	public void setRetweetId(String retweetId) {
		this.retweetId = retweetId;
	}

	public String getRetweetStatus() {
		return retweetStatus;
	}

	public void setRetweetStatus(String retweetStatus) {
		this.retweetStatus = retweetStatus;
	}

	public String getRetweetErrorMessage() {
		return retweetErrorMessage;
	}

	public void setRetweetErrorMessage(String retweetErrorMessage) {
		this.retweetErrorMessage = retweetErrorMessage;
	}

	public RetweetResult(String twitterId, String retweetId, String retweetStatus, String retweetErrorMessage) {
		super();
		this.twitterId = twitterId;
		this.retweetId = retweetId;
		this.retweetStatus = retweetStatus;
		this.retweetErrorMessage = retweetErrorMessage;
	}

	@Override
	public String toString() {
		return "RetweetResult [twitterId=" + twitterId + ", retweetId=" + retweetId + ", retweetStatus=" + retweetStatus
				+ ", retweetErrorMessage=" + retweetErrorMessage + "]";
	}

}
