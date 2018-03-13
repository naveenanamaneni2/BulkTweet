package com.anamaneni.bulk.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.anamaneni.bulk.connect.AccessBean;
import com.opencsv.CSVReader;

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
				Iterator<String[]> iterator= reader.readAll().iterator();
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

}
