package com.anamaneni.bulk.connect;

import java.util.Date;

public class TweetBean {

	private String twitterId = "";
	private String tweetName = "";
	private Date tweetDate;
	private AccessBean accessBean;
	private String updateStatus = "";
	private String retweetStatus= "";
	
	
	
	public AccessBean getAccessBean() {
		return accessBean;
	}
	public void setAccessBean(AccessBean accessBean) {
		this.accessBean = accessBean;
	}
	public String getUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}
	public String getRetweetStatus() {
		return retweetStatus;
	}
	public void setRetweetStatus(String retweetStatus) {
		this.retweetStatus = retweetStatus;
	}
	public String getTwitterId() {
		return twitterId;
	}
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	public String getTweetName() {
		return tweetName;
	}
	public void setTweetName(String tweetName) {
		this.tweetName = tweetName;
	}
	public Date getTweetDate() {
		return tweetDate;
	}
	public void setTweetDate(Date tweetDate) {
		this.tweetDate = tweetDate;
	}
	public TweetBean(String twitterId, String tweetName, Date tweetDate, AccessBean accessBean, String updateStatus,
			String retweetStatus) {
		super();
		this.twitterId = twitterId;
		this.tweetName = tweetName;
		this.tweetDate = tweetDate;
		this.accessBean = accessBean;
		this.updateStatus = updateStatus;
		this.retweetStatus = retweetStatus;
	}
	@Override
	public String toString() {
		return "TweetBean [twitterId=" + twitterId + ", tweetName=" + tweetName + ", tweetDate=" + tweetDate
				+ ", accessBean=" + accessBean + ", updateStatus=" + updateStatus + ", retweetStatus=" + retweetStatus
				+ "]";
	}
	
	
}
