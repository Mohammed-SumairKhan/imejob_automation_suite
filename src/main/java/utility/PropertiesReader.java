package utility;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Utility class to read configuration values from config.properties file.
 * This helps in keeping to read properties file value details externalized
 * so they can be changed without touching the code.
 */
public class PropertiesReader {
    Properties properties;  // Properties object to hold key-value pairs

    public PropertiesReader() {
        properties = new Properties();  // Initialize Properties object
        try {
            FileInputStream fileInputStream = new FileInputStream(
                    System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");  // Load file from resources folder
            properties.load(fileInputStream);  // Load key-value pairs into Properties object
        } catch (Exception e) {
            e.printStackTrace();  // Print stack trace if file is not found or loading fails
        }
    }
    
    /**
     * Fetches the browser name from config.properties file
     * Example: chrome, firefox, edge
     *
     * @return browser name as String
     */
    public String getBrowserName() {
        return properties.getProperty("browser");  // Fetch browser name from config.properties
    }
    
    /**
     * Fetches the base URL from config.properties file
     * Example: https://imejob.com
     *
     * @return URL as String
     */
    public String getUrl() {
        return properties.getProperty("url");  // Fetch base URL from config.properties
    }
    
    /**
     * Reads Hard wait time (in seconds) from the properties file.
     * 
     * @return Hard  wait duration in seconds
     */
    public int getHardWait() {
    	return Integer.parseInt(properties.getProperty("hardwait"));
    }
    
    /**
     * Reads Implicit wait time (in seconds) from the properties file.
     * 
     * @return Implicit wait duration in seconds
     */
    public int getImplicitWait() {
    	return Integer.parseInt(properties.getProperty("implicit"));
    }
    
    /**
     * Reads explicit wait time (in seconds) from the properties file.
     * 
     * @return explicit wait duration in seconds
     */
    public int getExplicitWait() {
    	return Integer.parseInt(properties.getProperty("explicit"));
    }
    
    public String getUrl2() {
        return properties.getProperty("url2");  // Fetch base URL from config.properties
    }
}




