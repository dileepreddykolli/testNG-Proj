package com.framework.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProp {

	public static Properties readData(String filename) {
		Properties prop = null;
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Config\\" + filename); //to read file
			prop = new Properties();
			prop.load(fis); //load properties from file into prop variable
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

}
