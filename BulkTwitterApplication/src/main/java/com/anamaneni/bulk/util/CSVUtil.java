package com.anamaneni.bulk.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.anamaneni.bulk.connect.AccessBean;
import com.anamaneni.bulk.connect.RetweetResult;
import com.anamaneni.bulk.connect.TweetResult;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * @author Naveen Anamaneni
 *
 */
@Component("csvUtil")
public class CSVUtil {

	private static Logger log = Logger.getLogger(CSVUtil.class.getClass());

	// reading csv file
	public Object readCsvFile(Object object, String fileName) {
		log.info("readingCSVFile .... Start");
		List<Object> objectList = null;
		try {
			CSVReader reader = new CSVReader(new FileReader(fileName), ',');

			if (object instanceof AccessBean) {
				log.info("readingCSVFile .... AccessToken Bean Mapping");
				objectList = new ArrayList<Object>();
				Iterator<String[]> iterator = reader.readAll().iterator();
				while (iterator.hasNext()) {
					String[] record = iterator.next();
					AccessBean accessBean = new AccessBean(record[0], record[1], record[2], record[3], record[4]);
					objectList.add(accessBean);
				}
				reader.close();
			}
		} catch (IOException e) {
			log.error("Exception occmured readingCSVFile" + e);
		}
		log.info("readingCSVFile .... Ends");
		return objectList;
	}

	@SuppressWarnings("unchecked")
	public void writeCsvFile(Object object, String fileName) throws IOException {

		List<String[]> writeRecords = new ArrayList<String[]>();
		List<TweetResult> results = (List<TweetResult>) object;
		// writeRecords.add(new String[] { "twitterId", "twitterMessageId",
		// "tweetStatus", "authStatus",
		// "tweetMessageError", "authStatusError" });
		Iterator<TweetResult> iterator = results.iterator();
		while (iterator.hasNext()) {
			TweetResult tweetResult = iterator.next();
			writeRecords.add(new String[] { tweetResult.getTwitterId(), tweetResult.getTwitterMessageId(),
					tweetResult.getTweetStatus(), tweetResult.getAuthStatus(), tweetResult.getTweetMessageError(),
					tweetResult.getAuthStatusError() });
		}
		CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		csvWriter.writeAll(writeRecords);
		csvWriter.close();
	}
	
	@SuppressWarnings("unchecked")
	public void writeReTweetCsvFile(Object object, String fileName) throws IOException {

		List<String[]> writeRecords = new ArrayList<String[]>();
		List<RetweetResult> results = (List<RetweetResult>) object;
		for (int i = 0; i < results.size(); i++) {
			RetweetResult retweetResult = results.get(i);
			writeRecords.add(new String[] { retweetResult.getTwitterId(), retweetResult.getRetweetId(),
					retweetResult.getRetweetStatus(), retweetResult.getRetweetErrorMessage() });
		}
		
		CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName), CSVWriter.DEFAULT_SEPARATOR,
				CSVWriter.NO_QUOTE_CHARACTER);
		csvWriter.writeAll(writeRecords);
		csvWriter.close();
	}
}
