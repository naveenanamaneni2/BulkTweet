package com.anamaneni.bulk.twitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anamaneni.bulk.connect.AccessBean;
import com.anamaneni.bulk.connect.TweetBean;
import com.anamaneni.bulk.util.CSVUtil;
import com.anamaneni.bulk.util.TwitterIdUtil;

@Component
public class StartProcess {

	@Autowired
	CSVUtil csvUtil;
	@Autowired
	TwitterIdUtil twitterIdUtil;

	private static final Logger log = LoggerFactory.getLogger(StartProcess.class);

	@SuppressWarnings("unchecked")
	public void startProcessingRequest() throws IOException {
		log.info("startProcessingRequest method Started");
		AccessBean accessBean = new AccessBean(null, null, null, null, null);
		List<?> objects = (List<AccessBean>) csvUtil.readCsvFile(accessBean, "D:/authentication.csv");
		log.info("Size : " + objects.size());
		ConcurrentHashMap<String, TweetBean> tweetsBankMap = twitterIdUtil.tweetBeanConstruction("D:/tweetBank.csv",
				(List<AccessBean>) objects);
	}

}
