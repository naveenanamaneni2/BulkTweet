package com.anamaneni.bulk.connect;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Naveen Anamaneni
 *
 */
public class RetweetBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RetweetBean() {
		
	}
	private String tweeterID;
	private AccessBean accessBean;
	private String[] reTweetMessageIds;


	public String[] getReTweetMessageIds() {
		return reTweetMessageIds;
	}
	public void setReTweetMessageIds(String[] reTweetMessageIds) {
		this.reTweetMessageIds = reTweetMessageIds;
	}
	public String getTweeterID() {
		return tweeterID;
	}
	public void setTweeterID(String tweeterID) {
		this.tweeterID = tweeterID;
	}
	public AccessBean getAccessBean() {
		return accessBean;
	}
	public void setAccessBean(AccessBean accessBean) {
		this.accessBean = accessBean;
	}
	@Override
	public String toString() {
		return "RetweetBean [tweeterID=" + tweeterID + ", accessBean=" + accessBean + ", reTweetMessageIds="
				+ Arrays.toString(reTweetMessageIds) + "]";
	}
	public RetweetBean(String tweeterID, AccessBean accessBean, String[] reTweetMessageIds) {
		super();
		this.tweeterID = tweeterID;
		this.accessBean = accessBean;
		this.reTweetMessageIds = reTweetMessageIds;
	}
	
	
}
