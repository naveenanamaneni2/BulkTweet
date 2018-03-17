package com.anamaneni.bulk.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class PropertyLoader {
	private static PropertyLoader LOADER;
	static Properties properties;

	private PropertyLoader() {

	}

	public static PropertyLoader getInstance() {
		synchronized (PropertyLoader.class) {
			if (LOADER == null) {
				LOADER = new PropertyLoader();
			}
		}
		return LOADER;
	}

	public static String propertyValue(String key) throws FileNotFoundException, IOException {
		if (LOADER == null || properties == null) {
			LOADER = PropertyLoader.getInstance();
			properties = new Properties();
			properties.load(new FileInputStream(new File("D:/application.properties")));
		}
		properties.getProperty(key);

		return properties.getProperty(key).trim();
	}
	
	public static File getImageName() throws NumberFormatException, FileNotFoundException, IOException{
		Random randomNum = new Random();
		int imageNumber = 1 + randomNum.nextInt(Integer.valueOf(PropertyLoader.propertyValue("IMAGES_COUNT")));
		String filePath = "D:/images/image_"+imageNumber+".JPG";
		return new File(filePath);
	}

}
