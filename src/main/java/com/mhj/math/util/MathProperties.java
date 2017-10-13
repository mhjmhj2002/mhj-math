package com.mhj.math.util;

import java.util.ResourceBundle;

public class MathProperties {

//	private static final Logger logger = Logger.getLogger(MathProperties.class);
//
//	private static final String PATH = "src/main/resources/math.properties";
//
//	private static Properties properties;
//
//	private static MathProperties INSTANCE = new MathProperties();
	
//	private static ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", new Locale("en","US"));
	private static ResourceBundle bundle = ResourceBundle.getBundle("messages");

	private MathProperties() {
		super();
	}
	
	public static String getPropertyString(final String propertyName){
		return bundle.getString(propertyName);
	}

//	public static String getPropertyString2(final String propertyName) {
//
//		INSTANCE.loadProperties();
//		String propertyValue;
//		if (properties == null) {
//			propertyValue = "";
//		} else {
//			propertyValue = properties.getProperty(propertyName);
//		}
//
//		return propertyValue;
//	}
//
//	public static String getPropertyString(final String propertyName, final Object... args) {
//		String s = getPropertyString(propertyName);
//		return MessageFormat.format(s, args);
//	}
//
//	private void loadProperties() {
//		synchronized (INSTANCE) {
//			if (properties == null) {
//				FileInputStream in = null;
//				try {
//					in = new FileInputStream(PATH);
//					Properties p = new Properties();
//					p.load(in);
//					properties = p;
//				} catch (IOException e) {
//					logger.fatal("getPropertyString Error - ", e);
//				} finally {
//					// IOUtils.closeQuietly(in);
//				}
//			}
//		}
//	}

}
